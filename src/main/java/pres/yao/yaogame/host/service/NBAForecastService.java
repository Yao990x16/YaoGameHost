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
	 * @Description: 根据比赛时间和赢的队伍名查找
	 */
	public NBAForecast findByStartTimeLikeAndWinTeamLike(String startTime, String winTeam);

	/**
	 * @MethodName: findByStartTimeLikeAndLoseTeamLike
	 * @Param: [startTime, winTeam]
	 * @ParamType: [java.lang.String, java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.NBAForecast
	 * @Description: 根据比赛时间和输的队伍查找
	 */
	public NBAForecast findByStartTimeLikeAndLoseTeamLike(String startTime, String loseTeam);
}
