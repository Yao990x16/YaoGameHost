package pres.yao.yaogame.host.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pres.yao.yaogame.host.entity.ESportsTeam;

/**
 * @Author Fahaxiki
 * @ClassName ESportsTeamRepository
 * @Description TODO
 * @Date 2021/3/10
 */
public interface ESportsTeamRepository extends JpaRepository<ESportsTeam,Integer> {
	ESportsTeam findByTeamId(int teamId);
	ESportsTeam findByTeamLogoUrl(String teamLogoUrl);
	ESportsTeam findByTeamName(String teamName);
	ESportsTeam findByTeamSubName(String teamSubName);
}
