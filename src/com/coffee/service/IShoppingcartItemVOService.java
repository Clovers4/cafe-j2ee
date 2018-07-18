package com.coffee.service;

import java.sql.SQLException;
import java.util.List;

import com.coffee.domain.ShoppingcartItemVO;

/**
 * @InterfaceName: IShoppingcartItemVOService
 * @Description:Service层接口，提供等服务。
 * 
 * @author: K
 */
public interface IShoppingcartItemVOService {

	/**
	 * 得到某个用户的购物车中商品列表
	 * 
	 * @throws SQLException
	 */
	List<ShoppingcartItemVO> get(int userId) throws SQLException;

	/**
	 * 根据itemId来查找
	 * 
	 * @param itemId
	 * @return
	 * @throws SQLException
	 */
	ShoppingcartItemVO get(int userId, int itemId) throws SQLException;

}
