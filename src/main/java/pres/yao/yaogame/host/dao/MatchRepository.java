package pres.yao.yaogame.host.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pres.yao.yaogame.host.entity.Match;

/**
 * @Author Fahaxiki
 * @ClassName MatchRepository
 * @Description TODO
 * @Date 2021/3/10
 */
public interface MatchRepository extends JpaRepository<Match,Integer> {
}