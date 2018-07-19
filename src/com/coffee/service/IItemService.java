package com.coffee.service;

import java.sql.SQLException;
import java.util.List;

import com.coffee.domain.Item;
import com.coffee.domain.Page;

/**
 * @InterfaceName: IItemService
 * @Description:Service层接口，提供添加商品，修改，删除等服务。
 * 
 * @author: K
 */
public interface IItemService extends IPageService<Item> {
	/**
	 * 提供添加服务
	 * 
	 * @param item
	 */
	void add(Item item) throws Exception;

	/**
	 * 提供更新商品数据服务
	 * 
	 * @param item
	 * 
	 * @throws SQLException
	 */
	void update(Item item) throws SQLException;

	/**
	 * 上传图片URL
	 * 
	 * @param itemId
	 * @param imageUrl
	 * @throws SQLException
	 */
	void update(int itemId, String imageUrl) throws SQLException;

	/**
	 * 删除商品
	 * 
	 * @param itemId
	 * @throws SQLException
	 */
	void delete(int itemId) throws SQLException;

	/**
	 * 得到全部item
	 * 
	 * @throws SQLException
	 */
	List<Item> getAll() throws SQLException;

	/**
	 * 根据itemId来查找
	 * 
	 * @param itemId
	 * @return
	 * @throws SQLException
	 */
	Item get(int itemId) throws SQLException;
	
	/**
	 * 得到这个区间内的item
	 * 
	 * @param begin
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	Page<Item> get(int begin, int pageSize) throws SQLException;

	/**
	 * 得到这个区间内的item
	 * 
	 * @param begin
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	Page<Item> get(Item item, int begin, int pageSize) throws SQLException;

	/**
	 * 查询该商品是否存在
	 * 
	 * @param itemId
	 * @return
	 * @throws SQLException
	 */
	boolean checkExist(int itemId) throws SQLException;
}
