import logging
from urllib.parse import urljoin
import scrapy
from scrapy import Request
from requests import exceptions
import json
import time
import math
from ..items import BilibiliGameTypeItem
from ..items import BilibiliGameMatchItem
from ..items import BilibiliGameTeamItem
from ..items import BilibiliGameTimeItem

class BilibiliSpider(scrapy.Spider):
    name = 'bilibili'
    allowed_domains = ['bilibili.com']
    #游戏类型
    gameType_url = 'https://api.bilibili.com/x/esports/matchs/filter?mid=0&gid=0&tid=0'
    #比赛日期
    gameTime_url = 'https://api.bilibili.com/x/esports/matchs/list?mid=0&gid=0&tid=0&pn={pageNum}' \
                   '&ps={pageSize}&etime={etime}&stime={stime}'
    request_code = '0'

    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        if kwargs.get("request_code") is not None:
            self.request_code = kwargs.get("request_code")

    def start_requests(self):
        if self.request_code == '0':
            self.request_code = input('爬取游戏类型/赛事分类/队伍信息输入1;爬取比赛信息输入2;爬取当天实时比分输入3:')
        if self.request_code == '1':
            yield Request(self.gameType_url, callback=self.parse_gameType)
        elif self.request_code == '2':
            #爬取原始数据
            stime_str = input('请输入开始日期(xxxx-xx-xx):')
            etime_str = input('请输入结束日期(xxxx-xx-xx):')
            yield Request(self.gameTime_url.format(pageNum=1,
                                                   pageSize=10,
                                                   etime=etime_str,
                                                   stime=stime_str),
                          callback=self.get_total,
                          meta={'etime': etime_str, 'stime': stime_str})
        elif self.request_code == '3':
            #%Y四位数年份表示,%m月份,%d月内中的一天
            #获取当天日期,爬取当天比分变化
            localdate = time.strftime('%Y-%m-%d', time.localtime())
            print('今天的日期:', localdate)
            yield Request(self.gameTime_url.format(pageNum=1,
                                                   pageSize=10,
                                                   etime=localdate,
                                                   stime=localdate),
                          callback=self.get_total,
                          dont_filter=True,
                          meta={'etime': localdate, 'stime': localdate})

        else:
            print('请输入1或2或3')

    def parse(self, response, **kwargs):
        pass

    @staticmethod
    def parse_gameType(response):
        result = json.loads(response.text)
        if result.get('data').get('games'):
            games = result.get('data').get('games')
            for game in games:
                game_title = game.get('title')
                game_subTitle = game.get('sub_title')
                game_logoUrl = response.urljoin(game.get('logo'))
                item = BilibiliGameTypeItem(game_title=game_title,
                                            game_subTitle=game_subTitle,
                                            game_logoUrl=game_logoUrl)
                item['item_name'] = 'bilibili_gameType'
                yield item

        if result.get('data').get('matchs'):
            matchs = result.get('data').get('matchs')
            for match in matchs:
                match_id = match.get('id')
                match_title = match.get('title')
                match_subTitle = match.get('sub_title')
                match_logoUrl = response.urljoin(match.get('logo'))
                item = BilibiliGameMatchItem(match_id=match_id,
                                             match_title=match_title,
                                             match_subTitle=match_subTitle,
                                             match_logoUrl=match_logoUrl)
                item['item_name'] = 'bilibili_gameMatch'
                yield item

        if result.get('data').get('teams'):
            teams = result.get('data').get('teams')
            for team in teams:
                team_id = team.get('id')
                team_title = team.get('title')
                team_subTitle = team.get('sub_title')
                team_logoUrl = response.urljoin(team.get('logo'))
                item = BilibiliGameTeamItem(team_id=team_id,
                                            team_title=team_title,
                                            team_subTitle=team_subTitle,
                                            team_logoUrl=team_logoUrl)
                item['item_name'] = 'bilibili_gameTeam'
                yield item

    def get_total(self, response):
        result = json.loads(response.text)
        stime = response.meta.get('stime')
        etime = response.meta.get('etime')
        try:
            total = result.get('data').get('page').get('total')
            if total == 0:
                logging.warning("今天没有比赛")
                print('今天没有比赛')
            elif 50 > total > 0:
                yield Request(self.gameTime_url.format(pageNum=1,
                                                       pageSize=total,
                                                       etime=etime,
                                                       stime=stime),
                              callback=self.parse_gameTime)
            else:
                print('比赛场数:', total)
                page = self.get_page(total)
                for i in range(1, page+1):
                    yield Request(self.gameTime_url.format(pageNum=i,
                                                           pageSize=50,
                                                           etime=etime,
                                                           stime=stime),
                                  callback=self.parse_gameTime)
        except exceptions.HTTPError:
            print('http请求错误')

    # 获得分页的页数,向上取整
    @staticmethod
    def get_page(total):
        page = math.ceil(total/50)
        return page

    @staticmethod
    def parse_gameTime(response):
        result = json.loads(response.text)
        if result.get('data').get('list'):
            games = result.get('data').get('list')
            for game in games:
                logo_url = 'https://i0.hdslb.com/'
                # 比赛id
                competitionId = game.get('id')
                #赛事轮次
                game_stage = game.get('game_stage')
                game_stage1 = game.get('game_stage1')
                game_stage2 = game.get('game_stage2')
                #赛事开始和结束时间(时间戳,秒级别)
                sTime = game.get('stime')
                #print('比赛开始时间的时间戳格式:', sTime, type(sTime))
                eTime = game.get('etime')
                #主客场队伍id
                home_teamID = game.get('home_id')
                away_teamID = game.get('away_id')
                #主场/客场队伍名称
                home_teamTitle = game.get('home_team').get('title')
                away_teamTitle = game.get('away_team').get('title')
                #比分
                home_score = game.get('home_score')
                away_score = game.get('away_score')
                #赛季开始/结束时间
                season_stime = game.get('season').get('stime')
                season_etime = game.get('season').get('etime')
                #赛季logoURl
                season_logo = urljoin(logo_url, game.get('season').get('logo'))
                #赛季名称
                season_title = game.get('season').get('title')
                season_subTitle = game.get('season').get('sub_title')
                item = BilibiliGameTimeItem(competitionId=competitionId,
                                            game_stage=game_stage,
                                            game_stage1=game_stage1,
                                            game_stage2=game_stage2,
                                            stime=sTime,
                                            etime=eTime,
                                            home_teamID=home_teamID,
                                            away_teamID=away_teamID,
                                            home_teamTitle=home_teamTitle,
                                            away_teamTitle=away_teamTitle,
                                            home_score=home_score,
                                            away_score=away_score,
                                            season_stime=season_stime,
                                            season_etime=season_etime,
                                            season_logo=season_logo,
                                            season_title=season_title,
                                            season_subTitle=season_subTitle)
                item['item_name'] = 'bilibili_gameTime'
                yield item