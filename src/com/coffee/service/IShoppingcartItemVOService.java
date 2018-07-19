package com.coffee.service;

import java.sql.SQLException;
import java.util.List;

import com.coffee.domain.ShoppingcartItemVO;

/**
 * @InterfaceName: IShoppingcartItemVOService
 * @Description:Service层接口，提供获取某个用户购物车中的商品及其详细信息,获取单个对象或者List
 * 
 * @author: K
 */
public interface IShoppingcartItemVOService {
	/**
	 * 得到某个用户的购物车中商品列表
	 * 
	 * @param userId
	 * @return List<ShoppingcartItemVO>
	 * @throws SQLException
	 */
	List<ShoppingcartItemVO> get(int userId) throws SQLException;

	/**
	 * 根据userId和itemId来查找，获取某用户购物车中某商品的详细信息
	 * 
	 * @param itemId
	 * @return ShoppingcartItemVO
	 * @throws SQLException
	 */
	ShoppingcartItemVO get(int userId, int itemId) throws SQLException;

}
