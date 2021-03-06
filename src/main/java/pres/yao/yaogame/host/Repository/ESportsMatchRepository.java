package pres.yao.yaogame.host.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pres.yao.yaogame.host.entity.ESportsMatch;

/**
 * @Author Fahaxiki
 * @ClassName ESportsMatch
 * @Description TODO
 * @Date 2021/3/10
 */
public interface ESportsMatchRepository extends JpaRepository<ESportsMatch,Integer> {
	ESportsMatch findByMatchId(int matchId);
	ESportsMatch findByMatchLogoUrl(String matchLogoUrl);
	ESportsMatch findByMatchName(String matchName);
	ESportsMatch findByMatchSubName(String matchSubName);
}
