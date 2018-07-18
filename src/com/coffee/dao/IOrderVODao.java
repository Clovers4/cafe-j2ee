package com.coffee.dao;

import java.sql.SQLException;
import java.util.List;

import com.coffee.domain.Order;
import com.coffee.domain.OrderVO;

public interface IOrderVODao {

	/**
	 * 返回一个包含所有Item的List
	 * 
	 * @return
	 * @throws SQLException
	 */
	List<OrderVO> findAll() throws SQLException;

	/**
	 * 分页查找,返回从begin开始到begin+pageSize结束范围内的Item
	 * 
	 * @param begin
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	List<OrderVO> find(int begin, int pageSize) throws SQLException;

	/**
	 * 特征搜索(根据订单id或者userid)
	 * 
	 * @param orderFeature
	 * @return
	 * @throws SQLException
	 */
	List<OrderVO> find(OrderVO orderFeature, int begin, int pageSize) throws SQLException;

	List<OrderVO> find(OrderVO orderFeature) throws SQLException;
}
