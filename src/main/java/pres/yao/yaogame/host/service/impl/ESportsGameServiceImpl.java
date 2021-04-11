package pres.yao.yaogame.host.service.impl;

import org.springframework.stereotype.Service;
import pres.yao.yaogame.host.dao.ESportsGameRepository;
import pres.yao.yaogame.host.entity.ESportsGame;
import pres.yao.yaogame.host.service.ESportsGameService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Fahaxiki
 * @ClassName ESportsGameServiceImpl
 * @Description TODO
 * @Date 2021/3/10
 */
@Service
public class ESportsGameServiceImpl implements ESportsGameService {
	@Resource
	private ESportsGameRepository eSportsGameDao;

	/**
	 * @param gameLogoUrl
	 * @MethodName: findByGameLogoUrl
	 * @Param: [gameLogoUrl]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.ESportsGame
	 * @Description: 电竞比赛种类logo
	 */
	@Override
	public ESportsGame findByGameLogoUrl(String gameLogoUrl) {
		return eSportsGameDao.findByGameLogoUrl(gameLogoUrl);
	}

	/**
	 * @param gameName
	 * @MethodName: findByGameName
	 * @Param: [gameName]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.ESportsGame
	 * @Description: 电竞比赛种类名称
	 */
	@Override
	public ESportsGame findByGameName(String gameName) {
		return eSportsGameDao.findByGameName(gameName);
	}

	/**
	 * @param gameSubName
	 * @MethodName: findByGameSubName
	 * @Param: [gameSubName]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.ESportsGame
	 * @Description: 电竞比赛种类别称
	 */
	@Override
	public ESportsGame findByGameSubName(String gameSubName) {
		return eSportsGameDao.findByGameSubName(gameSubName);
	}

	/**
	 * @MethodName: getGameNames
	 * @Param: []
	 * @ParamType: []
	 * @return: java.util.List<pres.yao.yaogame.host.entity.ESportsGame>
	 * @Description: 获取所有电竞比赛项目名称
	 */
	@Override
	public List<ESportsGame> getAllGames() {
		return eSportsGameDao.findAll();
	}
}
