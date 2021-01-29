import scrapy
from scrapy.linkextractors import LinkExtractor
from scrapy.spiders import CrawlSpider, Rule
from scrapy_redis.spiders import RedisCrawlSpider

class BilibiliSpider(CrawlSpider):
    name = 'bilibili'
    allowed_domains = ['bilibili.com']
    start_urls = ['https://www.bilibili.com/v/game/match/schedule?mid=0&gid=0&tid=0&time=1611878400000']
    #redis_key = 'bilibili'
    rules = (
        Rule(LinkExtractor(allow=r'Items/'), callback='parse_item', follow=True),
    )

    def parse_item(self, response):
        item = {}
        #item['domain_id'] = response.xpath('//input[@id="sid"]/@value').get()
        #item['name'] = response.xpath('//div[@id="name"]').get()
        #item['description'] = response.xpath('//div[@id="description"]').get()
        return item
