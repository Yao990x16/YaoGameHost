package pres.yao.yaogame.host.service.impl;

import org.springframework.stereotype.Service;
import pres.yao.yaogame.host.Repository.ESportsTeamRepository;
import pres.yao.yaogame.host.entity.ESportsTeam;
import pres.yao.yaogame.host.service.ESportsTeamService;

import javax.annotation.Resource;

/**
 * @Author Fahaxiki
 * @ClassName ESportsTeamServiceImpl
 * @Description TODO
 * @Date 2021/3/10
 */
@Service
public class ESportsTeamServiceImpl implements ESportsTeamService {
	@Resource
	private ESportsTeamRepository eSportsTeamDao;
	/**
	 * @param teamId
	 * @MethodName: findByTeamId
	 * @Param: [teamId]
	 * @ParamType: [int]
	 * @return: pres.yao.yaogame.host.entity.ESportsTeam
	 * @Description: 电竞队伍id
	 */
	@Override
	public ESportsTeam findByTeamId(int teamId) {
		return eSportsTeamDao.findByTeamId(teamId);
	}

	/**
	 * @param teamLogoUrl
	 * @MethodName: findByTeamLogoUrl
	 * @Param: [teamLogoUrl]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.ESportsTeam
	 * @Description: 电竞队伍LogoUrl
	 */
	@Override
	public ESportsTeam findByTeamLogoUrl(String teamLogoUrl) {
		return eSportsTeamDao.findByTeamLogoUrl(teamLogoUrl);
	}

	/**
	 * @param teamName
	 * @MethodName: findByTeamName
	 * @Param: [teamName]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.ESportsTeam
	 * @Description: 电竞队伍名称
	 */
	@Override
	public ESportsTeam findByTeamName(String teamName) {
		return eSportsTeamDao.findByTeamName(teamName);
	}

	/**
	 * @param teamSubName
	 * @MethodName: findByTeamSubName
	 * @Param: [teamSubName]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.ESportsTeam
	 * @Description: 电竞队伍别称
	 */
	@Override
	public ESportsTeam findByTeamSubName(String teamSubName) {
		return eSportsTeamDao.findByTeamSubName(teamSubName);
	}
}
