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
	 * @return
	 * @throws SQLException
	 */
	List<Item> findAll() throws SQLException;
	

	/**
	 * 分页查找,返回从begin开始到begin+pageSize结束范围内的Item
	 * 
	 * @param begin
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	List<Item> find(int begin, int pageSize) throws SQLException;
	

	 /**根据商品名来查找，可能有重复
    * @param account
    * @return 查到的商品list
    */
	List<Item> find(String name)throws SQLException;
   
   /**
    * 添加商品
    * @param item
    */
   void insert(Item item) throws SQLException;

   /**
    * 修改商品信息
    * @param item
    */
   void update(Item item) throws SQLException;
   
   /**
    * 删除商品,只能通过商品id
    * @param account
    */
   void delete(int itemId) throws SQLException;
}
