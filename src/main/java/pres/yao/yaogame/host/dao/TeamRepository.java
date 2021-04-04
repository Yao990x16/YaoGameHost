package pres.yao.yaogame.host.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pres.yao.yaogame.host.entity.Team;
import pres.yao.yaogame.host.entity.meiju.Type;

import java.util.List;

/**
 * @Author Fahaxiki
 * @ClassName TeamRepository
 * @Description TODO
 * @Date 2021/3/10
 */
public interface TeamRepository extends JpaRepository<Team,Integer> {
	Team findByTeamName(String teamName);
	List<Team> findByTeamType(String teamType);
}
