package pres.yao.yaogame.host.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pres.yao.yaogame.host.entity.Match;

import java.util.List;

/**
 * @Author Fahaxiki
 * @ClassName MatchRepository
 * @Description TODO
 * @Date 2021/3/10
 */
public interface MatchRepository extends JpaRepository<Match,Integer> {
	Match findByMatchName(String matchName);
	List<Match> findByMatchType(String matchType);
}
