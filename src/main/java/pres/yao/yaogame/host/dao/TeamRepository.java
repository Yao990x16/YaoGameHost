package pres.yao.yaogame.host.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pres.yao.yaogame.host.entity.Team;

/**
 * @Author Fahaxiki
 * @ClassName TeamRepository
 * @Description TODO
 * @Date 2021/3/10
 */
public interface TeamRepository extends JpaRepository<Team,Integer> {
}
