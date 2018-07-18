package com.coffee.service;

import java.sql.SQLException;
import java.util.List;

import com.coffee.domain.Order;
import com.coffee.domain.OrderDetailVO;
import com.coffee.domain.OrderVO;
import com.coffee.domain.Page;

/**
 * @InterfaceName: IOrderService
 * @Description:Service层接口，提供下订单等服务。
 * 
 * @author: K
 */
public interface IOrderService extends IBaseService<Order>{
	/**
	 * 提供添加服务
	 * 
	 * @param item
	 */
	void add(Order order) throws Exception;

	
	/**
	 * 得到全部item
	 * 
	 * @throws SQLException
	 */
	List<Order> getAll() throws SQLException;

	/**
	 * 根据order特征查找，如order_id或者user_id
	 * 
	 * @param itemId
	 * @return
	 * @throws SQLException
	 */
	List<Order> get(Order orderFeature) throws SQLException;
	
	List<OrderDetailVO> getOrderDetailVOByOrderId(int orderId) throws SQLException;
	
	/**
	 * 得到这个区间内的item
	 * 
	 * @param begin
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	Page<Order> get(int begin, int pageSize) throws SQLException;

	/**
	 * 得到这个区间内的item
	 * 
	 * @param begin
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	Page<Order> get(Order order, int begin, int pageSize) throws SQLException;
	
	

	
}
