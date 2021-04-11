package pres.yao.yaogame.host.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import pres.yao.yaogame.host.entity.User;

import java.util.Optional;

/**
 * @Author Fahaxiki
 * @ClassName UserRepository
 * @Description TODO
 * @Date 2021/3/10
 */
public interface UserRepository extends JpaRepository<User,Integer> {
	User findByUsername(String username);

	User findByEmail(String email);

	User findByUsernameAndPassword(String name,String password);

	@Transactional(rollbackFor = Exception.class)
	@Modifying(clearAutomatically = true)
	void deleteByUsername(String username);
}
