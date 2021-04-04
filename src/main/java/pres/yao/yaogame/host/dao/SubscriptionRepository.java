package pres.yao.yaogame.host.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pres.yao.yaogame.host.entity.Subscription;

/**
 * @Author Fahaxiki
 * @ClassName SubscriptionRepository
 * @Description TODO
 * @Date 2021/3/10
 */
public interface SubscriptionRepository extends JpaRepository<Subscription,Integer> {
	Subscription findByCompetitionId(int competitionId);
	Subscription findByUserName(String userName);
}
