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

class BilibiliGameTimeItem(scrapy.Item):
    #赛事轮次
    game_stage = scrapy.Field()
    game_stage1 = scrapy.Field()
    game_stage2 = scrapy.Field()
    #赛事开始和结束时间(时间戳)
    stime = scrapy.Field()
    etime = scrapy.Field()
    #主客场队伍id
    home_teamID = scrapy.Field()
    away_teamID = scrapy.Field()
    #比分
    home_score = scrapy.Field()
    away_score = scrapy.Field()
    #赛季开始/结束时间
    season_stime = scrapy.Field()
    season_etime = scrapy.Field()
    #赛季logoURl
    season_logo = scrapy.Field()
    #赛季名称
    season_title = scrapy.Field()
    season_subTitle = scrapy.Field()

class TxtySportTypeItem(scrapy.Field):
    #体育名称
    sport_title = scrapy.Field()
    #具体赛事ID
    column_ID = scrapy.Field()
    #赛事图标Url
    icon = scrapy.Field()
    #赛事名称
    name = scrapy.Field()
    #赛事比赛数量
    matchNum = scrapy.Field()

class TxtySportTimeItem(scrapy.Field):
    #左边队伍id
    leftID = scrapy.Field()
    #左边队伍名称
    leftName = scrapy.Field()
    #左边队伍logoUrl
    leftBadge = scrapy.Field()
    #左边队伍比分
    leftGoal = scrapy.Field()
    rightID = scrapy.Field()
    rightName = scrapy.Field()
    rightBadge = scrapy.Field()
    rightGoal = scrapy.Field()
    #NBA常规赛等描述
    matchDesc = scrapy.Field()
    #开始时间
    startTime = scrapy.Field()