import pymysql
import pandas as pd

def db_connect():
	# 打开数据库链接
	return pymysql.connect(host="212.64.83.246",
						   port=3306,
						   user='2021bishe_yao',
						   password='2017013010',
						   db='2021bishe_yao',
						   charset='utf8')


def handle_error(failure):
	if failure:
		print("插入失败")


result = pd.read_csv('20-21Result.csv')

# 将预测结果保存到数据库中
def db_insert():
	db = db_connect()
	cursor = db.cursor()
	for index, row in result.iterrows():
		startTime = row['STime']
		winTeam = row['win']
		loseTeam = row['lose']
		probability = '%.2f' % row['probability']
		sql = "insert into nba_forecast(id, lose_team, probability, start_time, win_team) values(" \
			  			"0,%s,%s,%s,%s)"
		try:
			args = (loseTeam, probability, startTime, winTeam)
			print('插入数据: ', args)
			cursor.execute(sql, args)
			db.commit()
		except Exception as e:
			handle_error(e)
			db.rollback()
	db.close()


if __name__ == "__main__":
	db_insert()