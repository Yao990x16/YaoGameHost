package pres.yao.yaogame.host.service.impl;

import org.springframework.stereotype.Service;
import pres.yao.yaogame.host.Repository.TeamRepository;
import pres.yao.yaogame.host.entity.Team;
import pres.yao.yaogame.host.service.TeamService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Fahaxiki
 * @ClassName TeamServiceImpl
 * @Description TODO
 * @Date 2021/3/10
 */
@Service
public class TeamServiceImpl implements TeamService {
	@Resource
	private TeamRepository teamDao;

	/**
	 * @MethodName: findAll
	 * @Param: []
	 * @ParamType: []
	 * @return: java.util.List<pres.yao.yaogame.host.entity.Team>
	 * @Description: 查询全部队伍
	 */
	@Override
	public List<Team> findAll() {
		return teamDao.findAll();
	}

	/**
	 * @param teamName
	 * @MethodName: findByTeamName
	 * @Param: [teamName]
	 * @ParamType: [java.lang.String]
	 * @return: java.lang.String
	 * @Description: 根据队伍名称查询
	 */
	@Override
	public Team findByTeamName(String teamName) {
		return teamDao.findByTeamName(teamName);
	}

	/**
	 * @param teamType
	 * @MethodName: findByTeamType
	 * @Param: [teamType]
	 * @ParamType: [java.lang.String]
	 * @return: java.lang.String
	 * @Description: 根据队伍类型, 电竞/传统体育队伍来查询
	 */
	@Override
	public List<Team> findByTeamType(String teamType) {
		return teamDao.findByTeamType(teamType);
	}
}
