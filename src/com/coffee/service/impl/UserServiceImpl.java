package com.coffee.service.impl;

import java.sql.SQLException;

import com.coffee.dao.IUserDao;
import com.coffee.dao.impl.UserDaoImpl;
import com.coffee.domain.User;
import com.coffee.exception.UserExistException;
import com.coffee.service.IUserService;

/**
 * @InterfaceName: UserServiceImpl
 * @Description:Service层,IUserService的实现类，提供用户登录，注册等服务。
 * 
 * @author: K
 */
public class UserServiceImpl implements IUserService {
	private IUserDao userDao = new UserDaoImpl();

	@Override
	public void register(User user) throws UserExistException, SQLException {
		if (userDao.find(user.getAccount()) != null) {
			throw new UserExistException("注册的用户名已存在！");
		}
		userDao.insert(user);
	}

	@Override
	public User login(String account, String password) throws SQLException {
		User user = userDao.find(account);
		// 没找到，则user=null，返回空值。
		// 密码不匹配，同样也为空值。
		if (user == null || user.getPassword().equals(password)) {
			return user;
		} else {
			return null;
		}
	}

	@Override
	public void update(User user) throws SQLException {
		userDao.update(user);
	}

}
