package pres.yao.yaogame.host.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pres.yao.yaogame.host.entity.ESportsTeam;
import pres.yao.yaogame.host.service.ESportsTeamService;

import javax.annotation.Resource;

/**
 * @Author Fahaxiki
 * @ClassName ESportsTeamController
 * @Description TODO
 * @Date 2021/3/10
 */
@RestController
@RequestMapping("/ESportsTeam")
public class ESportsTeamController {
	@Resource
	private ESportsTeamService esportsService;

	@RequestMapping("/getByTeamId")
	public ESportsTeam getByTeamId(int teamId) {
		return esportsService.findByTeamId(teamId);
	}

	@RequestMapping(value="/getTeamLogoUrl",params="teamId")
	public String getTeamLogoUrl(int teamId) {
		ESportsTeam team = esportsService.findByTeamId(teamId);
		return team.getTeamLogoUrl();
	}
	@RequestMapping(value="getTeamLogoUrl",params="teamName")
	public String getTeamLogoUrl(String teamName) {
		ESportsTeam team = esportsService.findByTeamName(teamName);
		return team.getTeamLogoUrl();
	}
}
