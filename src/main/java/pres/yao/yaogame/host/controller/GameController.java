package pres.yao.yaogame.host.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.yao.yaogame.host.entity.Game;
import pres.yao.yaogame.host.service.GameService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Fahaxiki
 * @ClassName GameController
 * @Description TODO
 * @Date 2021/3/10
 */
@RestController
@RequestMapping("/game")
public class GameController {
	@Resource
	private GameService gameService;

	@RequestMapping("/getByGameName")
	public Game getByGameName(String gameName) {
		return gameService.findByGameName(gameName);
	}

	@RequestMapping("/getByGameType")
	public List<Game> getByGameType(String gameType) {
		return gameService.findByGameType(gameType);
	}
}
