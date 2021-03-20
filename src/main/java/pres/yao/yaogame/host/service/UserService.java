package pres.yao.yaogame.host.service;

import org.springframework.stereotype.Service;
import pres.yao.yaogame.host.entity.User;

import java.util.List;

/**
 * @Author Fahaxiki
 * @ClassName UserService
 * @Description TODO
 * @Date 2021/3/10
 */

public interface UserService {
	/**
	 * @MethodName: findByName
	 * @param: [name]
	 * @paramType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.User
	 * @Description:
	 */
	public User findByName(String name);

	/**
	 * @MethodName: findByEmail
	 * @Param: [email]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.User
	 * @Description: 根据邮箱查找
	 */
	public User findByEmail(String email);

	/**
	 * @MethodName: findByNameAndPwd
	 * @Param: [name, password]
	 * @ParamType: [java.lang.String, java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.User
	 * @Description: 登录时候,检查是否已经注册并且密码是否正确
	 */
	public User findByNameAndPwd(String name, String password);

	/**
	 * @MethodName: findAll
	 * @Param: []
	 * @ParamType: []
	 * @return: java.util.List
	 * @Description: 查询全部用户
	 */
	public List<User> findAll();

	/**
	 * @MethodName: register
	 * @Param: [name, password, email]
	 * @ParamType: [java.lang.String, java.lang.String, java.lang.String]			
	 * @return: void
	 * @Description: 注册用户
	 */
	public void register(User user);
}
