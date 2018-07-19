package com.coffee.dao;

import com.coffee.domain.Item;

import java.sql.SQLException;
import java.util.List;

/**
 * @InterfaceName: IItemDao
 * @Description:DAO层接口，提供基本的CRUD（增删改）功能。
 * 
 * @author: K
 */
public interface IItemDao {
	/**
	 * 返回一个包含所有Item的List
	 * 
	 * @return List<Item>
	 * @throws SQLException
	 */
	List<Item> findAll() throws SQLException;

	/**
	 * 分页查找,返回从begin开始到begin+pageSize结束范围内的Item
	 * 
	 * @param begin
	 * @param pageSize
	 * @return List<Item>
	 * @throws SQLException
	 */
	List<Item> find(int begin, int pageSize) throws SQLException;

	/**
	 * 根据特征（搜索），全部查找,返回List
	 * 
	 * @param begin
	 * @param pageSize
	 * @return List<Item>
	 * @throws SQLException
	 */
	List<Item> find(Item item) throws SQLException;
	

	/**
	 * 根据特征（搜索），分页查找,返回从begin开始到begin+pageSize结束范围内的Item
	 * 
	 * @param begin
	 * @param pageSize
	 * @return List<Item>
	 * @throws SQLException
	 */
	List<Item> find(Item item,int begin, int pageSize) throws SQLException;

	/**
	 * 根据商品名关键词来查找，返回一个包含该关键词的List<Item>
	 * 
	 * @param account
	 * @return List<Item>
	 */
	List<Item> find(String name) throws SQLException;

	/**
	 * 根据商品ID来查找
	 * 
	 * @param itemId
	 * @return 查到的商品
	 */
	Item find(int itemId) throws SQLException;

	/**
	 * 向item表插入数据
	 * 
	 * @param item
	 * @throws SQLException
	 */
	void insert(Item item) throws SQLException;

	/**
	 * 修改item表数据
	 * 
	 * @param item
	 */
	void update(Item item) throws SQLException;

	/**
	 * 删除item表的数据，通过itemID主键来定位
	 * 
	 * @param account
	 */
	void delete(int itemId) throws SQLException;
}
