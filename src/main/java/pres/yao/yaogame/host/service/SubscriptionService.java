package pres.yao.yaogame.host.service;

import pres.yao.yaogame.host.entity.Subscription;

/**
 * @Author Fahaxiki
 * @ClassName SubscriptionService
 * @Description TODO
 * @Date 2021/3/10
 */
public interface SubscriptionService {
	/**
	 * @MethodName: findByCompetitionId
	 * @Param: [competitionId]
	 * @ParamType: [int]			
	 * @return: pres.yao.yaogame.host.entity.Subscription
	 * @Description: 根据比赛id查找
	 */
	public Subscription findByCompetitionId(int competitionId);
	
	/**
	 * @MethodName: findByUserName
	 * @Param: [userName]
	 * @ParamType: [java.lang.String]			
	 * @return: pres.yao.yaogame.host.entity.Subscription
	 * @Description: 根据用户id查找,用户id唯一
	 */
	public Subscription findByUserName(String userName);
}
