package pres.yao.yaogame.host.service.impl;

import org.springframework.stereotype.Service;
import pres.yao.yaogame.host.dao.SportsTeamRepository;
import pres.yao.yaogame.host.entity.SportsTeam;
import pres.yao.yaogame.host.service.SportsTeamService;

import javax.annotation.Resource;

/**
 * @Author Fahaxiki
 * @ClassName SportsTeamServiceImpl
 * @Description TODO
 * @Date 2021/3/10
 */
@Service
public class SportsTeamServiceImpl implements SportsTeamService {
	@Resource
	private SportsTeamRepository sportsTeamDao;

	/**
	 * @param teamId
	 * @MethodName: findByTeamId
	 * @Param: [teamId]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.SportsTeam
	 * @Description: 根据teamId查找
	 */
	@Override
	public SportsTeam findByTeamId(int teamId) {
		return sportsTeamDao.findByTeamId(teamId);
	}

	/**
	 * @param teamLogoUrl
	 * @MethodName: findByTeamLogoUrl
	 * @Param: [teamLogoUrl]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.SportsTeam
	 * @Description:
	 */
	@Override
	public SportsTeam findByTeamLogoUrl(String teamLogoUrl) {
		return sportsTeamDao.findByTeamLogoUrl(teamLogoUrl);
	}

	/**
	 * @param teamName
	 * @MethodName: findByTeamName
	 * @Param: [teamName]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.SportsTeam
	 * @Description: 根据队伍名称查找
	 */
	@Override
	public SportsTeam findByTeamName(String teamName) {
		return sportsTeamDao.findByTeamName(teamName);
	}
}
