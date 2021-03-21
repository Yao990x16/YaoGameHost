package pres.yao.yaogame.host.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.yao.yaogame.host.entity.User;
import pres.yao.yaogame.host.service.UserService;
import javax.annotation.Resource;
import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.HashMap;
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
	
	/**
	 * @MethodName: saveUser
	 * @Param: [name, password, email]
	 * @ParamType: [java.lang.String, java.lang.String, java.lang.String]			
	 * @return: void
	 * @Description: 用户注册
	 */
	@RequestMapping("/saveUser")
	public HashMap<String, String> saveUser(String name,String password,String email) {
		HashMap<String,String> hashMap = new HashMap<>(16);
		try {
			User user = new User();
			user.setUsername(name);
			user.setPassword(password);
			user.setEmail(email);
			userService.register(user);
			hashMap.put("msg","ok");
		}catch(Exception e){
			e.printStackTrace();
			hashMap.put("msg","error");
		}
		return hashMap;
	}
	
	/**
	 * @MethodName: login
	 * @Param: [username, password]
	 * @ParamType: [java.lang.String, java.lang.String]			
	 * @return: java.util.HashMap<java.lang.String,java.lang.String>
	 * @Description: 用户登录
	 */
	@RequestMapping("/login")
	public HashMap<String, String> login(String username, String password)throws IOException {
		User user = userService.findByNameAndPwd(username, password);
		if(user != null){
			return new HashMap<>(16){{
				put("msg","ok");
			}};
		}else{
			return new HashMap<>(16){{
				put("msg","error");
			}};
		}
	}
}
