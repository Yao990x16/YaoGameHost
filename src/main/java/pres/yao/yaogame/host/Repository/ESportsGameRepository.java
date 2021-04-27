package pres.yao.yaogame.host.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pres.yao.yaogame.host.entity.ESportsGame;

/**
 * @Author Fahaxiki
 * @ClassName ESportsGameRepository
 * @Description TODO
 * @Date 2021/3/10
 */
public interface ESportsGameRepository extends JpaRepository<ESportsGame,Integer> {
	ESportsGame findByGameLogoUrl(String gameLogoUrl);
	ESportsGame findByGameName(String gameName);
	ESportsGame findByGameSubName(String gameSubName);
}
