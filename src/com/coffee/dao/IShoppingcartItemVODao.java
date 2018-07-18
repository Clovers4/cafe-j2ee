package com.coffee.dao;

import java.sql.SQLException;
import java.util.List;

import com.coffee.domain.ShoppingcartItemVO;

public interface IShoppingcartItemVODao {
	/**
	 * 根据userId来查找
	 * 
	 * @param userId
	 * @return 购物车中所有商品及信息
	 */
	List<ShoppingcartItemVO> find(int userId) throws SQLException;

	/**
	 * 根据userId和itemId来查找
	 * 
	 * @param userId
	 * @param itemId
	 * @return 查到的商品
	 */
	ShoppingcartItemVO find(int userId, int itemId) throws SQLException;

}
