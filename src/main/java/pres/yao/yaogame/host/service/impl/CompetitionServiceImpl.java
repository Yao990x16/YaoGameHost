package pres.yao.yaogame.host.service.impl;

import org.springframework.stereotype.Service;
import pres.yao.yaogame.host.Repository.CompetitionRepository;
import pres.yao.yaogame.host.entity.Competition;
import pres.yao.yaogame.host.entity.meiju.Type;
import pres.yao.yaogame.host.service.CompetitionService;

import javax.annotation.Resource;
import java.util.List;

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
	 * @MethodName: getByCompetitionType
	 * @Param: [competitionType]
	 * @ParamType: [java.lang.String]
	 * @return: java.util.List<pres.yao.yaogame.host.entity.Competition>
	 * @Description: 根据赛程类型, 电竞/传统体育来获得赛程信息
	 */
	@Override
	public List<Competition> getByCompetitionType(Type competitionType) {
		return competitionDao.findByCompetitionType(competitionType);
	}

	/**
	 * @param gameStage
	 * @MethodName: getByGameStage
	 * @Param: [gameStage]
	 * @ParamType: [java.lang.String]
	 * @return: java.util.List<pres.yao.yaogame.host.entity.Competition>
	 * @Description: 根据比赛轮次获取赛程信息
	 */
	@Override
	public List<Competition> getByGameStage(String gameStage) {
		return competitionDao.findByGameStage(gameStage);
	}

	/**
	 * @param startTime
	 * @MethodName: getByStartTime
	 * @Param: [startTime]
	 * @ParamType: [java.lang.String]
	 * @return: java.util.List<pres.yao.yaogame.host.entity.Competition>
	 * @Description: 根据开始时间获得赛程信息
	 */
	@Override
	public List<Competition> getByStartTimeLike(String startTime) {
		return competitionDao.findByStartTimeLike("%"+startTime+"%");
	}

	/**
	 * @param compType
	 * @param sTime
	 * @MethodName: getByCompTypeAndSTime
	 * @Param: [compType, stime]
	 * @ParamType: [java.lang.String, java.lang.String]
	 * @return: java.util.List<pres.yao.yaogame.host.entity.Competition>
	 * @Description: 根据赛程类型和开始时间获取赛程信息
	 */
	@Override
	public List<Competition> getByCompTypeAndSTimeLike(Type compType, String sTime) {
		return competitionDao.findByCompetitionTypeAndStartTimeLike(compType,sTime);
	}

	/**
	 * @param competitionId
	 * @MethodName: getByCompetitionId
	 * @Param: [competitionId]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.Competition
	 * @Description: 根据比赛id获取比赛
	 */
	@Override
	public Competition getByCompetitionId(String competitionId) {
		return competitionDao.findByCompetitionId(competitionId);
	}
}
