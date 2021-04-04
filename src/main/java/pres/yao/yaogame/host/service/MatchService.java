package pres.yao.yaogame.host.service;

import pres.yao.yaogame.host.entity.Match;
import pres.yao.yaogame.host.entity.meiju.Type;

import java.util.List;

/**
 * @Author Fahaxiki
 * @ClassName MatchService
 * @Description TODO
 * @Date 2021/3/10
 */
public interface MatchService {
	/**
	 * @MethodName: findByMatchName
	 * @Param: [matchName]
	 * @ParamType: [java.lang.String]			
	 * @return: pres.yao.yaogame.host.entity.Match
	 * @Description: 竞赛名称
	 */
	public Match findByMatchName(String matchName);
	
	/**
	 * @MethodName: findByMatchType
	 * @Param: [matchType]
	 * @ParamType: [java.lang.String]			
	 * @return: pres.yao.yaogame.host.entity.Match
	 * @Description: 竞赛类型,电竞/传统体育
	 */
	public List<Match> findByMatchType(String matchType);
}
