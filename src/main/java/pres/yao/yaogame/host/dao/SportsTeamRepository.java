package pres.yao.yaogame.host.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pres.yao.yaogame.host.entity.SportsTeam;

/**
 * @Author Fahaxiki
 * @ClassName SportsTeamRepository
 * @Description TODO
 * @Date 2021/3/10
 */
public interface SportsTeamRepository extends JpaRepository<SportsTeam,Integer> {
}