package com.coffee.dao;

import java.sql.SQLException;
import java.util.List;

import com.coffee.domain.Order;
import com.coffee.domain.OrderVO;

/**
 * @InterfaceName: IOrderVODao
 * @Description:DAO层接口，提供了order表与user表的多表连接查询功能，因为order表提供了user_id，但是缺少account。
 * 
 * @author: K
 */
public interface IOrderVODao {
	/**
	 * 返回一个包含有所有OrderVO的List
	 * 
	 * @return List<OrderVO>
	 * @throws SQLException
	 */
	List<OrderVO> findAll() throws SQLException;

	/**
	 * 分页查找,返回从begin开始到begin+pageSize结束范围内的OrderVO
	 * 
	 * @param begin
	 * @param pageSize
	 * @return List<OrderVO>
	 * @throws SQLException
	 */
	List<OrderVO> find(int begin, int pageSize) throws SQLException;

	/**
	 * 特征搜索(根据订单id或者用户的account关键词),分页查找，返回一部分
	 * 
	 * @param orderFeature
	 * @param begin
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	List<OrderVO> find(OrderVO orderFeature, int begin, int pageSize) throws SQLException;

	/**
	 * 特征搜索(根据订单id或者用户的account关键词),全部返回
	 * 
	 * @param orderFeature
	 * @param begin
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	List<OrderVO> find(OrderVO orderFeature) throws SQLException;
}
