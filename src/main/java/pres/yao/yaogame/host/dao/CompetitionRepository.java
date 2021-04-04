package pres.yao.yaogame.host.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pres.yao.yaogame.host.entity.Competition;
import pres.yao.yaogame.host.entity.meiju.Type;

/**
 * @Author Fahaxiki
 * @ClassName CompetitionRepository
 * @Description TODO
 * @Date 2021/3/10
 */
public interface CompetitionRepository extends JpaRepository<Competition,Integer> {
	Competition findByCompetitionType(Type competitionType);
	Competition findByGameStage(String gameStage);
	Competition findByLeftId(int leftId);
	Competition findByLeftName(String leftName);
	Competition findByLeftScore(int leftScore);
	Competition findByRightId(int rightId);
	Competition findByRightName(String rightName);
	Competition findByRightScore(int rightScore);
	Competition findByStartTime(String startTime);

}
