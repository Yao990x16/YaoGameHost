package pres.yao.yaogame.host.service.impl;

import org.springframework.stereotype.Service;
import pres.yao.yaogame.host.dao.MatchRepository;
import pres.yao.yaogame.host.entity.Match;
import pres.yao.yaogame.host.entity.meiju.Type;
import pres.yao.yaogame.host.service.MatchService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Fahaxiki
 * @ClassName MatchServiceImpl
 * @Description TODO
 * @Date 2021/3/10
 */
@Service
public class MatchServiceImpl implements MatchService {
	@Resource
	private MatchRepository matchDao;

	/**
	 * @param matchName
	 * @MethodName: findByMatchName
	 * @Param: [matchName]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.Match
	 * @Description: 竞赛名称
	 */
	@Override
	public Match findByMatchName(String matchName) {
		return matchDao.findByMatchName(matchName);
	}

	/**
	 * @param matchType
	 * @MethodName: findByMatchType
	 * @Param: [matchType]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.Match
	 * @Description: 竞赛类型, 电竞/传统体育
	 */
	@Override
	public List<Match> findByMatchType(String matchType) {
		return matchDao.findByMatchType(matchType);
	}
}
