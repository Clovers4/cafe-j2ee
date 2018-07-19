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
	 * 特征搜索，根据Order的order_id或者user_id,分页查找,返回从begin开始到begin+pageSize结束范围内的Item
	 * 
	 * @param orderFeature
	 * @param begin
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	List<Order> find(Order orderFeature, int begin, int pageSize) throws SQLException;

	/**
	 * 特征搜索，根据Order的order_id或者user_id进行查找
	 * 
	 * @param orderFeature
	 * @return
	 * @throws SQLException
	 */
	List<Order> find(Order orderFeature) throws SQLException;

	/**
	 * 向order表插入一条数据，并且返回其id（主键）
	 * 
	 * @param order
	 * @return
	 * @throws SQLException
	 */
	Integer insert(Order order) throws SQLException;

	/**
	 * 更新order表的数据，原则上不允许
	 * 
	 * @param order
	 * @throws SQLException
	 */
	void update(Order order) throws SQLException;

	/**
	 * 删除order表的数据，原则上不允许
	 * 
	 * @param orderId
	 * @throws SQLException
	 */
	void delete(int orderId) throws SQLException;
}
