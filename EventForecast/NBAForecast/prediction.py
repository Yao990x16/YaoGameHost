import csv
import sys
from time import time
import pandas as pd
import math
import random
import numpy as np
# 标准化操作
from sklearn.preprocessing import scale
# 将数据集分成测试集和训练集
from sklearn.model_selection import train_test_split
# 交叉验证
from sklearn.model_selection import cross_val_score
# 超参数调参模块
from sklearn.model_selection import GridSearchCV
# F1得分
from sklearn.metrics import f1_score
# XGBoost模型
import xgboost as xgb
#支持向量机分类模型
from sklearn.svm import SVC
# 逻辑回归模型
from sklearn.linear_model import LogisticRegression
# 模型评估
from sklearn.metrics import make_scorer
# 模型的保存与加载模块
import joblib

class Logger(object):
	def __init__(self, fileN='Default.log'):
		self.terminal = sys.stdout
		self.log = open(fileN, 'a')

	def write(self, message):
		"""print实际相当于sys.stdout.write"""
		self.terminal.write(message)
		self.log.write(message)

	def flush(self):
		pass


sys.stdout = Logger('train.txt')  # 调用print时相当于Logger().write()
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


#构造数据集和特征
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
	X_train, X_test, y_train, y_test = train_test_split(np.nan_to_num(X), y, test_size=0.3,
														random_state=2, stratify=y)
	# X_train:训练集特征值,X_test:测试集特征值,y_train:训练集目标值,y_test:测试集目标值
	return X_train, X_test, y_train, y_test

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


# noinspection PyShadowingNames
def train_classifier(clf, X_train, y_train, params):
	""" 训练模型 """
	# 记录训练时长
	f1_scorer = make_scorer(f1_score, pos_label=1)
	start = time()
	# 使用 grdi search 自动调参
	grid_obj = GridSearchCV(clf,
							scoring=f1_scorer,
							param_grid=params,
							cv=5)
	grid_obj = grid_obj.fit(X_train, y_train)
	# 得到最佳的模型
	best_clf = grid_obj.best_estimator_
	#clf.fit(X_train, y_train)
	end = time()
	print("训练最佳超参: ", grid_obj.best_params_)
	print("训练时间 {:.4f} 秒".format(end - start))
	return best_clf

def predict_labels(clf, features, target):
	""" 使用模型进行预测 """
	# 记录预测时长
	start = time()
	y_pred = clf.predict(features)
	end = time()
	print("预测时间 in {:.4f} 秒".format(end - start))
	return f1_score(target, y_pred, pos_label=1), sum(target == y_pred) / float(len(y_pred))


# noinspection PyShadowingNames
def train_predict(clf, X_train, y_train, X_test, y_test, params):
	""" 训练并评估模型 """
	# Indicate the classifier and the training set size
	print("训练 {} 模型，样本数量 {}。".format(clf.__class__.__name__, len(X_train)))
	# 训练模型
	clf = train_classifier(clf, X_train, y_train, params)
	# 在测试集上评估模型
	f1, acc = predict_labels(clf, X_train, y_train)
	train_cross = cross_val_score(clf, X_train, y_train, cv=10, scoring='accuracy',
								  n_jobs=-1).mean()
	print("训练集上的 F1 分数和准确率为: {:.4f} , {:.4f}。".format(f1, acc))
	# 利用10折交叉验证计算训练正确率
	print("训练集上的交叉验证计算训练正确率为: ", train_cross)

	f1, acc = predict_labels(clf, X_test, y_test)
	test_cross = cross_val_score(clf, X_test, y_test, cv=10, scoring='accuracy',
								 n_jobs=-1).mean()
	print("测试集上的 F1 分数和准确率为: {:.4f} , {:.4f}。".format(f1, acc))
	print("测试集上的交叉验证计算训练正确率为: ", test_cross)


if __name__ == '__main__':
	Mstat = pd.read_csv(folder + '/19-20Miscellaneous_Stats.csv')
	Ostat = pd.read_csv(folder + '/19-20Opponent_Per_Game_Stats.csv')
	Tstat = pd.read_csv(folder + '/19-20Team_Per_Game_Stats.csv')
	team_stats = initialize_data(Mstat, Ostat, Tstat)
	result_data = pd.read_csv(folder + '/19-20_result.csv')
	X_train, X_test, y_train, y_test = build_dataSet(result_data)
	# 训练网络模型
	print("Fitting on %d game samples.." % len(X_train))
	# 分别建立并初始化三个模型,设置模型对应的要自动调参的参数
	# random_state: 随机种子数,max_iter: 最大迭代次数
	clf_Log = LogisticRegression(max_iter=10000, random_state=42)
	# penalty：正则化参数,solver：损失函数的优化方法,class_weight:用于标示分类模型中各种类型的权重
	Log_params = {'penalty': ['l2'], 'solver': ['liblinear', 'lbfgs', 'sag'],
				  'class_weight': ['balanced']}
	# kernel: 核函数,gamma: rbf,poly 和sigmoid的核函数参数。默认是auto，则会选择1/n_features
	clf_SVC = SVC(random_state=42, probability=True)
	SVC_params = {'C': [1, 10], 'gamma': [0.5, 1, 1.5, 2, 5], 'class_weight': ['balanced']}
	# seed: 随机种子
	clf_XGB = xgb.XGBClassifier(seed=42, use_label_encoder=False, eval_metric='error')
	# n_estimatores: 总共迭代的次数，即决策树的个数,max_depth：树的深度，默认值为6，典型值3-10
	# min_child_weight：值越大，越容易欠拟合；值越小，越容易过拟合（值较大时，避免模型学习到局部的特殊样本）
	# colsample_bytree: [default=1] 在建立树时对特征采样的比例
	# subsample: [default=1]用于训练模型的子样本占整个样本集合的比例
	XGB_params = {'n_estimators': [90, 100, 110],
				  'max_depth': [5, 6, 7],
				  'min_child_weight': [1, 5, 10],
				  'gamma': [0.5, 1, 1.5, 2, 5]}

	# 训练模型
	# train_predict(clf_Log, X_train, y_train, X_test, y_test, Log_params)
	# print('')
	# train_predict(clf_SVC, X_train, y_train, X_test, y_test, SVC_params)
	# print('')
	# train_predict(clf_XGB, X_train, y_train, X_test, y_test, XGB_params)
	# print('')

	# 选取相对较好的模型进行训练
	best_model = LogisticRegression(max_iter=10000, random_state=42, class_weight='balanced',
									penalty='l2', solver='lbfgs')
	best_model.fit(X_train, y_train)
	# 利用训练好的model在20-21年的比赛中进行预测
	print('Predicting on new schedule..')
	schedule2021 = pd.read_csv(folder + '/20-21_schedule.csv')
	result = []
	for index, row in schedule2021.iterrows():
		team1 = row['VTeam']
		team2 = row['HTeam']
		STime = row['STime']
		pred = predict_winner(team1, team2, best_model)
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
	pd.read_csv('20-21Result.csv', header=0)