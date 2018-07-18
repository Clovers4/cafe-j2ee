package com.coffee.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.coffee.dao.IOrderDetailDao;
import com.coffee.domain.OrderDetail;
import com.coffee.util.ConnectionContext;

/**
 * @ClassName: OrderDetailImpl
 * @Description:DAO层,IOrderDetail的实现类，提供基本的CRUD（增删改）功能。
 * 
 * @author: K
 */
public class OrderDetailDaoImpl implements IOrderDetailDao {

	@Override
	public void insert(OrderDetail orderDetail) throws SQLException {
		QueryRunner runner = new QueryRunner();
		String sql = "insert into `order_detail`(order_id,item_id,number) values(?, ?, ?)";
		Object[] params = { orderDetail.getOrderId(), orderDetail.getItemId(), orderDetail.getNumber() };

		runner.update(ConnectionContext.getInstance().getConnection(), sql, params);
	}

}
