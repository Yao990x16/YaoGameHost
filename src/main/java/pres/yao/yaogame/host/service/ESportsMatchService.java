package pres.yao.yaogame.host.service;

import pres.yao.yaogame.host.entity.ESportsMatch;

/**
 * @Author Fahaxiki
 * @ClassName ESportsMatchService
 * @Description TODO
 * @Date 2021/3/10
 */
public interface ESportsMatchService {
	/**
	 * @MethodName: findByMatchId
	 * @Param: [matchId]
	 * @ParamType: [int]
	 * @return: pres.yao.yaogame.host.entity.ESportsMatch
	 * @Description: 电竞比赛id
	 */
	public ESportsMatch findByMatchId(int matchId);

	/**
	 * @MethodName: findByMatchLogoUrl
	 * @Param: [matchLogoUrl]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.ESportsMatch
	 * @Description: 电竞比赛logo
	 */
	public ESportsMatch findByMatchLogoUrl(String matchLogoUrl);

	/**
	 * @MethodName: findByMatchName
	 * @Param: [matchName]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.ESportsMatch
	 * @Description: 电竞比赛名称
	 */
	public ESportsMatch findByMatchName(String matchName);

	/**
	 * @MethodName: findByMatchSubName
	 * @Param: [matchSubName]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.ESportsMatch
	 * @Description: 电竞比赛别称
	 */
	public ESportsMatch findByMatchSubName(String matchSubName);
}