package pres.yao.yaogame.host.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.yao.yaogame.host.entity.ESportsGame;
import pres.yao.yaogame.host.service.ESportsGameService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Fahaxiki
 * @ClassName ESportsGameController
 * @Description TODO
 * @Date 2021/3/10
 */
@RestController
@RequestMapping("/ESportsGame")
public class ESportsGameController {
	@Resource
	private ESportsGameService esportsService;

	@RequestMapping(value="getGameLogoUrl",params="gameName")
	public String getGameLogoUrl(String gameName) {
		ESportsGame game = esportsService.findByGameName(gameName);
		return game.getGameLogoUrl();
	}

	@RequestMapping("/getAllGames")
	public List<ESportsGame> getAllGames(){
		return esportsService.getAllGames();
	}
}
