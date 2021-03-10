package pres.yao.yaogame.host.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pres.yao.yaogame.host.entity.ESportsGame;

/**
 * @Author Fahaxiki
 * @ClassName ESportsGameRepository
 * @Description TODO
 * @Date 2021/3/10
 */
public interface ESportsGameRepository extends JpaRepository<ESportsGame,Integer> {
}
