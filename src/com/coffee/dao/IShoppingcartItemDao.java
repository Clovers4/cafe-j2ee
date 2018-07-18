package com.coffee.dao;

import java.sql.SQLException;
import java.util.List;

import com.coffee.domain.Item;
import com.coffee.domain.ShoppingcartItem;

/**
 * @InterfaceName: IShoppingcartItemDao
 * @Description:DAO层接口，提供基本的CRUD（增删改）功能。
 * 
 * @author: K
 */
public interface IShoppingcartItemDao {
	/**
	 * 返回一个包含所有ShoppingcartItem的List
	 * 
	 * @return
	 * @throws SQLException
	 */
	List<ShoppingcartItem> findAll() throws SQLException;

	/**
	 * 根据userId来查找
	 * 
	 * @param userId
	 * @return 购物车中所有商品及信息
	 */
	List<ShoppingcartItem> find(int userId) throws SQLException;

	/**
	 * 根据userId和itemId来查找
	 * 
	 * @param userId
	 * @param itemId
	 * @return 查到的商品
	 */
	ShoppingcartItem find(int userId, int itemId) throws SQLException;

	/**
	 * 添加商品到购物车
	 * 
	 * @param item
	 */
	void insert(ShoppingcartItem item) throws SQLException;

	/**
	 * 修改商品信息
	 * 
	 * @param item
	 */
	void update(ShoppingcartItem item) throws SQLException;

	/**
	 * 删除商品
	 * 
	 * @param account
	 */
	void delete(int userId, int itemId) throws SQLException;
}
