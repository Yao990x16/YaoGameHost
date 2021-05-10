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

	@RequestMapping("getByUserNameAndCompId")
	public HashMap<String, String> getByUserNameAndCompId(String userName, String competitionId){
		Subscription userSubs = subscriptionService.findByUserNameAndCompetitionId(userName, competitionId);
		return userSubs==null?new HashMap<>(16){{
			put("msg","没有订阅");
		}}:new HashMap<>(16){{
			put("userName",userSubs.getUserName());
			put("msg","已经订阅");
		}};
	}

	@RequestMapping("/deleteByCompId")
	public HashMap<String, String> deleteByCompId(String userName,String competitionId) {
		Subscription userSubs = subscriptionService.findByUserNameAndCompetitionId(userName, competitionId);
		if(userSubs!=null){
			subscriptionService.deleteByUserNameAndCompetitionId(userName, competitionId);
			return new HashMap<>(16){{
				put("userName",userSubs.getUserName());
				put("msg","取消成功");
			}};
		}else{
			return new HashMap<>(16){{
				put("msg","取消失败");
			}};
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
	public HashMap<String, String>subsComp(String userName,String competitionId){
		Subscription userSubs = subscriptionService.findByUserNameAndCompetitionId(userName,competitionId);
		if(userSubs==null){
			Subscription subs = new Subscription(userName, competitionId);
			subscriptionService.subscribe(subs);
			return new HashMap<>(16){{
				put("userName",subs.getUserName());
				put("msg","订阅成功");
			}};
		}else{
			return new HashMap<>(16){{
				put("userName",userSubs.getUserName());
				put("msg","订阅失败");
			}};
		}
	}
}
