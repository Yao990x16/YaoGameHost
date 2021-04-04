package pres.yao.yaogame.host.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pres.yao.yaogame.host.dao.UserRepository;
import pres.yao.yaogame.host.entity.User;
import pres.yao.yaogame.host.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Fahaxiki
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Date 2021/3/10
 */
@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserRepository userDao;


	/**
	 * @param name
	 * @MethodName: findByName
	 * @param: [name]
	 * @paramType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.User
	 * @Description:
	 */
	@Override
	public User findByName(String name) {
		return userDao.findByUsername(name);
	}

	/**
	 * @param email
	 * @MethodName: findByEmail
	 * @Param: [email]
	 * @ParamType: [java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.User
	 * @Description: 根据邮箱查找
	 */
	@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	/**
	 * @param name
	 * @param password
	 * @MethodName: findByNameAndPwd
	 * @Param: [name, password]
	 * @ParamType: [java.lang.String, java.lang.String]
	 * @return: pres.yao.yaogame.host.entity.User
	 * @Description: 登录时候, 检查是否已经注册并且密码是否正确
	 */
	@Override
	public User findByNameAndPwd(String name, String password) {
		return userDao.findByUsernameAndPassword(name,password);
	}

	/**
	 * @MethodName: findAll
	 * @Param: []
	 * @ParamType: []
	 * @return: java.util.List
	 * @Description: 查询全部用户
	 */
	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	/**
	 * @param
	 * @MethodName: register
	 * @Param: [user]
	 * @ParamType: [pres.yao.yaogame.host.entity.User]
	 * @return: void
	 * @Description: 注册, 插入到数据库
	 */
	@Override
	public void register(User user) {
		userDao.save(user);
	}

	/**
	 * @param userName
	 * @MethodName: deleteByUserName
	 * @Param: [userName]
	 * @ParamType: [java.lang.String]
	 * @return: void
	 * @Description: 根据用户名删除
	 */
	@Override
	public void deleteByUserName(String userName) {
		userDao.deleteByUsername(userName);
	}
}
