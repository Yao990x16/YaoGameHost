package pres.yao.yaogame.host.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pres.yao.yaogame.host.entity.Competition;

/**
 * @Author Fahaxiki
 * @ClassName CompetitionRepository
 * @Description TODO
 * @Date 2021/3/10
 */
public interface CompetitionRepository extends JpaRepository<Competition,Integer> {
}
