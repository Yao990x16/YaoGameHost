import scrapy

class TxtySpider(scrapy.Spider):
    name = 'txty'
    allowed_domains = ['qq.com']
    #start_urls = ['http://qq.com/']

    def parse(self, response, **kwargs):
        pass

    @staticmethod
    def parse_item(self, response):
        item = {}
        return item
