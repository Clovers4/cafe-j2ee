package com.coffee.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coffee.domain.*;
import com.coffee.exception.*;

/**
 * @InterfaceName: IUserService
 * @Description:Service层接口，提供用户登录，注册等服务。
 * 
 * @author: K
 */
public interface IUserService extends IBaseService<User>{

	/**
	 * 提供注册服务
	 * 
	 * @param user
	 * @throws UserExistException
	 */
	void register(User user) throws Exception;

	/**
	 * 提供登录服务
	 * 
	 * @param account
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	User login(String account, String password) throws SQLException;

	/**
	 * 提供更新数据(修改个人信息，密码等)服务
	 * 
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	void update(User user) throws SQLException;
	
	/**
	 * 删除用户
	 * 
	 * @param account
	 * @throws SQLException
	 */
	void delete(String account)throws SQLException;

	/**
	 * 得到全部User
	 * 
	 * @throws SQLException
	 */
	List<User> getAll() throws SQLException;

	/**
	 * 得到这个区间内的User
	 * 
	 * @param begin
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	Page<User> get(int begin, int pageSize) throws SQLException;
	
	/**
	 * 通过account查找id
	 * 
	 * @param account
	 * @return
	 * @throws SQLException
	 */
	int getId(String account) throws SQLException;
	
	/**
	 * 通过id获得用户名
	 * 
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	String getAccount(int userId) throws SQLException;
	

	/**
	 * 查询该账号是否存在
	 * 
	 * @param account
	 * @return
	 * @throws SQLException
	 */
	boolean checkExist(String account) throws SQLException;
}