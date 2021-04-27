package pres.yao.yaogame.host.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pres.yao.yaogame.host.entity.Game;

import java.util.List;

/**
 * @Author Fahaxiki
 * @ClassName GameRepository
 * @Description TODO
 * @Date 2021/3/10
 */
public interface GameRepository extends JpaRepository<Game,Integer> {
	Game findByGameName(String gameName);
	List<Game> findByGameType(String gameType);
}
