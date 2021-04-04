package pres.yao.yaogame.host.service.impl;

import org.springframework.stereotype.Service;
import pres.yao.yaogame.host.dao.SportsMatchRepository;
import pres.yao.yaogame.host.entity.SportsMatch;
import pres.yao.yaogame.host.service.SportsMatchService;

import javax.annotation.Resource;

/**
 * @Author Fahaxiki
 * @ClassName SportsMatchServiceImpl
 * @Description TODO
 * @Date 2021/3/10
 */
@Service
public class SportsMatchServiceImpl implements SportsMatchService {
	@Resource
	private SportsMatchRepository sportsMatchDao;

	/**
	 * @param matchId
	 * @MethodName: findByMatchId
	 * @Param: [matchId]
	 * @ParamType: [int]
	 * @return: pres.yao.yaogame.host.entity.SportsMatch
	 * @Description: 根据id查找竞赛
	 */
	@Override
	public SportsMatch findByMatchId(int matchId) {
		return sportsMatchDao.findByMatchId(matchId);
	}

	/**
	 * @param matchLogoUrl
	 * @MethodName: findByMatchLogoUrl
	 * @Param: [matchLogoUrl]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.SportsMatch
	 * @Description: 竞赛图标url
	 */
	@Override
	public SportsMatch findByMatchLogoUrl(String matchLogoUrl) {
		return sportsMatchDao.findByMatchLogoUrl(matchLogoUrl);
	}

	/**
	 * @param matchName
	 * @MethodName: findByMatchName
	 * @Param: [matchName]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.SportsMatch
	 * @Description: 竞赛名称
	 */
	@Override
	public SportsMatch findByMatchName(String matchName) {
		return sportsMatchDao.findByMatchName(matchName);
	}
}
