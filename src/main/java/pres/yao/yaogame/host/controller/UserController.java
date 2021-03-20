package pres.yao.yaogame.host.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.yao.yaogame.host.entity.User;
import pres.yao.yaogame.host.service.UserService;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Fahaxiki
 * @ClassName UserController
 * @Description TODO
 * @Date 2021/3/10
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;

	@RequestMapping("/getAll")
	public List<User> getAll(){
		return userService.findAll();
	}

	@RequestMapping("/getByName")
	public User getByName(String userName) {
		return userService.findByName(userName);
	}

	@RequestMapping("/getByEmail")
	public User getByEmail(String email) {
		return userService.findByEmail(email);
	}

	@RequestMapping("/getByNameAndPwd")
	public User getByNameAndPwd(String name,String password){
		return userService.findByNameAndPwd(name,password);
	}

	@RequestMapping("/saveUser")
	public void saveUser(String name,String password,String email){
		User user = new User();
		user.setUsername(name);
		user.setPassword(password);
		user.setEmail(email);
		userService.register(user);
	}
}
