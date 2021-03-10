package pres.yao.yaogame.host.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pres.yao.yaogame.host.entity.User;

/**
 * @Author Fahaxiki
 * @ClassName UserRepository
 * @Description TODO
 * @Date 2021/3/10
 */
public interface UserRepository extends JpaRepository<User,Integer> {
}
