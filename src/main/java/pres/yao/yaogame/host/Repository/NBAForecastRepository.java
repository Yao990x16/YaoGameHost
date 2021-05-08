package pres.yao.yaogame.host.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pres.yao.yaogame.host.entity.NBAForecast;

/**
 * @Author Fahaxiki
 * @ClassName NBAForecatRepository
 * @Description TODO
 * @Date 2021/4/26
 */
public interface NBAForecastRepository extends JpaRepository<NBAForecast,Integer> {
	NBAForecast findByStartTimeLikeAndWinTeam(String startTime, String winTeam);
	NBAForecast findByStartTimeLikeAndLoseTeam(String startTime, String loseTeam);
}
