package pres.yao.yaogame.host.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.yao.yaogame.host.service.SportsMatchService;

import javax.annotation.Resource;

/**
 * @Author Fahaxiki
 * @ClassName SportsMatchController
 * @Description TODO
 * @Date 2021/3/10
 */
@RestController
@RequestMapping("/SportsMatch")
public class SportsMatchController {
	@Resource
	private SportsMatchService sportsMatchService;


}
