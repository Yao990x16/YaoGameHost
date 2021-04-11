package pres.yao.yaogame.host.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pres.yao.yaogame.host.entity.SportsMatch;
import pres.yao.yaogame.host.service.SportsMatchService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Fahaxiki
 * @ClassName SportsMatchController
 * @Description TODO
 * @Date 2021/3/10
 */
@RestController
@RequestMapping("/SportsMatch")
public class SportsMatchController {
	@Resource
	private SportsMatchService sportsMatchService;

	@RequestMapping("/getByMatchId")
	public SportsMatch getByMatchId(int matchId) {
		return sportsMatchService.findByMatchId(matchId);
	}

	@RequestMapping(value="/getSportsMatchUrl", params = "matchId")
	public String getSportsMatchUrl(int matchId) {
		SportsMatch sportsMatch = sportsMatchService.findByMatchId(matchId);
		return sportsMatch.getMatchLogoUrl();
	}
	@RequestMapping(value="/getSportsMatchUrl", params="matchName")
	public String getSportsMatchUrl(String matchName) {
		SportsMatch sportsMatch = sportsMatchService.findByMatchName(matchName);
		return  sportsMatch.getMatchLogoUrl();
	}

	@RequestMapping("/getByMatchName")
	public SportsMatch getByMatchName(String matchName) {
		return sportsMatchService.findByMatchName(matchName);
	}
}
