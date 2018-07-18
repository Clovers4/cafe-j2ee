package com.coffee.dao;

import java.sql.SQLException;
import java.util.List;

import com.coffee.domain.Item;
import com.coffee.domain.Order;
import com.coffee.domain.OrderDetail;

/**
 * @InterfaceName: IOrderDetailDao
 * @Description:DAO层接口，提供基本的CRUD（增删改）功能。
 * 
 * @author: K
 */
public interface IOrderDetailDao {
	/**
	 * 添加订单小件信息
	 * 
	 * @param orderDetail
	 */
	void insert(OrderDetail orderDetail) throws SQLException;

}
