package pres.yao.yaogame.host.service.impl;

import org.springframework.stereotype.Service;
import pres.yao.yaogame.host.dao.CompetitionRepository;
import pres.yao.yaogame.host.entity.Competition;
import pres.yao.yaogame.host.entity.meiju.Type;
import pres.yao.yaogame.host.service.CompetitionService;

import javax.annotation.Resource;

/**
 * @Author Fahaxiki
 * @ClassName CompetitionServiceImpl
 * @Description TODO
 * @Date 2021/3/10
 */
@Service
public class CompetitionServiceImpl implements CompetitionService {
	@Resource
	private CompetitionRepository competitionDao;

	/**
	 * @param competitionType
	 * @MethodName: findByCompetitionType
	 * @Param: [competitionType]
	 * @ParamType: [pres.yao.yaogame.host.entity.meiju.Type]
	 * @return: pres.yao.yaogame.host.entity.Competition
	 * @Description: 比赛类型, 电竞/传统体育
	 */
	@Override
	public Competition findByCompetitionType(Type competitionType) {
		return competitionDao.findByCompetitionType(competitionType);
	}

	/**
	 * @param gameStage
	 * @MethodName: findByGameStage
	 * @Param: [gameStage]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.Competition
	 * @Description: 比赛状态, 第几轮
	 */
	@Override
	public Competition findByGameStage(String gameStage) {
		return competitionDao.findByGameStage(gameStage);
	}

	/**
	 * @param leftId
	 * @MethodName: findByLeftId
	 * @Param: [leftId]
	 * @ParamType: [int]
	 * @return: pres.yao.yaogame.host.entity.Competition
	 * @Description: 左边队伍Id
	 */
	@Override
	public Competition findByLeftId(int leftId) {
		return competitionDao.findByLeftId(leftId);
	}

	/**
	 * @param leftName
	 * @MethodName: findByLeftName
	 * @Param: [leftName]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.Competition
	 * @Description: 左边队伍名称
	 */
	@Override
	public Competition findByLeftName(String leftName) {
		return competitionDao.findByLeftName(leftName);
	}

	/**
	 * @param leftScore
	 * @MethodName: findByLeftScore
	 * @Param: [leftScore]
	 * @ParamType: [int]
	 * @return: pres.yao.yaogame.host.entity.Competition
	 * @Description: 左边队伍比分
	 */
	@Override
	public Competition findByLeftScore(int leftScore) {
		return competitionDao.findByLeftScore(leftScore);
	}

	/**
	 * @param rightId
	 * @MethodName: findByRightId
	 * @Param: [rightId]
	 * @ParamType: [int]
	 * @return: pres.yao.yaogame.host.entity.Competition
	 * @Description: 右边队伍Id
	 */
	@Override
	public Competition findByRightId(int rightId) {
		return competitionDao.findByRightId(rightId);
	}

	/**
	 * @param rightName
	 * @MethodName: findByRightName
	 * @Param: [rightName]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.Competition
	 * @Description: 右边队伍名称
	 */
	@Override
	public Competition findByRightName(String rightName) {
		return competitionDao.findByRightName(rightName);
	}

	/**
	 * @param rightScore
	 * @MethodName: findByRightScore
	 * @Param: [rightScore]
	 * @ParamType: [int]
	 * @return: pres.yao.yaogame.host.entity.Competition
	 * @Description: 右边队伍分数
	 */
	@Override
	public Competition findByRightScore(int rightScore) {
		return competitionDao.findByRightScore(rightScore);
	}

	/**
	 * @param startTime
	 * @MethodName: findByStartTime
	 * @Param: [startTime]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.Competition
	 * @Description: 比赛开始时间
	 */
	@Override
	public Competition findByStartTime(String startTime) {
		return competitionDao.findByStartTime(startTime);
	}
}
