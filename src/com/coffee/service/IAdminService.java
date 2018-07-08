package com.coffee.service;

import java.sql.SQLException;

import com.coffee.domain.Admin;


/**
 * @InterfaceName: IAdminService
 * @Description:Service层接口，提供管理员户登录等服务。
 * 
 * @author: K
 */
public interface IAdminService {
	/**
	 * 
     * 提供登录服务
	 * @param account
	 * @param password
	 * @return
	 * @throws SQLException
	 */
    Admin login(String account, String password) throws SQLException;

}
