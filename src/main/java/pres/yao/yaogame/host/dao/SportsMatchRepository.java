package pres.yao.yaogame.host.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pres.yao.yaogame.host.entity.SportsMatch;

/**
 * @Author Fahaxiki
 * @ClassName SportsMatch
 * @Description TODO
 * @Date 2021/3/10
 */
public interface SportsMatchRepository extends JpaRepository<SportsMatch,Integer> {
}
