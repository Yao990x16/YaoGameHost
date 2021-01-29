# Define here the models for your spider middleware
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/spider-middleware.html

from scrapy import signals

# useful for handling different item types with a single interface
from itemadapter import is_item, ItemAdapter
#随机ua
from fake_useragent import UserAgent

class UserAgentDownloadMiddleware(object):
    def process_request(self, request, spider):
        ua = UserAgent()
        request.headers['User-Agent'] = ua.random
