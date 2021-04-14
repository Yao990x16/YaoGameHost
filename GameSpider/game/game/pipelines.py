# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html


# useful for handling different item types with a single interface
import logging
import datetime
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
        # 判断request_code,如果不是爬取每天的信息存储的时候是插入语句
        if spider.request_code == '1' or spider.request_code == '2':
            result = self.dbpool.runInteraction(self.insert_item, item)
            result.addErrback(self.insert_error)
        if spider.request_code == '3':
            result = self.dbpool.runInteraction(self.update_item, item)
            result.addErrback(self.update_error)
            logging.warning("爬取每天赛事")
        return item

    def insert_item(self, cursor, item):
        # B站电竞信息数据存储
        # 电竞项目存储,包括项目名称/别名/logo的url
        if item["item_name"] == "bilibili_gameType":
            sql = "insert ignore into esports_game(id, game_logo_url, game_name, game_sub_name) " \
                  "VALUES (0,%s,%s,%s)"
            args = (item['game_logoUrl'], item['game_title'], item['game_subTitle'])
            cursor.execute(sql, args)

            sql1 = "insert ignore into game(id, game_name, game_type) VALUES (0,%s,'电子竞技')"
            args1 = (item['game_title'])
            cursor.execute(sql1, args1)

        # 电竞联赛信息存储,包括联赛id/logo的url/名称/别名
        if item["item_name"] == "bilibili_gameMatch":
            sql = "insert ignore into esports_match(id, match_id, match_logo_url, match_name, " \
                  "match_sub_name) VALUES (0,%s,%s,%s,%s)"
            args = (item['match_id'], item['match_logoUrl'], item['match_title'],
                    item['match_subTitle'])
            cursor.execute(sql, args)

            sql1 = "insert ignore into league_match(id, match_name, match_type) VALUES (0,%s," \
                   "'电子竞技')"
            args1 = (item['match_title'])
            cursor.execute(sql1, args1)

        # 电竞队伍信息存储,包括队伍id/logo的url/名称/别名
        if item["item_name"] == "bilibili_gameTeam":
            sql = "insert ignore into esports_team(id, team_id, team_logo_url, tean_name, " \
                  "team_sub_name) VALUES (0,%s,%s,%s,%s)"
            args = (item['team_id'], item['team_logoUrl'], item['team_title'],
                    item['team_subTitle'])
            cursor.execute(sql, args)

            sql1 = "insert ignore into team(id, team_name, team_type) VALUES (0,%s,'电子竞技')"
            args1 = (item['team_title'])
            cursor.execute(sql1, args1)

        #电竞具体比赛的信息,包括比赛id;赛事轮次;主场客场队伍的id/名称/比分;开始时间(时间戳,单位是s)
        if item["item_name"] == "bilibili_gameTime":
            sql = "insert ignore into competition(id, competition_id, competition_type, " \
                  "game_stage, left_id, left_name, left_score, right_id, right_name, right_score," \
                  "start_time) VALUES (0,%s,'电子竞技',%s,%s,%s,%s,%s,%s,%s,%s)"
            args = (item['competitionId'], item['season_title']+','+item['game_stage'],
                    item['home_teamID'], item['home_teamTitle'], item['home_score'],
                    item['away_teamID'], item['away_teamTitle'], item['away_score'],
                    self.timestamp_to_standard_date(item['stime']))
            cursor.execute(sql, args)

        # 腾讯体育传统体育信息存储
        if item["item_name"] == "txty_sportType":
            # 传统体育项目
            sql = "insert ignore into sports_game(id, game_name) VALUES (0,%s)"
            args = (item['sport_title'])
            cursor.execute(sql, args)

            sql1 = "insert ignore into game(id, game_name, game_type) VALUES (0,%s,'传统体育')"
            cursor.execute(sql1, args)

            # 传统体育联赛,包括联赛id/logo的url/名称
            sql2 = "insert ignore into sports_match(id, match_id, match_logo_url, match_name) " \
                   "VALUES (0,%s,%s,%s)"
            args1 = (item['column_ID'], item['icon'], item['name'])
            cursor.execute(sql2, args1)

            sql3 = "insert ignore into league_match(id, match_name, match_type) VALUES (0,%s," \
                   "'传统体育')"
            args2 = (item['name'])
            cursor.execute(sql3, args2)

        # 传统体育的队伍和比赛信息存储
        if item["item_name"] == "txty_sportTime":
            # 传统体育的队伍信息,包括队伍id/logo的url/名称
            sql = "insert ignore into sports_team(id, team_id, team_logo_url, team_name) VALUES (" \
                  "0,%s,%s,%s)"
            args = (item['leftID'], item['leftBadge'], item['leftName'])
            args1 = (item['rightID'], item['rightBadge'], item['rightName'])
            cursor.execute(sql, args)
            cursor.execute(sql, args1)

            sql1 = "insert ignore into team(id, team_name, team_type) VALUES (0,%s,'传统体育')"
            args2 = (item['leftName'])
            args3 = (item['rightName'])
            cursor.execute(sql1, args2)
            cursor.execute(sql1, args3)

            # 传统体育具体比赛的信息,包括赛事id;赛事类型;比赛双方队伍的id/名称/比分;赛事开始时间
            sql2 = "insert ignore into competition(id, competition_id, competition_type, " \
                   "game_stage, left_id, left_name, left_score, right_id, right_name, " \
                   "right_score, start_time) VALUES (0,%s,'传统体育',%s,%s,%s,%s,%s,%s,%s,%s)"
            args4 = (item['competitionId'], item['matchDesc'], item['leftID'], item['leftName'],
                     item['leftGoal'], item['rightID'], item['rightName'], item['rightGoal'],
                     item['startTime'])
            cursor.execute(sql2, args4)

    @staticmethod
    def insert_error(failure):
        print("*"*30)
        logging.warning("有重复数据或插入语句有错误")
        print(failure)
        print("*"*30)

    def update_item(self, cursor, item):
        #电竞具体比赛的信息,包括比赛id;赛事轮次;主场客场队伍的id/名称/比分;开始时间(时间戳,单位是s)
        if item["item_name"] == "bilibili_gameTime":
            sql = "select count(competition_id) from competition where competition_id=%s"
            args = (item['competitionId'])
            cursor.execute(sql, args)
            count = cursor.fetchone()[0]
            # print("*"*50)
            # print(count)
            # print("*"*50)
            if count == 0:
                sql = "insert into competition(id, competition_id, competition_type, game_stage, " \
                      "left_id, left_name, left_score, right_id, right_name, right_score, " \
                      "start_time) VALUES (0,%s,'电子竞技',%s,%s,%s,%s,%s,%s,%s,%s)"
                args = (item['competitionId'], item['season_title']+','+item['game_stage'],
                        item['home_teamID'],
                        item['home_teamTitle'], item['home_score'], item['away_teamID'],
                        item['away_teamTitle'], item['away_score'],
                        self.timestamp_to_standard_date(item['stime']))
                logging.warning("数据库没有有源数据,执行插入语句")
                cursor.execute(sql, args)
            else:
                sql = "update competition set left_score=%s,right_score=%s where competition_id=%s"
                args = (item['home_score'], item['away_score'], item['competitionId'])
                logging.warning("数据库有数据,执行更新语句")
                cursor.execute(sql, args)

        # 传统体育具体比赛的信息,包括赛事id;赛事类型;比赛双方队伍的id/名称/比分;赛事开始时间
        if item["item_name"] == "txty_sportTime":
            sql = "select count(competition_id) from competition where competition_id=%s"
            args = (item['competitionId'])
            cursor.execute(sql, args)
            count = cursor.fetchone()[0]
            if count == 0:
                sql = "insert into competition(id, competition_id, competition_type, " \
                       "game_stage, left_id, left_name, left_score, right_id, right_name, " \
                       "right_score, start_time) VALUES (0,%s,'传统体育',%s,%s,%s,%s,%s,%s,%s,%s)"
                args = (item['competitionId'], item['matchDesc'], item['leftID'], item['leftName'],
                        item['leftGoal'], item['rightID'], item['rightName'], item['rightGoal'],
                        item['startTime'])
                logging.warning("数据库没有有源数据,执行插入语句")
                cursor.execute(sql, args)
            else:
                sql = "update competition set left_score=%s,right_score=%s where competition_id=%s"
                args = (item['leftGoal'], item['rightGoal'], item['competitionId'])
                logging.warning("数据库有数据,执行更新语句")
                cursor.execute(sql, args)

    @staticmethod
    def update_error(failure):
        print("*"*30)
        logging.warning("更新数据失败")
        print(failure)
        print("*"*30)

    # 时间戳转为标准格式输出
    @staticmethod
    def timestamp_to_standard_date(timestamp):
        dateArray = datetime.datetime.fromtimestamp(timestamp)
        resultTime = dateArray.strftime("%Y-%m-%d %H:%M:%S")
        return resultTime