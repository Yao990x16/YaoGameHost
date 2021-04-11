package pres.yao.yaogame.host.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.yao.yaogame.host.entity.Competition;
import pres.yao.yaogame.host.service.CompetitionService;

import javax.annotation.Resource;
import java.util.List;

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

	@RequestMapping("/getByCompType")
	public List<Competition> getByCompType(String compType) {
		return competitionService.getByCompetitionType(compType);
	}

	@RequestMapping("/getByCompTypeAndSTime")
	public List<Competition> getByCompTypeAndSTime(String compType, String sTime){
		return competitionService.getByCompTypeAndSTime(compType, sTime);
	}

	@RequestMapping("/getByGameStage")
	public List<Competition> getByGameStage(String gameStage){
		return competitionService.getByGameStage(gameStage);
	}

	@RequestMapping("/getBySTime")
	public List<Competition> getBySTime(String sTime){
		return competitionService.getByStartTime(sTime);
	}
}
