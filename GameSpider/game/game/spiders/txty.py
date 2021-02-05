import time
import json
from scrapy import Request
import scrapy
from ..items import TxtySportTypeItem
from ..items import TxtySportTimeItem
class TxtySpider(scrapy.Spider):
    name = 'txty'
    allowed_domains = ['qq.com']
    #体育类型
    sports_typeUrl = 'https://matchweb.sports.qq.com/matchUnion/cateColumns?from=pc'
    #赛事信息
    sports_timeUrl = 'https://matchweb.sports.qq.com/matchUnion/list?today={today}&startTime={startTime}&endTime={endTime}&columnId=100000&index=3&isInit=true'

    def parse(self, response, **kwargs):
        pass

    def start_requests(self):
        localdate = time.strftime('%Y-%m-%d', time.localtime())
        request_code = input('爬取体育类型/赛事分类/队伍信息输入1;爬取比赛信息输入2;爬取当天实时比分输入3: ')
        if request_code == '1':
            yield Request(self.sports_typeUrl, callback=self.parse_sportsType)
        elif request_code == '2':
            #爬取原始数据
            stime_str = input('请输入开始日期(xxxx-xx-xx):')
            etime_str = input('请输入结束日期(xxxx-xx-xx):')
            yield Request(self.sports_timeUrl.format(today=localdate,
                                                     startTime=stime_str,
                                                     endTime=etime_str),
                          callback=self.parse_sportsTime)
        elif request_code == '3':
            #%Y四位数年份表示,%m月份,%d月内中的一天
            #获取当天日期,爬取当天比分变化
            print('今天的日期:', localdate)
            yield Request(self.sports_timeUrl.format(today=localdate,
                                                     startTime=localdate,
                                                     endTime=localdate),
                          callback=self.parse_sportsTime)
        else:
            print('请输入1或2或3')

    @staticmethod
    def parse_sportsType(response):
        result = json.loads(response.text)
        if result.get('data'):
            sports = result['data']
            for sport in sports:
                sport_title = sport.get('title')
                columns = sport.get('columns')
                for column in columns:
                    column_ID = column.get('columnId')
                    icon = column.get('icon')
                    name = column.get('name')
                    matchNum = column.get('matchNum')
                    item = TxtySportTypeItem(sport_title=sport_title,
                                             column_ID=column_ID,
                                             icon=icon,
                                             name=name,
                                             matchNum=matchNum)
                    yield item

    @staticmethod
    def parse_sportsTime(response):
        result = json.loads(response.text)
        if result.get('data'):
            datas = result.get('data')
            for data in datas:
                matchs = datas[data]
                for match in matchs:
                    leftID = match.get('leftID')
                    leftName = match.get('leftName')
                    leftBadge = match.get('leftBadge')
                    leftGoal = match.get('leftGoal')
                    rightID = match.get('rightID')
                    rightName = match.get('rightName')
                    rightBadge = match.get('rightBadge')
                    rightGoal = match.get('rightGoal')
                    matchDesc = match.get('matchDesc')
                    startTime = match.get('startTime')
                    item = TxtySportTimeItem(leftID=leftID,
                                             leftName=leftName,
                                             leftBadge=leftBadge,
                                             leftGoal=leftGoal,
                                             rightID=rightID,
                                             rightName=rightName,
                                             rightBadge=rightBadge,
                                             rightGoal=rightGoal,
                                             matchDesc=matchDesc,
                                             startTime=startTime)
                    yield item