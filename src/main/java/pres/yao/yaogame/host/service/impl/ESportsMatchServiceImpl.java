package pres.yao.yaogame.host.service.impl;

import org.springframework.stereotype.Service;
import pres.yao.yaogame.host.dao.ESportsMatchRepository;
import pres.yao.yaogame.host.entity.ESportsMatch;
import pres.yao.yaogame.host.service.ESportsMatchService;

import javax.annotation.Resource;

/**
 * @Author Fahaxiki
 * @ClassName ESportsMatchServiceImpl
 * @Description TODO
 * @Date 2021/3/10
 */
@Service
public class ESportsMatchServiceImpl implements ESportsMatchService {
	@Resource
	private ESportsMatchRepository eSportsMatchDao;

	/**
	 * @param matchId
	 * @MethodName: findByMatchId
	 * @Param: [matchId]
	 * @ParamType: [int]
	 * @return: pres.yao.yaogame.host.entity.ESportsMatch
	 * @Description: 电竞比赛id
	 */
	@Override
	public ESportsMatch findByMatchId(int matchId) {
		return eSportsMatchDao.findByMatchId(matchId);
	}

	/**
	 * @param matchLogoUrl
	 * @MethodName: findByMatchLogoUrl
	 * @Param: [matchLogoUrl]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.ESportsMatch
	 * @Description: 电竞比赛logo
	 */
	@Override
	public ESportsMatch findByMatchLogoUrl(String matchLogoUrl) {
		return eSportsMatchDao.findByMatchLogoUrl(matchLogoUrl);
	}

	/**
	 * @param matchName
	 * @MethodName: findByMatchName
	 * @Param: [matchName]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.ESportsMatch
	 * @Description: 电竞比赛名称
	 */
	@Override
	public ESportsMatch findByMatchName(String matchName) {
		return eSportsMatchDao.findByMatchName(matchName);
	}

	/**
	 * @param matchSubName
	 * @MethodName: findByMatchSubName
	 * @Param: [matchSubName]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.ESportsMatch
	 * @Description: 电竞比赛别称
	 */
	@Override
	public ESportsMatch findByMatchSubName(String matchSubName) {
		return eSportsMatchDao.findByMatchSubName(matchSubName);
	}
}
