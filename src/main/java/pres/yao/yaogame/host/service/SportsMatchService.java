package pres.yao.yaogame.host.service;

import pres.yao.yaogame.host.entity.SportsMatch;

/**
 * @Author Fahaxiki
 * @ClassName SportsMatchService
 * @Description TODO
 * @Date 2021/3/10
 */
public interface SportsMatchService {
	/**
	 * @MethodName: findByMatchId
	 * @Param: [matchId]
	 * @ParamType: [int]			
	 * @return: pres.yao.yaogame.host.entity.SportsMatch
	 * @Description: 根据id查找竞赛
	 */
	public SportsMatch findByMatchId(int matchId);
	
	/**
	 * @MethodName: findByMatchLogoUrl
	 * @Param: [matchLogoUrl]
	 * @ParamType: [java.lang.String]			
	 * @return: pres.yao.yaogame.host.entity.SportsMatch
	 * @Description: 竞赛图标url
	 */
	public SportsMatch findByMatchLogoUrl(String matchLogoUrl);
	
	/**
	 * @MethodName: findByMatchName
	 * @Param: [matchName]
	 * @ParamType: [java.lang.String]			
	 * @return: pres.yao.yaogame.host.entity.SportsMatch
	 * @Description: 竞赛名称
	 */
	public SportsMatch findByMatchName(String matchName);
}
