import csv
import datetime

import pytz
import time

import pandas as pd

October_2019 = pd.read_csv('data/October_2019.csv')
November_2019 = pd.read_csv('data/November_2019.csv')
December_2019 = pd.read_csv('data/December_2019.csv')
January_2020 = pd.read_csv('data/January_2020.csv')
February_2020 = pd.read_csv('data/February_2020.csv')
March_2020 = pd.read_csv('data/March_2020.csv')
July_2020 = pd.read_csv('data/July_2020.csv')
August_2020 = pd.read_csv('data/August_2020.csv')
September_2020 = pd.read_csv('data/September_2020.csv')
October_2020 = pd.read_csv('data/October_2020.csv')
December_2020 = pd.read_csv('data/December_2020.csv')
results = [October_2019, November_2019, December_2019, January_2020, February_2020, March_2020,
		  July_2020, August_2020, September_2020, October_2020, December_2020]

January_2021 = pd.read_csv('data/January_2021.csv')
February_2021 = pd.read_csv('data/February_2021.csv')
March_2021 = pd.read_csv('data/March_2021.csv')
April_2021 = pd.read_csv('data/April_2021.csv')
May_2021 = pd.read_csv('data/May_2021.csv')
schedules = [January_2021, February_2021, March_2021, April_2021, May_2021]

def get19_20result():
	# 新文件的列
	result_row = []
	for result in results:
		for index, row in result.iterrows():
			# 客场队伍
			VTeam = row['Visitor/Neutral']
			# 客场队伍得分
			VPst = row[3]

			# 主场队伍
			HTeam = row['Home/Neutral']
			# 主场队伍得分
			HPst = row[5]

			if VPst > HPst:
				WTeam = VTeam
				LTeam = HTeam
				WLoc = 'V'
				result_row.append([WTeam, LTeam, WLoc])
			else:
				WTeam = HTeam
				LTeam = VTeam
				WLoc = 'H'
				result_row.append([WTeam, LTeam, WLoc])
	# newline确保不会有多余的换行符
	with open('data/19-20_result.csv', 'w', encoding='utf-8', newline='') as f:
		writer = csv.writer(f)
		writer.writerow(['WTeam', 'LTeam', 'WLoc'])
		writer.writerows(result_row)
		print('19-20年比赛结果整合完毕')

def get20_21schedule():
	# 时间表的列
	schedule_row = []
	for schedule in schedules:
		for index, row in schedule.iterrows():
			date = row['Date']
			if type(row['Start (ET)']) is not float:
				sET = row['Start (ET)']+'m'
				# print('SET类型：', type(sET))
			else:
				sET = row['Start (ET)']
			try:
				STime = date+' '+sET
				STime = time.strptime(STime, "%a %b %d %Y %I:%M%p")
				STime = time.strftime("%Y-%m-%d %H:%M", STime)
				print('EST:', STime)
				STime = timezone_translate(STime)
				print('Asia/Shanghai:', STime)
				VTeam = row['Visitor/Neutral']
				HTeam = row['Home/Neutral']
				schedule_row.append([STime, VTeam, HTeam])
			except TypeError:
				print('时间出现错误: ', date, sET)

	with open('data/20-21_schedule.csv', 'w', encoding='utf-8', newline='') as f:
		writer = csv.writer(f)
		writer.writerow(['STime', 'VTeam', 'HTeam'])
		writer.writerows(schedule_row)
		print('20-21年比赛安排整合完毕')

def timezone_translate(est_time_str):
	# 标注时间的时区
	est_tz = pytz.timezone('EST')
	# 北京时区
	local_tz = pytz.timezone('Asia/Shanghai')
	# 所需要的时间打印格式
	local_format = "%Y-%m-%d %H:%M"
	est_dt = datetime.datetime.strptime(est_time_str, local_format)
	# 将原有时区换成我们需要的
	local_dt = est_dt.replace(tzinfo=est_tz).astimezone(local_tz)
	# 偏移量
	offset = datetime.timedelta(hours=-1)
	time_str = (local_dt + offset).strftime(local_format)
	return time_str


if __name__ == '__main__':
	# get19_20result()
	get20_21schedule()