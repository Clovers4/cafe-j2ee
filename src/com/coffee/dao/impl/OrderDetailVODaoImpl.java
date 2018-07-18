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

import com.coffee.dao.IOrderDetailDao;
import com.coffee.dao.IOrderDetailVODao;
import com.coffee.domain.OrderDetailVO;
import com.coffee.domain.ShoppingcartItemVO;
import com.coffee.util.ConnectionContext;

/**
 * @ClassName: OrderDetailVODaoImpl
 * @Description:DAO层,IOrderDetailVODao的实现类，提供基本的CRUD（增删改）功能。
 * 
 * @author: K
 */
public class OrderDetailVODaoImpl implements IOrderDetailVODao {

	@Override
	public List<OrderDetailVO> findByOrderId(int orderId) throws SQLException {
		QueryRunner qr = new QueryRunner();
		BeanProcessor bean = new GenerousBeanProcessor();
		RowProcessor processor = new BasicRowProcessor(bean);
		String sql = "select * from `order_detail` inner join `item` on `order_detail`.item_id=`item`.item_id where order_id=?";

		return (List<OrderDetailVO>) qr.query(ConnectionContext.getInstance().getConnection(), sql, orderId,
				new BeanListHandler<OrderDetailVO>(OrderDetailVO.class, processor));
	}

	@Override
	public List<OrderDetailVO> findByItemId(int itemId) throws SQLException {
		QueryRunner qr = new QueryRunner();
		BeanProcessor bean = new GenerousBeanProcessor();
		RowProcessor processor = new BasicRowProcessor(bean);
		String sql = "select * from `order_detail` inner join `item` on `order_detail`.item_id=`item`.item_id where item_id=?";

		return (List<OrderDetailVO>) qr.query(ConnectionContext.getInstance().getConnection(), sql, itemId,
				new BeanListHandler<OrderDetailVO>(OrderDetailVO.class, processor));
	}

}
