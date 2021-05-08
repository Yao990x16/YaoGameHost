package pres.yao.yaogame.host.service;

import pres.yao.yaogame.host.entity.Subscription;

import java.util.List;

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
	public Subscription findByCompetitionId(String competitionId);
	
	/**
	 * @MethodName: findByUserName
	 * @Param: [userName]
	 * @ParamType: [java.lang.String]			
	 * @return: pres.yao.yaogame.host.entity.Subscription
	 * @Description: 根据用户名称查找,用户名称唯一
	 */
	public List<Subscription> findByUserName(String userName);
	
	/**
	 * @MethodName: deleteByCompetitionId
	 * @Param: [CompetitionId]
	 * @ParamType: [int]			
	 * @return: void
	 * @Description: 根据赛程id删除用户订阅表中对应的订阅信息
	 */
	public void deleteByCompetitionId(String competitionId);

	/**
	 * @MethodName: subscribe
	 * @Param: [subscription]
	 * @ParamType: [pres.yao.yaogame.host.entity.Subscription]			
	 * @return: void
	 * @Description: 用户订阅赛程,添加到订阅表中
	 */
	public void subscribe(Subscription subscription);
	
	/**
	 * @MethodName: findByCompetitionIdAndUserName
	 * @Param: [competitionId, userName]
	 * @ParamType: [java.lang.String, java.lang.String]			
	 * @return: pres.yao.yaogame.host.entity.Subscription
	 * @Description: 根据用户名和比赛id查找
	 */
	public Subscription findByCompetitionIdAndUserName(String competitionId, String userName);
	
	/**
	 * @MethodName: deleteByCompetitionIdAndAndUserName
	 * @Param: [competitionId, userName]
	 * @ParamType: [java.lang.String, java.lang.String]			
	 * @return: void
	 * @Description: 根据比赛ID和用户名删除
	 */
	public void deleteByCompetitionIdAndAndUserName(String competitionId, String userName);
}
