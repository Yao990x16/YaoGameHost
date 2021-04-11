package pres.yao.yaogame.host.service.impl;

import org.springframework.stereotype.Service;
import pres.yao.yaogame.host.dao.SubscriptionRepository;
import pres.yao.yaogame.host.entity.Subscription;
import pres.yao.yaogame.host.service.SubscriptionService;

import javax.annotation.Resource;
import java.util.List;

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
	public List<Subscription> findByUserName(String userName) {
		return subscriptionDao.findByUserName(userName);
	}

	/**
	 * @param competitionId
	 * @MethodName: deleteByCompetitionId
	 * @Param: [CompetitionId]
	 * @ParamType: [int]
	 * @return: void
	 * @Description: 根据赛程id删除用户订阅表中对应的订阅信息
	 */
	@Override
	public void deleteByCompetitionId(int competitionId) {
		subscriptionDao.deleteByCompetitionId(competitionId);
	}

	/**
	 * @param subscription
	 * @MethodName: subscribe
	 * @Param: [subscription]
	 * @ParamType: [pres.yao.yaogame.host.entity.Subscription]
	 * @return: void
	 * @Description: 用户订阅赛程, 添加到订阅表中
	 */
	@Override
	public void subscribe(Subscription subscription) {
		subscriptionDao.saveAndFlush(subscription);
	}
}
