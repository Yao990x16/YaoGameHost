import scrapy
from scrapy.linkextractors import LinkExtractor
from scrapy.spiders import CrawlSpider, Rule
from scrapy_redis.spiders import RedisCrawlSpider
from scrapy import Request
import json
from..items import BilibiliGameTypeItem

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
        #gameImg_urls =response.xpath("//div[contains(text(),'游戏筛选')]/../../div[                                 @class='filter-content']//li//img/@src").getall()
        # game_titles = response.xpath("//div[contains(text(),'游戏筛选')]/../../div["
        #                              "@class='filter-content']//li//div/@title").getall()
        result = json.loads(response.text)
        if result.get('data').get('games'):
            games = result.get('data').get('games')
            for game in games:
               # print(game)
                game_title = game.get('title')
                game_subTitle = game.get('sub_title')
                game_logoUrl = response.urljoin(game.get('logo'))
                item =BilibiliGameTypeItem(game_title=game_title, game_subTitle=game_subTitle,
                                                gameLogo_urls=game_logoUrl)
                #print(game_title, type(game_title))
                yield item
                yield Request(self.gameTime_url.format(pageNum=1, pageSize=10,
                                                       etime='2021-02-02', stime='2021-01-25'),
                              callback=self.parse_gameTime)
    def parse_gameTime(self, response):
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
                total = result.get('data').get('page').get('total')
                print("数据总条数:", total)
                size = result.get('data').get('page').get('size')
                print("每页数据大小:", size)
                page_num = total/size
                print(home_teamTitle, home_score, ":", away_score, away_teamTitle)
