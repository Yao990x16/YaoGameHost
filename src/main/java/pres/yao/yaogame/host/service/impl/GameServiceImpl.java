package pres.yao.yaogame.host.service.impl;

import org.springframework.stereotype.Service;
import pres.yao.yaogame.host.dao.GameRepository;
import pres.yao.yaogame.host.entity.Game;
import pres.yao.yaogame.host.entity.meiju.Type;
import pres.yao.yaogame.host.service.GameService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Fahaxiki
 * @ClassName GameServiceImpl
 * @Description TODO
 * @Date 2021/3/10
 */
@Service
public class GameServiceImpl implements GameService {
	@Resource
	private GameRepository gameDao;

	/**
	 * @param gameName
	 * @MethodName: findByGameName
	 * @Param: [gameName]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.Game
	 * @Description: 游戏/比赛名称
	 */
	@Override
	public Game findByGameName(String gameName) {
		return gameDao.findByGameName(gameName);
	}

	/**
	 * @param gameType
	 * @MethodName: findByGameType
	 * @Param: [gameType]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.Game
	 * @Description: 游戏/比赛类型
	 */
	@Override
	public List<Game> findByGameType(String gameType) {
		return gameDao.findByGameType(gameType);
	}

	/**
	 * @MethodName: getAllGames
	 * @Param: []
	 * @ParamType: []
	 * @return: java.util.List<pres.yao.yaogame.host.entity.Game>
	 * @Description: 获取所有比赛
	 */
	@Override
	public List<Game> getAllGames() {
		return gameDao.findAll();
	}
}
