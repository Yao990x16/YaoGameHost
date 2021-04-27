package pres.yao.yaogame.host.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pres.yao.yaogame.host.entity.SportsGame;

/**
 * @Author Fahaxiki
 * @ClassName SportsGameRepository
 * @Description TODO
 * @Date 2021/3/10
 */
public interface SportsGameRepository extends JpaRepository<SportsGame,Integer> {
	SportsGame findByGameName(String gameName);
}
