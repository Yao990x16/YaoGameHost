package pres.yao.yaogame.host.service;

import pres.yao.yaogame.host.entity.NBAForecast;

/**
 * @Author Fahaxiki
 * @ClassName NBAForecastService
 * @Description TODO
 * @Date 2021/4/26
 */
public interface NBAForecastService {
	/**
	 * @MethodName: findByStartTimeAndWinTeamOrLoseTeam
	 * @Param: [startTime, winTeam, loseTeam]
	 * @ParamType: [java.lang.String, java.lang.String, java.lang.String]			
	 * @return: pres.yao.yaogame.host.entity.NBAForecast
	 * @Description: 根据比赛时间和队伍名查找
	 */
	public NBAForecast findByStartTimeAndWinTeamOrLoseTeam(String startTime,String winTeam,
														   String loseTeam);
}
