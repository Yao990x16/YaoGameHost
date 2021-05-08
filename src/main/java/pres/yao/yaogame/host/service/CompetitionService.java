package pres.yao.yaogame.host.service;

import pres.yao.yaogame.host.entity.Competition;
import pres.yao.yaogame.host.entity.meiju.Type;

import java.util.List;

/**
 * @Author Fahaxiki
 * @ClassName CompetitionService
 * @Description TODO
 * @Date 2021/3/10
 */
public interface CompetitionService {
	/**
	 * @MethodName: getByCompetitionType
	 * @Param: [competitionType]
	 * @ParamType: [java.lang.String]
	 * @return: java.util.List<pres.yao.yaogame.host.entity.Competition>
	 * @Description: 根据赛程类型,电竞/传统体育来获得赛程信息
	 */
	List<Competition> getByCompetitionType(Type competitionType);

	/**
	 * @MethodName: getByGameStage
	 * @Param: [gameStage]
	 * @ParamType: [java.lang.String]
	 * @return: java.util.List<pres.yao.yaogame.host.entity.Competition>
	 * @Description: 根据比赛轮次获取赛程信息
	 */
	List<Competition> getByGameStage(String gameStage);

	/**
	 * @MethodName: getByStartTime
	 * @Param: [startTime]
	 * @ParamType: [java.lang.String]
	 * @return: java.util.List<pres.yao.yaogame.host.entity.Competition>
	 * @Description: 根据开始时间获得赛程信息
	 */
	List<Competition> getByStartTimeLike(String startTime);

	/**
	 * @MethodName: getByCompTypeAndSTime
	 * @Param: [compType, stime]
	 * @ParamType: [java.lang.String, java.lang.String]
	 * @return: java.util.List<pres.yao.yaogame.host.entity.Competition>
	 * @Description: 根据赛程类型和开始时间获取赛程信息
	 */
	List<Competition> getByCompTypeAndSTimeLike(Type compType,String sTime);
	
	/**
	 * @MethodName: getByCompetitionId
	 * @Param: [competitionId]
	 * @ParamType: [java.lang.String]			
	 * @return: pres.yao.yaogame.host.entity.Competition
	 * @Description: 根据比赛id获取比赛
	 */
	Competition getByCompetitionId(String competitionId);
}
