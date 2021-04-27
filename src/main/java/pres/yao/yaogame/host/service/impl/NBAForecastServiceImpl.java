package pres.yao.yaogame.host.service.impl;

import org.springframework.stereotype.Service;
import pres.yao.yaogame.host.Repository.NBAForecastRepository;
import pres.yao.yaogame.host.entity.NBAForecast;
import pres.yao.yaogame.host.service.NBAForecastService;

import javax.annotation.Resource;

/**
 * @Author Fahaxiki
 * @ClassName NBAForecastServiceImpl
 * @Description TODO
 * @Date 2021/4/26
 */
@Service
public class NBAForecastServiceImpl implements NBAForecastService {
	@Resource
	private NBAForecastRepository NBAForecastDao;

	/**
	 * @param startTime
	 * @param winTeam
	 * @param loseTeam
	 * @MethodName: findByStartTimeAndWinTeamOrLoseTeam
	 * @Param: [startTime, winTeam, loseTeam]
	 * @ParamType: [java.lang.String, java.lang.String, java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.NBAForecast
	 * @Description: 根据比赛时间和队伍名查找
	 */
	@Override
	public NBAForecast findByStartTimeAndWinTeamOrLoseTeam(String startTime, String winTeam, String loseTeam) {
		return NBAForecastDao.findByStartTimeAndWinTeamOrLoseTeam(startTime, winTeam, loseTeam);
	}
}
