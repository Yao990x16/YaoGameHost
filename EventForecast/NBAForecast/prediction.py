import pandas as pd
import math
import csv
import random
import numpy as np
from sklearn import linear_model
from sklearn.model_selection import cross_val_score

# 设置回归训练所用到的参数变量
# 每支队伍没有elo等级分时,赋予基础elo等级分
base_elo = 1600
team_elos = {}
team_stats = {}
X = []
y = []
# 存放数据的目录
folder = 'data'
#  队伍英文名称对应的中文名称
team_name = {'Dallas Mavericks': '独行侠', 'Milwaukee Bucks': '雄鹿', 'Portland Trail Blazers': '开拓者',
			 'Houston Rockets': '火箭', 'Los Angeles Clippers': '快船', 'New Orleans Pelicans': '鹈鹕',
			 'Phoenix Suns': '太阳', 'Washington Wizards': '奇才', 'Memphis Grizzlies': '灰熊',
			 'Boston Celtics': '凯尔特人', 'Miami Heat': '热火', 'Denver Nuggets': '掘金',
			 'Toronto Raptors': '猛龙', 'San Antonio Spurs': '马刺', 'Philadelphia 76ers': '76人',
			 'Los Angeles Lakers': '湖人', 'Brooklyn Nets': '篮网', 'Utah Jazz': '爵士',
			 'Indiana Pacers': '步行者', 'Oklahoma City Thunder': '雷霆', 'Sacramento Kings': '国王',
			 'Orlando Magic': '魔术', 'Atlanta Hawks': '老鹰', 'Minnesota Timberwolves': '森林狼',
			 'Detroit Pistons': '活塞', 'New York Knicks': '尼克斯', 'Cleveland Cavaliers': '骑士',
			 'Chicago Bulls': '公牛', 'Golden State Warriors': '勇士', 'Charlotte Hornets': '黄蜂'}
# 根据每支队伍的Miscellaneous Stats(综合统计数据)
# Opponent Per Game Stats(所遇到的对手平均每场比赛统计信息)
# Team Per Game Stats(每支队伍平均每场比赛的表现统计)
# 统计数据csv文件进行初始化
# noinspection PyShadowingNames
def initialize_data(Mstat, Ostat, Tstat):
	new_Mstat = Mstat.drop(['Rk', 'Arena'], axis=1)
	new_Ostat = Ostat.drop(['Rk', 'G', 'MP'], axis=1)
	new_Tstat = Tstat.drop(['Rk', 'G', 'MP'], axis=1)

	team_stats1 = pd.merge(new_Mstat, new_Ostat, how='left', on='Team')
	team_stats1 = pd.merge(team_stats1, new_Tstat, how='left', on='Team')
	return team_stats1.set_index('Team', inplace=False, drop=True)


# noinspection PyBroadException
def get_elo(team):
	try:
		return team_elos[team]
	except:
		# 当最初没有elo时，给每个队伍最初赋base_elo
		team_elos[team] = base_elo
		return team_elos[team]

# 计算每个球队的elo值
def calc_elo(win_team, lose_team):
	winner_rank = get_elo(win_team)
	loser_rank = get_elo(lose_team)

	rank_diff = winner_rank - loser_rank
	exp = (rank_diff * -1) / 400
	odds = 1 / (1 + math.pow(10, exp))
	# 根据rank级别修改K值
	if winner_rank < 2100:
		k = 32
	elif 2100 <= winner_rank < 2400:
		k = 24
	else:
		k = 16

	# 更新 rank 数值
	new_winner_rank = round(winner_rank + (k * (1 - odds)))
	new_loser_rank = round(loser_rank + (k * (0 - odds)))
	return new_winner_rank, new_loser_rank


# noinspection PyShadowingNames
def build_dataSet(all_data):
	print("Building data set..")
	X = []
	skip = 0
	for index, row in all_data.iterrows():

		Wteam = row['WTeam']
		Lteam = row['LTeam']

		#获取最初的elo或是每个队伍最初的elo值
		team1_elo = get_elo(Wteam)
		team2_elo = get_elo(Lteam)

		# 给主场比赛的队伍加上100的elo值
		if row['WLoc'] == 'H':
			team1_elo += 100
		else:
			team2_elo += 100

		# 把elo当为评价每个队伍的第一个特征值
		team1_features = [team1_elo]
		team2_features = [team2_elo]

		# 添加我们从basketball reference.com获得的每个队伍的统计信息
		for key, value in team_stats.loc[Wteam].iteritems():
			team1_features.append(value)
		for key, value in team_stats.loc[Lteam].iteritems():
			team2_features.append(value)

		# 将两支队伍的特征值随机的分配在每场比赛数据的左右两侧
		# 并将对应的0/1赋给y值
		if random.random() > 0.5:
			X.append(team1_features + team2_features)
			y.append(0)
		else:
			X.append(team2_features + team1_features)
			y.append(1)

		if skip == 0:
			print('X', X)
			skip = 1

		# 根据这场比赛的数据更新队伍的elo值
		new_winner_rank, new_loser_rank = calc_elo(Wteam, Lteam)
		team_elos[Wteam] = new_winner_rank
		team_elos[Lteam] = new_loser_rank

	return np.nan_to_num(X), y

def predict_winner(team_1, team_2, train_model):
	features = [get_elo(team_1)]

	# team 1，客场队伍
	for key, value in team_stats.loc[team_1].iteritems():
		features.append(value)

	# team 2，主场队伍
	features.append(get_elo(team_2) + 100)
	for key, value in team_stats.loc[team_2].iteritems():
		features.append(value)

	features = np.nan_to_num(features)
	return train_model.predict_proba([features])


if __name__ == '__main__':
	Mstat = pd.read_csv(folder + '/19-20Miscellaneous_Stats.csv')
	Ostat = pd.read_csv(folder + '/19-20Opponent_Per_Game_Stats.csv')
	Tstat = pd.read_csv(folder + '/19-20Team_Per_Game_Stats.csv')
	team_stats = initialize_data(Mstat, Ostat, Tstat)
	result_data = pd.read_csv(folder + '/19-20_result.csv')
	X, y = build_dataSet(result_data)
	# 训练网络模型
	print("Fitting on %d game samples.." % len(X))
	model = linear_model.LogisticRegression(max_iter=1000)
	model.fit(X, y)
	# 利用10折交叉验证计算训练正确率
	print("Doing cross-validation..")
	print(cross_val_score(model, X, y, cv=10, scoring='accuracy', n_jobs=-1).mean())
	# 利用训练好的model在16-17年的比赛中进行预测
	print('Predicting on new schedule..')
	schedule2021 = pd.read_csv(folder + '/20-21_schedule.csv')
	result = []
	for index, row in schedule2021.iterrows():
		team1 = row['VTeam']
		team2 = row['HTeam']
		STime = row['STime']
		pred = predict_winner(team1, team2, model)
		prob = pred[0][0]
		if prob > 0.5:
			winner = team_name[team1]
			loser = team_name[team2]
			result.append([STime, winner, loser, prob])
		else:
			winner = team_name[team2]
			loser = team_name[team1]
			result.append([STime, winner, loser, 1 - prob])
	with open('20-21Result.csv', 'w', encoding='utf-8', newline='') as f:
		writer = csv.writer(f)
		writer.writerow(['STime', 'win', 'lose', 'probability'])
		writer.writerows(result)
		print('预测结果生成csv..')
	# pd.read_csv('20-21Result.csv', header=0)
