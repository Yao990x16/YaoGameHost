import scrapy
from scrapy.linkextractors import LinkExtractor
from scrapy.spiders import CrawlSpider, Rule
from scrapy_redis.spiders import RedisCrawlSpider
from scrapy import Request
import json
from ..items import BilibiliGameTypeItem
from ..items import BilibiliGameMatchItem
from ..items import BilibiliGameTeamItem

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
        yield Request(self.gameType_url, callback=self.parse_gameType)

    def parse_gameType(self, response):
        result = json.loads(response.text)
        if result.get('data').get('games'):
            games = result.get('data').get('games')
            for game in games:
                #print(game)
                game_title = game.get('title')
                game_subTitle = game.get('sub_title')
                game_logoUrl = response.urljoin(game.get('logo'))
                item = BilibiliGameTypeItem(game_title=game_title,
                                            game_subTitle=game_subTitle,
                                            game_logoUrl=game_logoUrl)
                #print(game_title, type(game_title))
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

                yield Request(self.gameTime_url.format(pageNum=1,
                                                       pageSize=10,
                                                       etime='2021-02-02',
                                                       stime='2021-01-25'),
                              callback=self.parse_gameTime)

    @staticmethod
    def parse_gameTime(response):
        result = json.loads(response.text)
        if result.get('data').get('list'):
            games = result.get('data').get('list')
            for game in games:
                #print(game)
                #print(game.get('home_team').get('title'))
                game_stage = game.get('game_stage')
                sTime = game.get('stime')
                eTime = game.get('etime')
                home_teamTitle = game.get('home_team').get('title')
                away_teamTitle = game.get('away_team').get('title')
                home_score = game.get('home_score')
                away_score = game.get('away_score')
                season_logo = response.urljoin(game.get('season').get('logo'))
                total = result.get('data').get('page').get('total')
                print("比赛logoUrl:", season_logo)
