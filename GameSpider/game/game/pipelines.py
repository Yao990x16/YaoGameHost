# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html


# useful for handling different item types with a single interface
from itemadapter import ItemAdapter
import pymysql
from twisted.enterprise import adbapi

class MySQLPipeline:
    def __init__(self, mysql_config):
        self.dbpool = adbapi.ConnectionPool(
            mysql_config['DRIVER'],
            host=mysql_config['HOST'],
            port=mysql_config['PORT'],
            user=mysql_config['USER'],
            password=mysql_config['PASSWORD'],
            db=mysql_config['DATABASE'],
            charset='utf8'
        )

    @classmethod
    def from_crawler(cls, crawler):
        #重写了此方法,以后创建对象的时候,就会调用这个方法来获取pipeline对象
        mysql_config = crawler.settings['MYSQL_CONFIG']
        # cls代表当前pipeline
        return cls(mysql_config)

    def process_item(self, item, spider):
        result = self.dbpool.runInteraction(self.insert_item, item)
        result.addErrback(self.insert_error)
        return item

    def insert_item(self, cursor, item):
        if item["item_name"] == "bilibili_gameType":
            sql = "insert into esports_game(id, game_logo_url, game_name, game_sub_name) " \
                  "VALUES (0,%s,%s,%s)"
            args = (item['game_logoUrl'], item['game_title'], item['game_subTitle'])
            cursor.execute(sql, args)

    def insert_error(self, failure):
        print("*"*30)
        print(failure)
        print("*"*30)



