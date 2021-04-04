package pres.yao.yaogame.host.service;

import pres.yao.yaogame.host.entity.ESportsGame;

/**
 * @Author Fahaxiki
 * @ClassName ESportsGameService
 * @Description TODO
 * @Date 2021/3/10
 */
public interface ESportsGameService {
	/**
	 * @MethodName: findByGameLogoUrl
	 * @Param: [gameLogoUrl]
	 * @ParamType: [java.lang.String]			
	 * @return: pres.yao.yaogame.host.entity.ESportsGame
	 * @Description: 电竞比赛种类logo
	 */
	public ESportsGame findByGameLogoUrl(String gameLogoUrl);
	
	/**
	 * @MethodName: findByGameName
	 * @Param: [gameName]
	 * @ParamType: [java.lang.String]			
	 * @return: pres.yao.yaogame.host.entity.ESportsGame
	 * @Description: 电竞比赛种类名称
	 */
	public ESportsGame findByGameName(String gameName);
	
	/**
	 * @MethodName: findByGameSubName
	 * @Param: [gameSubName]
	 * @ParamType: [java.lang.String]			
	 * @return: pres.yao.yaogame.host.entity.ESportsGame
	 * @Description: 电竞比赛种类别称
	 */
	public ESportsGame findByGameSubName(String gameSubName);
}
