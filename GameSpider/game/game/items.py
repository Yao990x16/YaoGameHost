# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html
import scrapy


class BilibiliGameTypeItem(scrapy.Item):
    # #保存图片链接
    # image_urls = scrapy.Field()
    # #下载完成后形成image对象保存到这上面
    # images = scrapy.Field()

    #游戏名称
    game_title = scrapy.Field()
    #游戏别称
    game_subTitle = scrapy.Field()
    #游戏图片链接
    game_logoUrl = scrapy.Field()

class BilibiliGameMatchItem(scrapy.Item):
    #赛事id
    match_id = scrapy.Field()
    #赛事标题
    match_title = scrapy.Field()
    #赛事别称
    match_subTitle = scrapy.Field()
    #赛事logoUrl
    match_logoUrl = scrapy.Field()

class BilibiliGameTeamItem(scrapy.Item):
    #队伍id
    team_id = scrapy.Field()
    #队伍标题
    team_title = scrapy.Field()
    #队伍别称
    team_subTitle = scrapy.Field()
    #队伍logoUrl
    team_logoUrl = scrapy.Field()
