package pres.yao.yaogame.host.service;

import org.springframework.data.jpa.domain.Specification;
import pres.yao.yaogame.host.entity.SportsTeam;

/**
 * @Author Fahaxiki
 * @ClassName SportsTeamService
 * @Description TODO
 * @Date 2021/3/10
 */
public interface SportsTeamService {
	/**
	 * @MethodName: findByTeamId
	 * @Param: [teamId]
	 * @ParamType: [java.lang.String]			
	 * @return: pres.yao.yaogame.host.entity.SportsTeam
	 * @Description: 根据teamId查找
	 */
	public SportsTeam findByTeamId(int teamId);
	
	/**
	 * @MethodName: findByTeamLogoUrl
	 * @Param: [teamLogoUrl]
	 * @ParamType: [java.lang.String]			
	 * @return: pres.yao.yaogame.host.entity.SportsTeam
	 * @Description: 
	 */
	public SportsTeam findByTeamLogoUrl(String teamLogoUrl);

	/**
	 * @MethodName: findByTeamName
	 * @Param: [teamName]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.SportsTeam
	 * @Description: 根据队伍名称查找
	 */
	public SportsTeam findByTeamName(String teamName);
}
