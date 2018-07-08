package com.coffee.dao;

import java.sql.SQLException;
import java.util.List;

import com.coffee.domain.Admin;


/**
 * @InterfaceName: IAdminDao
 * @Description:DAO层接口，提供基本的CRUD（增删改）功能。
 * 
 * @author: K
 */
public interface IAdminDao {

	/**
	 * 查找所有管理员
	 * 
	 * @param account
	 * @return 所有管理员
	 */
	List<Admin> findALL() throws SQLException;

	/**
	 * 根据管理员名来查找管理员
	 * 
	 * @param account
	 * @return 查到的管理员
	 */
	Admin find(String account) throws SQLException;

	/**
	 * 修改管理员信息，如密码
	 * 
	 * @param admin
	 */
	void update(Admin admin) throws SQLException;

}
