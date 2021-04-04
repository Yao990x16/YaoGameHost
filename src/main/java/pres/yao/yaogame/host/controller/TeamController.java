package pres.yao.yaogame.host.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.yao.yaogame.host.entity.Team;
import pres.yao.yaogame.host.entity.meiju.Type;
import pres.yao.yaogame.host.service.TeamService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author Fahaxiki
 * @ClassName TeamController
 * @Description TODO
 * @Date 2021/3/10
 */
@RestController
@RequestMapping("/team")
public class TeamController {
	@Resource
	private TeamService teamService;

	/**
	 * @MethodName: getAll
	 * @Param: []
	 * @ParamType: []
	 * @return: java.util.List<pres.yao.yaogame.host.entity.Team>
	 * @Description: 得到所有队伍
	 */
	@RequestMapping("/getAll")
	public List<Team> getAll(){
		return teamService.findAll();
	}

	/**
	 * @MethodName: getByTeamName
	 * @Param: [teamName]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.Team
	 * @Description: 通过队伍名称来获取队伍信息
	 */
	@RequestMapping("/getByTeamName")
	public Team getByTeamName(String teamName){
		return teamService.findByTeamName(teamName);
	}

	/**
	 * @MethodName: getByTeamType
	 * @Param: [teamType]
	 * @ParamType: [java.lang.String]
	 * @return: java.util.List<pres.yao.yaogame.host.entity.Team>
	 * @Description: 通过队伍类型来查询得到队伍信息
	 */
	@RequestMapping("/getByTeamType")
	public List<Team> getByTeamType(String teamType){
		return teamService.findByTeamType(teamType);
	}

	/**
	 * @MethodName: getTypeByTeam
	 * @Param: [team]
	 * @ParamType: [java.lang.String]
	 * @return: java.lang.String
	 * @Description: 通过队伍名称来获取队伍类型
	 */
	@RequestMapping("/getTypeByTeam")
	public String getTypeByTeam(String team){
		return teamService.findByTeamName(team).getTeamType().getType();
	}
}
