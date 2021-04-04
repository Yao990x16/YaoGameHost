package pres.yao.yaogame.host.service;

import pres.yao.yaogame.host.entity.ESportsTeam;

/**
 * @Author Fahaxiki
 * @ClassName ESportsTeamService
 * @Description TODO
 * @Date 2021/3/10
 */
public interface ESportsTeamService {
	/**
	 * @MethodName: findByTeamId
	 * @Param: [teamId]
	 * @ParamType: [int]			
	 * @return: pres.yao.yaogame.host.entity.ESportsTeam
	 * @Description: 电竞队伍id
	 */
	public ESportsTeam findByTeamId(int teamId);
	
	/**
	 * @MethodName: findByTeamLogoUrl
	 * @Param: [teamLogoUrl]
	 * @ParamType: [java.lang.String]			
	 * @return: pres.yao.yaogame.host.entity.ESportsTeam
	 * @Description: 电竞队伍LogoUrl
	 */
	public ESportsTeam findByTeamLogoUrl(String teamLogoUrl);
	
	/**
	 * @MethodName: findByTeamName
	 * @Param: [teamName]
	 * @ParamType: [java.lang.String]			
	 * @return: pres.yao.yaogame.host.entity.ESportsTeam
	 * @Description: 电竞队伍名称
	 */
	public ESportsTeam findByTeamName(String teamName);
	
	/**
	 * @MethodName: findByTeamSubName
	 * @Param: [teamSubName]
	 * @ParamType: [java.lang.String]			
	 * @return: pres.yao.yaogame.host.entity.ESportsTeam
	 * @Description: 电竞队伍别称
	 */
	public ESportsTeam findByTeamSubName(String teamSubName);
}
