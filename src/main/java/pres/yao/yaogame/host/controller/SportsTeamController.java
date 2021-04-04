package pres.yao.yaogame.host.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.yao.yaogame.host.entity.SportsTeam;
import pres.yao.yaogame.host.service.SportsTeamService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Fahaxiki
 * @ClassName SportsTeamController
 * @Description TODO
 * @Date 2021/3/10
 */
@RestController
@RequestMapping("/SportsTeam")
public class SportsTeamController {
	@Resource
	private SportsTeamService sportsTeamService;

	@RequestMapping("/getByTeamId")
	public HashMap<String, String> getByTeamId(int teamId){
		HashMap<String,String> hashMap = new HashMap<>(16);
		SportsTeam team = sportsTeamService.findByTeamId(teamId);
		if(team != null){
			hashMap.put("msg","成功");
			hashMap.put("team",team.getTeamId().toString());
		}else{
			hashMap.put("msg","失败,没有队伍或输错队伍ID");
		}
		return hashMap;
	}

	@RequestMapping("/getByTeamName")
	public HashMap<String,String> getByTeamName(String teamName){
		HashMap<String,String> hashMap = new HashMap<>(16);
		SportsTeam team = sportsTeamService.findByTeamName(teamName);
		if(team != null){
			hashMap.put("msg","成功");
			hashMap.put("team",team.getTeamName());
		}else{
			hashMap.put("msg","失败,没有此队伍或输错队伍名称");
		}
		return hashMap;
	}

}
