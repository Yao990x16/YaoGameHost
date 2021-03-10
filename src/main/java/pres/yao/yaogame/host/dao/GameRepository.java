package pres.yao.yaogame.host.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pres.yao.yaogame.host.entity.Game;

/**
 * @Author Fahaxiki
 * @ClassName GameRepository
 * @Description TODO
 * @Date 2021/3/10
 */
public interface GameRepository extends JpaRepository<Game,Integer> {
}
