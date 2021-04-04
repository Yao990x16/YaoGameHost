package pres.yao.yaogame.host.service;

import pres.yao.yaogame.host.entity.Team;
import pres.yao.yaogame.host.entity.meiju.Type;

import java.util.List;

/**
 * @Author Fahaxiki
 * @ClassName TeamService
 * @Description TODO
 * @Date 2021/3/10
 */
public interface TeamService {
	/**
	 * @MethodName: findAll
	 * @Param: []
	 * @ParamType: []			
	 * @return: java.util.List<pres.yao.yaogame.host.entity.Team>
	 * @Description: 查询全部队伍
	 */
	public List<Team> findAll();
	
	/**
	 * @MethodName: findByTeamName
	 * @Param: [teamName]
	 * @ParamType: [java.lang.String]			
	 * @return: java.lang.String
	 * @Description: 根据队伍名称查询
	 */
	public Team findByTeamName(String teamName);
	
	/**
	 * @MethodName: findByTeamType
	 * @Param: [teamType]
	 * @ParamType: [java.lang.String]			
	 * @return: java.lang.String
	 * @Description: 根据队伍类型,电竞/传统体育队伍来查询
	 */
	public List<Team> findByTeamType(String teamType);
}
