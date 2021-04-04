package pres.yao.yaogame.host.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.yao.yaogame.host.service.SubscriptionService;

import javax.annotation.Resource;
import java.util.HashMap;

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


}
