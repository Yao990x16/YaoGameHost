package pres.yao.yaogame.host.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.yao.yaogame.host.entity.User;
import pres.yao.yaogame.host.service.UserService;
import pres.yao.yaogame.host.service.impl.UserServiceImpl;

import javax.annotation.Resource;

/**
 * @Author Fahaxiki
 * @ClassName UserController
 * @Description TODO
 * @Date 2021/3/10
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

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
		userService.register(name,password,email);
	}
}
