package pres.yao.yaogame.host.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pres.yao.yaogame.host.entity.Competition;

import java.util.List;

/**
 * @Author Fahaxiki
 * @ClassName CompetitionRepository
 * @Description TODO
 * @Date 2021/3/10
 */
public interface CompetitionRepository extends JpaRepository<Competition,Integer> {
	List<Competition> findByCompetitionType(String competitionType);

	List<Competition> findByGameStage(String gameStage);

	List<Competition> findByStartTime(String startTime);

	List<Competition> findByCompetitionTypeAndStartTime(String competitionType, String startTime);
}
