package com.coffee.dao;

import java.sql.SQLException;
import java.util.List;

import com.coffee.domain.*;

/**
 * @InterfaceName: IUserDao
 * @Description:DAO层接口，提供基本的CRUD（增删改）功能。
 * 
 * @author: K
 */
public interface IUserDao {

	/**
	 * 查找所有用户
	 * 
	 * @return 所有用户
	 */
	List<User> findALL() throws SQLException;

	/**
	 * 分页查找,返回从begin开始到begin+pageSize结束范围内的User
	 * 
	 * @param begin
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	List<User> find(int begin, int pageSize) throws SQLException;

	/**
	 * 根据用户名来查找用户
	 * 
	 * @param account
	 * @return 查到的用户
	 */
	User find(String account) throws SQLException;

	/**
	 * 通过id来查找
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	User findById(int userId) throws SQLException;

	/**
	 * 添加用户
	 * 
	 * @param user
	 */
	void insert(User user) throws SQLException;

	/**
	 * 修改用户信息
	 * 
	 * @param user
	 */
	void update(User user) throws SQLException;

	/**
	 * 删除用户
	 * 
	 * @param account
	 */
	void delete(String account) throws SQLException;
}
