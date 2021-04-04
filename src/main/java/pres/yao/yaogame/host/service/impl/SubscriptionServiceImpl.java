package pres.yao.yaogame.host.service.impl;

import org.springframework.stereotype.Service;
import pres.yao.yaogame.host.dao.SubscriptionRepository;
import pres.yao.yaogame.host.entity.Subscription;
import pres.yao.yaogame.host.service.SubscriptionService;

import javax.annotation.Resource;

/**
 * @Author Fahaxiki
 * @ClassName SubscriptionServiceImpl
 * @Description TODO
 * @Date 2021/3/10
 */
@Service
public class SubscriptionServiceImpl implements SubscriptionService {
	@Resource
	private SubscriptionRepository subscriptionDao;

	/**
	 * @param competitionId
	 * @MethodName: findByCompetitionId
	 * @Param: [competitionId]
	 * @ParamType: [int]
	 * @return: pres.yao.yaogame.host.entity.Subscription
	 * @Description: 根据比赛id查找
	 */
	@Override
	public Subscription findByCompetitionId(int competitionId) {
		return subscriptionDao.findByCompetitionId(competitionId);
	}

	/**
	 * @param userName
	 * @MethodName: findByUserName
	 * @Param: [userName]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.Subscription
	 * @Description: 根据用户id查找, 用户id唯一
	 */
	@Override
	public Subscription findByUserName(String userName) {
		return subscriptionDao.findByUserName(userName);
	}
}
