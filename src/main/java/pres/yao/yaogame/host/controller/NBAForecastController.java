package pres.yao.yaogame.host.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.yao.yaogame.host.entity.NBAForecast;
import pres.yao.yaogame.host.service.MatchService;
import pres.yao.yaogame.host.service.NBAForecastService;

import javax.annotation.Resource;

/**
 * @Author Fahaxiki
 * @ClassName NBAForecastController
 * @Description TODO
 * @Date 2021/4/26
 */
@RestController
@RequestMapping("/NBAForecast")
public class NBAForecastController {
	@Resource
	private NBAForecastService NBAForecastService;

	@RequestMapping("/getBySTimeAndTeam")
	public NBAForecast getBySTimeAndTeam(String startTime, String winTeam, String loseTeam){
		return NBAForecastService.findByStartTimeAndWinTeamOrLoseTeam(startTime, winTeam, loseTeam);
	}
}
