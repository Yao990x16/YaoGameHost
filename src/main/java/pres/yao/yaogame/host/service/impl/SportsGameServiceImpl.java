package pres.yao.yaogame.host.service.impl;

import org.springframework.stereotype.Service;
import pres.yao.yaogame.host.dao.SportsGameRepository;
import pres.yao.yaogame.host.entity.SportsGame;
import pres.yao.yaogame.host.service.SportsGameService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Fahaxiki
 * @ClassName SportsGameServiceImpl
 * @Description TODO
 * @Date 2021/3/10
 */
@Service
public class SportsGameServiceImpl implements SportsGameService {
	@Resource
	private SportsGameRepository sportsGameDao;

	/**
	 * @param gameName
	 * @MethodName: findByGameName
	 * @Param: [gameName]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.SportsGame
	 * @Description: 体育名称
	 */
	@Override
	public SportsGame findByGameName(String gameName) {
		return sportsGameDao.findByGameName(gameName);
	}

	/**
	 * @MethodName: getGameNames
	 * @Param: []
	 * @ParamType: []
	 * @return: java.util.List<java.lang.String>
	 * @Description: 获取所有比赛名称, 比如篮球/足球/网球
	 */
	@Override
	public List<SportsGame> getAllGames() {
		return sportsGameDao.findAll();
	}
}
