package pres.yao.yaogame.host.service;

import pres.yao.yaogame.host.entity.Game;
import pres.yao.yaogame.host.entity.meiju.Type;

import java.util.List;

/**
 * @Author Fahaxiki
 * @ClassName GameService
 * @Description TODO
 * @Date 2021/3/10
 */
public interface GameService {
	/**
	 * @MethodName: findByGameName
	 * @Param: [gameName]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.Game
	 * @Description: 游戏/比赛名称
	 */
	public Game findByGameName(String gameName);

	/**
	 * @MethodName: findByGameType
	 * @Param: [gameType]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.Game
	 * @Description: 游戏/比赛类型
	 */
	public List<Game> findByGameType(String gameType);
}
