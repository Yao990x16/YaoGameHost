# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class GameItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    pass
class BilibiliGameTypeItem(scrapy.Item):
    # #保存图片链接
    # image_urls = scrapy.Field()
    # #下载完成后形成image对象保存到这上面
    # images = scrapy.Field()

    #游戏名称
    game_title = scrapy.Field()
    #游戏简称
    game_subTitle = scrapy.Field()
    #游戏图片链接
    gameLogo_urls = scrapy.Field()
