# Define here the models for your spider middleware
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/spider-middleware.html

from scrapy import signals

# useful for handling different item types with a single interface
from itemadapter import is_item, ItemAdapter
#随机ua
from fake_useragent import UserAgent
#selenium相关
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from scrapy.http.response.html import HtmlResponse
#行为链
from selenium.webdriver.common.action_chains import ActionChains
from time import sleep

class UserAgentDownloadMiddleware(object):
    @staticmethod
    def process_request(request, spider):
        ua = UserAgent()
        request.headers['User-Agent'] = ua.random

class BilibiliSeleniumMiddleware(object):
    # def __init__(self):
    #     self.driver = webdriver.Edge(executable_path="D:\webdriver\MicrosoftWebDriver.exe")

    def process_request(self, request, spider):
        if spider.name == 'bilibili':
           driver = webdriver.Edge(executable_path="D:\webdriver\MicrosoftWebDriver.exe")
           driver.get(request.url)
           text = driver.page_source
           driver.quit()
           # print("request:url", request.url)
           # print("source:", text)
           return HtmlResponse(request.url, body=text, encoding='utf-8', request=request)