package pres.yao.yaogame.host.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pres.yao.yaogame.host.entity.Competition;
import pres.yao.yaogame.host.entity.meiju.Type;

import java.util.List;

/**
 * @Author Fahaxiki
 * @ClassName CompetitionRepository
 * @Description TODO
 * @Date 2021/3/10
 */
public interface CompetitionRepository extends JpaRepository<Competition,Integer> {
	List<Competition> findByCompetitionType(Type competitionType);

	List<Competition> findByGameStage(String gameStage);

	List<Competition> findByStartTimeLike(String startTime);

	List<Competition> findByCompetitionTypeAndStartTimeLike(Type competitionType,
														   String startTime);
}
