package com.coffee.service;

import java.sql.SQLException;
import java.util.List;

import com.coffee.domain.Order;
import com.coffee.domain.OrderDetailVO;
import com.coffee.domain.OrderVO;
import com.coffee.domain.Page;

/**
 * @InterfaceName: IOrderService
 * @Description:Service层接口，提供下订单/获取所有订单/根据订单特征搜索/获得订单详情VO/获取订单分页的功能
 * 
 * @author: K
 */
public interface IOrderService extends IPageService<Order> {
	/**
	 * 提供下订单服务
	 * 
	 * @param order
	 * @throws Exception
	 */
	void add(Order order) throws Exception;

	/**
	 * 得到一个包含所有Order的List
	 * 
	 * @return List<Order>
	 * @throws SQLException
	 */
	List<Order> getAll() throws SQLException;

	/**
	 * 根据order特征查找，如order_id或者user_id
	 * 
	 * @param orderFeature
	 * @return List<Order>
	 * @throws SQLException
	 */
	List<Order> get(Order orderFeature) throws SQLException;

	/**
	 * 通过orderId来获取一个包含该订单的所有订单小件及其详情的List
	 * 
	 * @param orderId
	 * @return List<OrderDetailVO>
	 * @throws SQLException
	 */
	List<OrderDetailVO> getOrderDetailVOByOrderId(int orderId) throws SQLException;

	/**
	 * 得到begin到begin+pageSize区间内的Page<Order>对象，以在前端显示
	 * 
	 * @param begin
	 * @param pageSize
	 * @return Page<Order>
	 * @throws SQLException
	 */
	Page<Order> get(int begin, int pageSize) throws SQLException;

	/**
	 * 通过orderId来获取一个包含该订单的所有订单小件及其详情的List,并且是分页查询的，得到begin到begin+pageSize区间内的Page<Order>对象，以在前端显示
	 * 
	 * @param begin
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	Page<Order> get(Order orderFeature, int begin, int pageSize) throws SQLException;

}
