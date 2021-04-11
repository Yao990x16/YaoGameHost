package pres.yao.yaogame.host.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import pres.yao.yaogame.host.entity.Subscription;

import java.util.List;

/**
 * @Author Fahaxiki
 * @ClassName SubscriptionRepository
 * @Description TODO
 * @Date 2021/3/10
 */
public interface SubscriptionRepository extends JpaRepository<Subscription,Integer> {
	Subscription findByCompetitionId(int competitionId);
	List<Subscription> findByUserName(String userName);

	@Transactional(rollbackFor = Exception.class)
	@Modifying(clearAutomatically = true)
	void deleteByCompetitionId(int competitionId);
}
