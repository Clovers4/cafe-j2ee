package com.coffee.service;

import java.sql.SQLException;
import java.util.List;

import com.coffee.domain.Item;
import com.coffee.domain.Page;
import com.coffee.domain.ShoppingcartItem;

/**
 * @InterfaceName: IShoppingcartItemService
 * @Description:Service层接口，提供添加商品，修改，删除等服务。
 * 
 * @author: K
 */
public interface IShoppingcartItemService {
	/**
	 * 提供添加服务
	 * 
	 * @param item
	 */
	void add(ShoppingcartItem item) throws Exception;

	/**
	 * 提供更新商品数据服务
	 * 
	 * @param item
	 * 
	 * @throws SQLException
	 */
	void update(ShoppingcartItem item) throws SQLException;

	/**
	 * 删除商品
	 * 
	 * @param itemId
	 * @throws SQLException
	 */
	void delete(int userId, int itemId) throws SQLException;

	/**
	 * 得到全部item
	 * 
	 * @throws SQLException
	 */
	List<ShoppingcartItem> getAll() throws SQLException;

	/**
	 * 得到某个用户的购物车中商品列表
	 * 
	 * @throws SQLException
	 */
	List<ShoppingcartItem> get(int userId) throws SQLException;
	
	/**
	 * 得到某个用户的购物车中总金额
	 * 
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	double sumTotalMoney(int userId) throws SQLException;

	/**
	 * 根据itemId来查找
	 * 
	 * @param itemId
	 * @return
	 * @throws SQLException
	 */
	ShoppingcartItem get(int userId, int itemId) throws SQLException;

	/**
	 * 查询该商品是否存在
	 * 
	 * @param itemId
	 * @return
	 * @throws SQLException
	 */
	boolean checkExist(int userId, int itemId) throws SQLException;
}
