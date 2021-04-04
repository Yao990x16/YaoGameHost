package pres.yao.yaogame.host.service;

import pres.yao.yaogame.host.entity.SportsGame;

/**
 * @Author Fahaxiki
 * @ClassName SportsService
 * @Description TODO
 * @Date 2021/3/10
 */
public interface SportsGameService {
	/**
	 * @MethodName: findByGameName
	 * @Param: [gameName]
	 * @ParamType: [java.lang.String]			
	 * @return: pres.yao.yaogame.host.entity.SportsGame
	 * @Description: 体育名称
	 */
	public SportsGame findByGameName(String gameName);
}
