package com.coffee.dao;

import java.sql.SQLException;
import java.util.List;

import com.coffee.domain.Order;

/**
 * @InterfaceName: IOrderDao
 * @Description:DAO层接口，提供基本的CRUD（增删改）功能。
 * 
 * @author: K
 */
public interface IOrderDao {

	/**
	 * 返回一个包含所有Item的List
	 * 
	 * @return
	 * @throws SQLException
	 */
	List<Order> findAll() throws SQLException;

	/**
	 * 分页查找,返回从begin开始到begin+pageSize结束范围内的Order
	 * 
	 * @param begin
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	List<Order> find(int begin, int pageSize) throws SQLException;

	/**
	 * 特征搜索,分页查找,返回从begin开始到begin+pageSize结束范围内的Item
	 * 
	 * @param begin
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	List<Order> find(Order orderFeature, int begin, int pageSize) throws SQLException;

	/**
	 * 特征搜索
	 * 
	 * @param account
	 * @return 查到的商品list
	 */
	List<Order> find(Order orderFeature) throws SQLException;

	/**
	 * 添加商品
	 * 
	 * @param item
	 */
	Integer insert(Order order) throws SQLException;

	/**
	 * 修改订单，原则不允许
	 * 
	 * @param item
	 */
	void update(Order order) throws SQLException;

	/**
	 * 删除订单，原则上不允许
	 * 
	 * @param account
	 */
	void delete(int orderId) throws SQLException;
}
