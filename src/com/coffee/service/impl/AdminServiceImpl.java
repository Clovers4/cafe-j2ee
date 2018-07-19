package com.coffee.service.impl;

import java.sql.SQLException;

import com.coffee.dao.IAdminDao;
import com.coffee.dao.impl.AdminDaoImpl;
import com.coffee.domain.Admin;
import com.coffee.service.IAdminService;

/**
 * @InterfaceName: AdminServiceImpl
 * @Description:Service层,IAdminServiceI的实现类，提供管理员登录等服务。
 * 
 * @author: K
 */
public class AdminServiceImpl implements IAdminService {
	private IAdminDao adminDao = new AdminDaoImpl();

	@Override
	public Admin login(String account, String password) throws SQLException {
		Admin admin = adminDao.find(account);
		// 没找到，则user=null，返回空值。
		// 密码不匹配，同样也为空值。
		if (admin == null || admin.getPassword().equals(password)) {
			return admin;
		} else {
			return null;
		}
	}
}
