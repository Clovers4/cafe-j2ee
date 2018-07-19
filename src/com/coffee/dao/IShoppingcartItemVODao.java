package com.coffee.dao;

import java.sql.SQLException;
import java.util.List;

import com.coffee.domain.ShoppingcartItemVO;

/**
 * @InterfaceName: IShoppingcartItemVODao
 * @Description:DAO层接口，提供了查询shoppingcart_item表与item表的多表连接的结果，提供更详细的购物车内的餐点的信息
 * 
 * @author: K
 */
public interface IShoppingcartItemVODao {
	/**
	 * 根据userId来查找，返回该用户购物车中的ShoppingcartItemVO（商品信息）List
	 * 
	 * @param userId
	 * @return List<ShoppingcartItemVO>
	 * @throws SQLException
	 */
	List<ShoppingcartItemVO> find(int userId) throws SQLException;

	/**
	 * 根据userId和itemId来查找，定位，返回一个ShoppingcartItemVO，
	 * 
	 * @param userId
	 * @param itemId
	 * @return 查到的商品
	 * @throws SQLException
	 */
	ShoppingcartItemVO find(int userId, int itemId) throws SQLException;

}
