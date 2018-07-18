package com.coffee.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.coffee.dao.IShoppingcartItemDao;
import com.coffee.domain.Item;
import com.coffee.domain.ShoppingcartItem;
import com.coffee.util.ConnectionContext;

/**
 * @ClassName: ShoppingcartItemDaoImpl
 * @Description:DAO层,IShoppingcartItemDao的实现类，提供基本的CRUD（增删改）功能。
 * 
 * @author: K
 */
public class ShoppingcartItemDaoImpl implements IShoppingcartItemDao {

	@Override
	public List<ShoppingcartItem> findAll() throws SQLException {
		QueryRunner qr = new QueryRunner();
		BeanProcessor bean = new GenerousBeanProcessor();
		RowProcessor processor = new BasicRowProcessor(bean);
		String sql = "select * from `shoppingcart_item`";

		return (List<ShoppingcartItem>) qr.query(ConnectionContext.getInstance().getConnection(), sql,
				new BeanListHandler<ShoppingcartItem>(ShoppingcartItem.class, processor));
	}

	@Override
	public List<ShoppingcartItem> find(int userId) throws SQLException {
		QueryRunner qr = new QueryRunner();
		BeanProcessor bean = new GenerousBeanProcessor();
		RowProcessor processor = new BasicRowProcessor(bean);
		String sql = "select * from `shoppingcart_item` where user_id=?";

		return (List<ShoppingcartItem>) qr.query(ConnectionContext.getInstance().getConnection(), sql, userId,
				new BeanListHandler<ShoppingcartItem>(ShoppingcartItem.class, processor));
	}

	@Override
	public ShoppingcartItem find(int userId, int itemId) throws SQLException {
		QueryRunner qr = new QueryRunner();
		BeanProcessor bean = new GenerousBeanProcessor();
		RowProcessor processor = new BasicRowProcessor(bean);
		String sql = "select * from `shoppingcart_item` where user_id=? and item_id=?";

		Object[] params = { userId, itemId };

		return (ShoppingcartItem) qr.query(ConnectionContext.getInstance().getConnection(), sql, params,
				new BeanHandler<ShoppingcartItem>(ShoppingcartItem.class, processor));
	}

	@Override
	public void insert(ShoppingcartItem item) throws SQLException {
		QueryRunner runner = new QueryRunner();
		String sql = "insert into `shoppingcart_item`(user_id,item_id,number) values(?, ?, ?)";
		Object[] params = { item.getUserId(), item.getItemId(), item.getNumber() };

		runner.update(ConnectionContext.getInstance().getConnection(), sql, params);

	}

	@Override
	public void update(ShoppingcartItem item) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "update `shoppingcart_item` set number=? where user_id=? and item_id=?";

		Object[] params = { item.getNumber(), item.getUserId(), item.getItemId() };

		qr.update(ConnectionContext.getInstance().getConnection(), sql, params);
	}

	@Override
	public void delete(int userId, int itemId) throws SQLException {
		QueryRunner runner = new QueryRunner();
		String sql = "delete from `shoppingcart_item` where user_id=? and item_id=?";

		Object[] params = { userId, itemId };

		runner.update(ConnectionContext.getInstance().getConnection(), sql, params);
	}

}
