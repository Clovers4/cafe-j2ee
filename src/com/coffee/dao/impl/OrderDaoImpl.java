package com.coffee.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.coffee.dao.IOrderDao;
import com.coffee.domain.Order;
import com.coffee.domain.User;
import com.coffee.util.ConnectionContext;

/**
 * @ClassName: OrderDaoImpl
 * @Description:DAO层,IOrderDao的实现类，提供基本的CRUD（增删改）功能。
 * 
 * @author: K
 */
public class OrderDaoImpl implements IOrderDao {

	@Override
	public List<Order> findAll() throws SQLException {
		QueryRunner qr = new QueryRunner();
		BeanProcessor bean = new GenerousBeanProcessor();
		RowProcessor processor = new BasicRowProcessor(bean);
		String sql = "select * from `order`";

		return (List<Order>) qr.query(ConnectionContext.getInstance().getConnection(), sql,
				new BeanListHandler<Order>(Order.class, processor));
	}

	@Override
	public List<Order> find(int begin, int pageSize) throws SQLException {
		QueryRunner qr = new QueryRunner();
		BeanProcessor bean = new GenerousBeanProcessor();
		RowProcessor processor = new BasicRowProcessor(bean);
		String sql = "select * from `order` order by order_id limit ?,?";
		Object[] params = { begin, pageSize };

		return (List<Order>) qr.query(ConnectionContext.getInstance().getConnection(), sql, params,
				new BeanListHandler<Order>(Order.class, processor));
	}

	@Override
	public List<Order> find(Order orderFeature) throws SQLException {
		QueryRunner qr = new QueryRunner();
		BeanProcessor bean = new GenerousBeanProcessor();
		RowProcessor processor = new BasicRowProcessor(bean);
		String sql = "select * from `order` where order_id=? or user_id=? ";
		Object[] params = { orderFeature.getOrderId(), orderFeature.getUserId() };

		return (List<Order>) qr.query(ConnectionContext.getInstance().getConnection(), sql, params,
				new BeanListHandler<Order>(Order.class, processor));

	}

	@Override
	public List<Order> find(Order orderFeature, int begin, int pageSize) throws SQLException {
		QueryRunner qr = new QueryRunner();
		BeanProcessor bean = new GenerousBeanProcessor();
		RowProcessor processor = new BasicRowProcessor(bean);
		String sql = "select * from `order` where order_id=? or user_id=?  order by order_id limit ?,?";
		Object[] params = { orderFeature.getOrderId(), orderFeature.getUserId(), begin, pageSize };

		return (List<Order>) qr.query(ConnectionContext.getInstance().getConnection(), sql, params,
				new BeanListHandler<Order>(Order.class, processor));
	}

	/*
	 * 不能保证并发有效
	 * (non-Javadoc)
	 * @see com.coffee.dao.IOrderDao#insert(com.coffee.domain.Order)
	 */
	@Override
	public Integer insert(Order order) throws SQLException {
		QueryRunner runner = new QueryRunner();
		BeanProcessor bean = new GenerousBeanProcessor();
		RowProcessor processor = new BasicRowProcessor(bean);
		
		String sql = "insert into `order`(user_id,order_total,created_time) values(?, ?, ?)";
		Object[] params = { order.getUserId(), order.getOrderTotal(), order.getCreatedTime() };

		runner.update(ConnectionContext.getInstance().getConnection(), sql, params);
		
		sql="select max(order_id) from `order`";

		//这里需要使用ScalarHandler才能正确读取，直接使用BeanHandler无法转化成Integer，可能原因是Integer没有实现setter方法
		return (Integer) runner.query(ConnectionContext.getInstance().getConnection(), sql,
				 new ScalarHandler<Integer>());
	}

	@Override
	public void update(Order order) throws SQLException {
		return;
	}

	@Override
	public void delete(int orderId) throws SQLException {
		return;
	}

}
