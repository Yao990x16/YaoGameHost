package pres.yao.yaogame.host.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.yao.yaogame.host.entity.Competition;
import pres.yao.yaogame.host.entity.CompetitionAS;
import pres.yao.yaogame.host.entity.Subscription;
import pres.yao.yaogame.host.service.CompetitionService;
import pres.yao.yaogame.host.service.ESportsTeamService;
import pres.yao.yaogame.host.service.SportsTeamService;
import pres.yao.yaogame.host.service.SubscriptionService;
import pres.yao.yaogame.host.utils.GSONCompetitionAS;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Fahaxiki
 * @ClassName SubscriptionController
 * @Description TODO
 * @Date 2021/3/10
 */
@RestController
@RequestMapping("/subscription")
public class SubscriptionController {
	@Resource
	private SubscriptionService subscriptionService;
	@Resource
	private CompetitionService competitionService;
	@Resource
	private ESportsTeamService esportsTeamService;
	@Resource
	private SportsTeamService sportsTeamService;

	@RequestMapping("/getCompsByUserName")
	public List<CompetitionAS> getCompsByUserName(String userName) {
		List<Subscription> userSubsList = subscriptionService.findByUserName(userName);
		List<CompetitionAS> competitionASList = new ArrayList<CompetitionAS>();
		for (Subscription userSub : userSubsList) {
			Competition comp = competitionService.getByCompetitionId(userSub.getCompetitionId());
			competitionASList.add(GSONCompetitionAS.getCompetitionAS(comp,esportsTeamService,
					sportsTeamService));
		}
		return competitionASList;
	}

	@RequestMapping("/deleteByCompId")
	public String deleteByCompId(String userName,String compId) {
		Subscription userSubs = subscriptionService.findByCompetitionIdAndUserName(userName, compId);
		if(userSubs!=null){
			subscriptionService.deleteByCompetitionIdAndAndUserName(userName, compId);
			return "删除成功";
		}else{
			return "删除失败";
		}
	}
	
	/**
	 * @MethodName: subsComp
	 * @Param: [userName, compId]
	 * @ParamType: [java.lang.String, int]			
	 * @return: java.lang.String
	 * @Description: 用户订阅比赛
	 */
	@RequestMapping("/subsComp")
	public String subsComp(String userName,String compId){
		Subscription userSubs = subscriptionService.findByCompetitionIdAndUserName(userName, compId);
		if(userSubs==null){
			subscriptionService.subscribe(new Subscription(userName, compId));
			return "订阅成功";
		}else{
			return "订阅失败";
		}
	}
}
