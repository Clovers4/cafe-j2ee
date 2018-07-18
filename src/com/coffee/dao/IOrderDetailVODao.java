package com.coffee.dao;

import java.sql.SQLException;
import java.util.List;

import com.coffee.domain.OrderDetailVO;

public interface IOrderDetailVODao {
	/**
	 * 通过userId查找
	 * 
	 * @param orderId
	 * @return
	 * @throws SQLException
	 */
	List<OrderDetailVO> findByOrderId(int orderId) throws SQLException;

	/**
	 * 通过itemId查找
	 * 
	 * @param itemId
	 * @return
	 * @throws SQLException
	 */
	List<OrderDetailVO> findByItemId(int itemId) throws SQLException;

}
