package com.coffee.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.coffee.dao.IOrderVODao;
import com.coffee.domain.Order;
import com.coffee.domain.OrderVO;
import com.coffee.util.ConnectionContext;

/**
 * @ClassName: OrderVODaoImpl
 * @Description:DAO层,IOrderVODao的实现类，提供了order表与user表的多表连接查询功能，因为order表提供了user_id，但是缺少account。
 * 
 * @author: K
 */
public class OrderVODaoImpl implements IOrderVODao {

	@Override
	public List<OrderVO> findAll() throws SQLException {
		QueryRunner qr = new QueryRunner();
		BeanProcessor bean = new GenerousBeanProcessor();
		RowProcessor processor = new BasicRowProcessor(bean);
		String sql = "select * from `order` inner join `user` on `order`.user_id=`user`.user_id";

		return (List<OrderVO>) qr.query(ConnectionContext.getInstance().getConnection(), sql,
				new BeanListHandler<OrderVO>(OrderVO.class, processor));
	}

	@Override
	public List<OrderVO> find(OrderVO orderFeature, int begin, int pageSize) throws SQLException {
		QueryRunner qr = new QueryRunner();
		BeanProcessor bean = new GenerousBeanProcessor();
		RowProcessor processor = new BasicRowProcessor(bean);
		String sql = "select * from `order` inner join `user` on `order`.user_id=`user`.user_id where order_id=? or account like ? order by order_id limit ?,?";
		Object[] params = { orderFeature.getOrderId(), "%" + orderFeature.getAccount() + "%", begin, pageSize };

		return (List<OrderVO>) qr.query(ConnectionContext.getInstance().getConnection(), sql, params,
				new BeanListHandler<OrderVO>(OrderVO.class, processor));
	}

	@Override
	public List<OrderVO> find(int begin, int pageSize) throws SQLException {
		QueryRunner qr = new QueryRunner();
		BeanProcessor bean = new GenerousBeanProcessor();
		RowProcessor processor = new BasicRowProcessor(bean);
		String sql = "select * from `order` inner join `user` on `order`.user_id=`user`.user_id order by order_id limit ?,?";
		Object[] params = { begin, pageSize };

		return (List<OrderVO>) qr.query(ConnectionContext.getInstance().getConnection(), sql, params,
				new BeanListHandler<OrderVO>(OrderVO.class, processor));
	}

	@Override
	public List<OrderVO> find(OrderVO orderFeature) throws SQLException {
		QueryRunner qr = new QueryRunner();
		BeanProcessor bean = new GenerousBeanProcessor();
		RowProcessor processor = new BasicRowProcessor(bean);
		String sql = "select * from `order` inner join `user` on `order`.user_id=`user`.user_id where order_id=? or account like ? ";
		Object[] params = { orderFeature.getOrderId(), "%" + orderFeature.getAccount() + "%" };

		return (List<OrderVO>) qr.query(ConnectionContext.getInstance().getConnection(), sql, params,
				new BeanListHandler<OrderVO>(OrderVO.class, processor));
	}


}
