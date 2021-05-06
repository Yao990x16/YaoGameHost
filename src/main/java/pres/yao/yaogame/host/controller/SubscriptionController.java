package pres.yao.yaogame.host.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.yao.yaogame.host.entity.Subscription;
import pres.yao.yaogame.host.service.SubscriptionService;

import javax.annotation.Resource;
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

	@RequestMapping("/getCompsByUserName")
	public List<Subscription> getCompsByUserName(String userName) {
		return subscriptionService.findByUserName(userName);
	}

	@RequestMapping("/deleteByCompId")
	public String deleteByCompId(int compId) {
		Subscription sub = subscriptionService.findByCompetitionId(compId);
		if(sub!=null){
			subscriptionService.deleteByCompetitionId(compId);
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
	public String subsComp(String userName,int compId){
		if(userName!=null && compId!=0){
			Subscription subs = new Subscription();
			subs.setUserName(userName);
			subs.setCompetitionId(compId);
			subscriptionService.subscribe(subs);
			return "订阅成功";
		}else{
			return "订阅失败";
		}
	}
}
