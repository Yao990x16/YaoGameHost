package pres.yao.yaogame.host.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.yao.yaogame.host.entity.Match;
import pres.yao.yaogame.host.service.MatchService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Fahaxiki
 * @ClassName MatchController
 * @Description TODO
 * @Date 2021/3/10
 */
@RestController
@RequestMapping("/match")
public class MatchController {
	@Resource
	private MatchService matchService;

	@RequestMapping("/getByMatchType")
	public List<Match> getByMatchType(String matchType) {
		return matchService.findByMatchType(matchType);
	}

	@RequestMapping("/getByMatchName")
	public Match getByMatchName(String matchName) {
		return matchService.findByMatchName(matchName);
	}
}
