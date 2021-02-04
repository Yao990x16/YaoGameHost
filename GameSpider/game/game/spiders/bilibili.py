from urllib.parse import urljoin

import scrapy
from scrapy.linkextractors import LinkExtractor
from scrapy.spiders import CrawlSpider, Rule
from scrapy_redis.spiders import RedisCrawlSpider
from scrapy import Request
import json
import time,datetime
from ..items import BilibiliGameTypeItem
from ..items import BilibiliGameMatchItem
from ..items import BilibiliGameTeamItem
from ..items import BilibiliGameTimeItem

class BilibiliSpider(scrapy.Spider):
    name = 'bilibili'
    allowed_domains = ['bilibili.com']
    #start_urls = ['https://www.bilibili.com/v/game/match/schedule']
    #redis_key = 'bilibili'
    # rules = (
    #     Rule(LinkExtractor(allow=r'.+'),
    #          callback='parse_item', follow=False),
    # )
    #游戏类型
    gameType_url = 'https://api.bilibili.com/x/esports/matchs/filter?mid=0&gid=0&tid=0'
    #比赛日期
    gameTime_url = 'https://api.bilibili.com/x/esports/matchs/list?mid=0&gid=0&tid=0&pn={pageNum}' \
                   '&ps={pageSize}&etime={etime}&stime={stime}'

    def start_requests(self):
        request_code = input('爬取游戏类型/赛事分类/队伍信息输入1;爬取比赛信息输入2;爬取当天实时比分输入3:')
        if request_code == '1':
            yield Request(self.gameType_url, callback=self.parse_gameType)
        if request_code == '2':
            #爬取原始数据
            stime_str = input('请输入开始日期(xxxx-xx-xx):')
            etime_str = input('请输入结束日期(xxxx-xx-xx):')
            yield Request(self.gameTime_url.format(pageNum=1,
                                                   pageSize=10,
                                                   etime=etime_str,
                                                   stime=stime_str),
                          callback=self.get_total,
                          meta={'etime': etime_str, 'stime': stime_str})
        if request_code == "3":
            #%Y四位数年份表示,%m月份,%d月内中的一天
            #获取当天日期,爬取当天比分变化
            localdate = time.strftime('%Y-%m-%d', time.localtime())
            print('今天的日期:', localdate, type(localdate))
            yield Request(self.gameTime_url.format(pageNum=1,
                                                   pageSize=10,
                                                   etime=localdate,
                                                   stime=localdate),
                          callback=self.get_total,
                          meta={'etime': localdate, 'stime': localdate})
        else:
            print('请输入1或2或3')

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
                yield item

    def get_total(self, response):
        result = json.loads(response.text)
        stime = response.meta.get('stime')
        etime = response.meta.get('etime')
        try:
            total = result.get('data').get('page').get('total')
            print('数据总量:', total)
        except AttributeError:
            print('没有获取到数据总量')
        else:
            yield Request(self.gameTime_url.format(pageNum=1,
                                                   pageSize=total,
                                                   etime=etime,
                                                   stime=stime),
                          callback=self.parse_gameTime)

    @staticmethod
    def parse_gameTime(response):
        result = json.loads(response.text)
        if result.get('data').get('list'):
            games = result.get('data').get('list')
            for game in games:
                logo_url = 'https://i0.hdslb.com/'
                #赛事轮次
                game_stage = game.get('game_stage')
                game_stage1 = game.get('game_stage1')
                game_stage2 = game.get('game_stage2')
                #赛事开始和结束时间(时间戳)
                sTime = game.get('stime')
                print('比赛开始时间的时间戳格式:', sTime, type(sTime))
                eTime = game.get('etime')
                #主客场队伍id
                home_teamID = game.get('home_id')
                away_teamID = game.get('away_id')
                #主场/客场队伍名称
                #home_teamTitle = game.get('home_team').get('title')
                #away_teamTitle = game.get('away_team').get('title')
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
                item = BilibiliGameTimeItem(game_stage=game_stage,
                                            game_stage1=game_stage1,
                                            game_stage2=game_stage2,
                                            stime=sTime,
                                            etime=eTime,
                                            home_teamID=home_teamID,
                                            away_teamID=away_teamID,
                                            home_score=home_score,
                                            away_score=away_score,
                                            season_stime=season_stime,
                                            season_etime=season_etime,
                                            season_logo=season_logo,
                                            season_title=season_title,
                                            season_subTitle=season_subTitle)
                yield item
