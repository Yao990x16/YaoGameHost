package pres.yao.yaogame.host.service;

import pres.yao.yaogame.host.entity.Competition;
import pres.yao.yaogame.host.entity.meiju.Type;

/**
 * @Author Fahaxiki
 * @ClassName CompetitionService
 * @Description TODO
 * @Date 2021/3/10
 */
public interface CompetitionService {
	/**
	 * @MethodName: findByCompetitionType
	 * @Param: [competitionType]
	 * @ParamType: [pres.yao.yaogame.host.entity.meiju.Type]			
	 * @return: pres.yao.yaogame.host.entity.Competition
	 * @Description: 比赛类型,电竞/传统体育
	 */
	public Competition findByCompetitionType(Type competitionType);
	
	/**
	 * @MethodName: findByGameStage
	 * @Param: [gameStage]
	 * @ParamType: [java.lang.String]			
	 * @return: pres.yao.yaogame.host.entity.Competition
	 * @Description: 比赛状态,第几轮
	 */
	public Competition findByGameStage(String gameStage);
	
	/**
	 * @MethodName: findByLeftId
	 * @Param: [leftId]
	 * @ParamType: [int]			
	 * @return: pres.yao.yaogame.host.entity.Competition
	 * @Description: 左边队伍Id
	 */
	public Competition findByLeftId(int leftId);
	/**
	 * @MethodName: findByLeftName
	 * @Param: [leftName]
	 * @ParamType: [java.lang.String]			
	 * @return: pres.yao.yaogame.host.entity.Competition
	 * @Description: 左边队伍名称
	 */
	public Competition findByLeftName(String leftName);
	
	/**
	 * @MethodName: findByLeftScore
	 * @Param: [leftScore]
	 * @ParamType: [int]			
	 * @return: pres.yao.yaogame.host.entity.Competition
	 * @Description: 左边队伍比分
	 */
	public Competition findByLeftScore(int leftScore);
	
	/**
	 * @MethodName: findByRightId
	 * @Param: [rightId]
	 * @ParamType: [int]			
	 * @return: pres.yao.yaogame.host.entity.Competition
	 * @Description: 右边队伍Id
	 */
	public Competition findByRightId(int rightId);
	
	/**
	 * @MethodName: findByRightName
	 * @Param: [rightName]
	 * @ParamType: [java.lang.String]			
	 * @return: pres.yao.yaogame.host.entity.Competition
	 * @Description: 右边队伍名称
	 */
	public Competition findByRightName(String rightName);
	
	/**
	 * @MethodName: findByRightScore
	 * @Param: [rightScore]
	 * @ParamType: [int]			
	 * @return: pres.yao.yaogame.host.entity.Competition
	 * @Description: 右边队伍分数
	 */
	public Competition findByRightScore(int rightScore);
	
	/**
	 * @MethodName: findByStartTime
	 * @Param: [startTime]
	 * @ParamType: [java.lang.String]			
	 * @return: pres.yao.yaogame.host.entity.Competition
	 * @Description: 比赛开始时间
	 */
	public Competition findByStartTime(String startTime);
}
