package pres.yao.yaogame.host.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.yao.yaogame.host.entity.SportsGame;
import pres.yao.yaogame.host.service.SportsGameService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Fahaxiki
 * @ClassName SportsGameController
 * @Description TODO
 * @Date 2021/3/10
 */
@RestController
@RequestMapping("/SportsGame")
public class SportsGameController {
	@Resource
	private SportsGameService sportsGameService;
	
	/**
	 * @MethodName: getSportsNames
	 * @Param: []
	 * @ParamType: []			
	 * @return: java.util.List<pres.yao.yaogame.host.entity.SportsGame>
	 * @Description: 获取所有赛事名称,比如篮球/足球
	 */
	@RequestMapping("/getAllSports")
	public List<SportsGame> getAllSports() {
		return sportsGameService.getAllGames();
	}
}