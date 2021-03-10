package pres.yao.yaogame.host.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pres.yao.yaogame.host.dao.UserRepository;
import pres.yao.yaogame.host.service.UserService;

/**
 * @Author Fahaxiki
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Date 2021/3/10
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
}
