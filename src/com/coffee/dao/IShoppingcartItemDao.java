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
	 * 根据userId来查找，返回该用户购物车中的各个商品信息（为shoppingcart_item表中的数据，只有item_id和number即购买数量）
	 * 
	 * @param userId
	 * @return 购物车中所有商品及信息
	 */
	List<ShoppingcartItem> find(int userId) throws SQLException;

	/**
	 * 根据userId和itemId来查找，返回一个ShoppingcartItem，可以知道该用户购物车中该商品的数量
	 * 
	 * @param userId
	 * @param itemId
	 * @return
	 * @throws SQLException
	 */
	ShoppingcartItem find(int userId, int itemId) throws SQLException;

	/**
	 * 向shoppingcart_item表中插入一条数据
	 * 
	 * @param item
	 * @throws SQLException
	 */
	void insert(ShoppingcartItem item) throws SQLException;

	/**
	 * 修改shoppingcart_item表中的一条数据
	 * 
	 * @param item
	 * @throws SQLException
	 */
	void update(ShoppingcartItem item) throws SQLException;

	/**
	 * 根据userId和itemId，删除shoppingcart_item表中的一条数据
	 * 
	 * @param userId
	 * @param itemId
	 * @throws SQLException
	 */
	void delete(int userId, int itemId) throws SQLException;
}
