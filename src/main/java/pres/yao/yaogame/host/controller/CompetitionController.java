package pres.yao.yaogame.host.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.yao.yaogame.host.entity.Competition;
import pres.yao.yaogame.host.entity.CompetitionAS;
import pres.yao.yaogame.host.entity.ESportsTeam;
import pres.yao.yaogame.host.entity.SportsTeam;
import pres.yao.yaogame.host.entity.meiju.Type;
import pres.yao.yaogame.host.service.CompetitionService;
import pres.yao.yaogame.host.service.ESportsTeamService;
import pres.yao.yaogame.host.service.SportsTeamService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import pres.yao.yaogame.host.utils.GSONCompetitionAS;
/**
 * @Author Fahaxiki
 * @ClassName CompetitionController
 * @Description TODO
 * @Date 2021/3/10
 */
@RestController
@RequestMapping("/competition")
public class CompetitionController {
	@Resource
	private CompetitionService competitionService;
	@Resource
	private ESportsTeamService esportsTeamService;
	@Resource
	private SportsTeamService sportsTeamService;


	@RequestMapping("/getByCompType")
	public List<Competition> getByCompType(Type compType) {
		return competitionService.getByCompetitionType(compType);
	}

	@RequestMapping("/getByCompTypeAndSTimeLike")
	public List<CompetitionAS> getByCompTypeAndSTimeLike(Type compType, String sTime){
		 List<Competition> list = competitionService.getByCompTypeAndSTimeLike(compType,
				 "%"+sTime+"%");
		return GSONCompetitionAS.getCompetitionAS(list, esportsTeamService, sportsTeamService);
	}

	@RequestMapping("/getByGameStage")
	public List<Competition> getByGameStage(String gameStage){
		return competitionService.getByGameStage(gameStage);
	}

	@RequestMapping("/getBySTime")
	public List<Competition> getBySTimeLike(String sTime){
		return competitionService.getByStartTimeLike("%"+sTime+"%");
	}
}
