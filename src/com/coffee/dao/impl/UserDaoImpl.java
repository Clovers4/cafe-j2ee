package com.coffee.dao.impl;

import java.sql.SQLException;
import java.util.*;

import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.*;

import com.coffee.dao.IUserDao;
import com.coffee.domain.User;
import com.coffee.util.ConnectionContext;

/**
 * @ClassName: UserDaoImpl
 * @Description:DAO层,IUserDap的实现类，提供基本的CRUD（增删改）功能。
 * 
 * @author: K
 */
public class UserDaoImpl implements IUserDao {
	@Override
	public List<User> findALL() throws SQLException {
		QueryRunner qr = new QueryRunner();
		BeanProcessor bean = new GenerousBeanProcessor();
		RowProcessor processor = new BasicRowProcessor(bean);
		String sql = "select * from `user`";

		return (List<User>) qr.query(ConnectionContext.getInstance().getConnection(), sql,
				new BeanListHandler<User>(User.class, processor));
	}

	@Override
	public List<User> find(int begin, int pageSize) throws SQLException {
		QueryRunner qr = new QueryRunner();
		BeanProcessor bean = new GenerousBeanProcessor();
		RowProcessor processor = new BasicRowProcessor(bean);
		String sql = "select * from `user` order by user_id limit ?,?";
		Object[] params = { begin, pageSize };

		return (List<User>) qr.query(ConnectionContext.getInstance().getConnection(), sql, params,
				new BeanListHandler<User>(User.class, processor));
	}

	@Override
	public User find(String account) throws SQLException {
		QueryRunner qr = new QueryRunner();
		BeanProcessor bean = new GenerousBeanProcessor();
		RowProcessor processor = new BasicRowProcessor(bean);
		String sql = "select * from `user` where account=?";

		return (User) qr.query(ConnectionContext.getInstance().getConnection(), sql, account,
				new BeanHandler<User>(User.class, processor));
	}
	
	@Override
	public User findById(int userId) throws SQLException {
		QueryRunner qr = new QueryRunner();
		BeanProcessor bean = new GenerousBeanProcessor();
		RowProcessor processor = new BasicRowProcessor(bean);
		String sql = "select * from `user` where user_id=?";

		return (User) qr.query(ConnectionContext.getInstance().getConnection(), sql, userId,
				new BeanHandler<User>(User.class, processor));
	}
	@Override
	public void insert(User user) throws SQLException {
		QueryRunner runner = new QueryRunner();
		String sql = "insert into `user`(account, password, tel, email) values(?, ?, ?, ?)";
		Object[] params = { user.getAccount(), user.getPassword(), user.getTel(), user.getEmail() };

		runner.update(ConnectionContext.getInstance().getConnection(), sql, params);
	}

	@Override
	public void update(User user) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "update `user` set password=?,tel=? ,email=? where account=?";
		Object params[] = { user.getPassword(), user.getTel(), user.getEmail(), user.getAccount() };

		qr.update(ConnectionContext.getInstance().getConnection(), sql, params);
	}

	@Override
	public void delete(String account) throws SQLException {
		QueryRunner runner = new QueryRunner();
		String sql = "delete from `user` where account=?";

		runner.update(ConnectionContext.getInstance().getConnection(), sql, account);
	}



}
