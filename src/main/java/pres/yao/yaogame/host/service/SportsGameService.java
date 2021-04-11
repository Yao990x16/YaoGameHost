package pres.yao.yaogame.host.service;

import pres.yao.yaogame.host.entity.SportsGame;

import java.util.List;

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

	/**
	 * @MethodName: getGameNames
	 * @Param: []
	 * @ParamType: []
	 * @return: java.util.List<java.lang.String>
	 * @Description: 获取所有比赛名称,比如篮球/足球/网球
	 */
	public List<SportsGame> getAllGames();
}
