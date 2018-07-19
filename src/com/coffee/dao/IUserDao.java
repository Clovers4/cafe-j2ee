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
	 * 返回所有用户(List)
	 * 
	 * @return List<User> 
	 * @throws SQLException
	 */
	List<User> findALL() throws SQLException;

	/**
	 * 分页查找,返回从begin开始到begin+pageSize结束范围内的User
	 * 
	 * @param begin
	 * @param pageSize
	 * @return List<User>
	 * @throws SQLException
	 */
	List<User> find(int begin, int pageSize) throws SQLException;

	/**
	 * 根据用户名来查找用户，返回用户对象
	 * 
	 * @param account
	 * @return User
	 * @throws SQLException
	 */
	User find(String account) throws SQLException;

	/**
	 * 通过id来查找用户，返回用户对象
	 * 
	 * @param userId
	 * @return User
	 * @throws SQLException
	 */
	User findById(int userId) throws SQLException;

	/**
	 * 向user表插入一条数据
	 * 
	 * @param user
	 * @throws SQLException
	 */
	void insert(User user) throws SQLException;

	/**
	 * 修改user表中的一条数据
	 * 
	 * @param user
	 * @throws SQLException
	 */
	void update(User user) throws SQLException;

	/**
	 * 根据用户名（Unique），删除user表中的一条数据
	 * 
	 * @param account
	 * @throws SQLException
	 */
	void delete(String account) throws SQLException;
}
