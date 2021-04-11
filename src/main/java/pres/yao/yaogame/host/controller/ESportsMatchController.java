package pres.yao.yaogame.host.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.yao.yaogame.host.entity.ESportsMatch;
import pres.yao.yaogame.host.service.ESportsMatchService;

import javax.annotation.Resource;

/**
 * @Author Fahaxiki
 * @ClassName ESportsMatchController
 * @Description TODO
 * @Date 2021/3/10
 */
@RestController
@RequestMapping("/ESportsMatch")
public class ESportsMatchController {
	@Resource
	private ESportsMatchService esportsService;

	@RequestMapping("getByMatchId")
	public ESportsMatch getByMatchId(int matchId) {
		return esportsService.findByMatchId(matchId);
	}

	@RequestMapping(value="getMatchLogoUrl",params="matchId")
	public String getMatchLogoUrl(int matchId){
		ESportsMatch match = esportsService.findByMatchId(matchId);
		return match.getMatchLogoUrl();
	}
	@RequestMapping(value="getMatchLogoUrl",params="matchName")
	public String getMatchLogoUrl(String matchName){
		ESportsMatch match = esportsService.findByMatchName(matchName);
		return match.getMatchLogoUrl();
	}
}
