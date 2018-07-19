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

import com.coffee.dao.IItemDao;
import com.coffee.domain.Item;
import com.coffee.util.ConnectionContext;

/**
 * @ClassName: ItemDaoImpl
 * @Description:DAO层,IItemDao的实现类，提供基本的CRUD（增删改）功能。
 * 
 * @author: K
 */
public class ItemDaoImpl implements IItemDao {

	@Override
	public List<Item> findAll() throws SQLException {
		QueryRunner qr = new QueryRunner();
		BeanProcessor bean = new GenerousBeanProcessor();
		RowProcessor processor = new BasicRowProcessor(bean);
		String sql = "select * from `item`";

		return (List<Item>) qr.query(ConnectionContext.getInstance().getConnection(), sql,
				new BeanListHandler<Item>(Item.class, processor));
	}

	@Override
	public List<Item> find(int begin, int pageSize) throws SQLException {
		QueryRunner qr = new QueryRunner();
		BeanProcessor bean = new GenerousBeanProcessor();
		RowProcessor processor = new BasicRowProcessor(bean);
		String sql = "select * from `item` order by item_id limit ?,?";
		Object[] params = { begin, pageSize };

		return (List<Item>) qr.query(ConnectionContext.getInstance().getConnection(), sql, params,
				new BeanListHandler<Item>(Item.class, processor));
	}

	@Override
	public List<Item> find(Item item) throws SQLException {
		QueryRunner qr = new QueryRunner();
		BeanProcessor bean = new GenerousBeanProcessor();
		RowProcessor processor = new BasicRowProcessor(bean);
		String sql = "select * from `item` where name like ? and type like ? ";
		Object[] params = { "%" + item.getName() + "%", "%" + item.getType() + "%" };
		System.out.println("%" + item.getName() + "%"+"---"+ "%" + item.getType() + "%" );

		return (List<Item>) qr.query(ConnectionContext.getInstance().getConnection(), sql, params,
				new BeanListHandler<Item>(Item.class, processor));

	}

	@Override
	public List<Item> find(Item item, int begin, int pageSize) throws SQLException {
		QueryRunner qr = new QueryRunner();
		BeanProcessor bean = new GenerousBeanProcessor();
		RowProcessor processor = new BasicRowProcessor(bean);
		String sql = "select * from `item` where name like ? and type like ? order by item_id limit ?,?";
		Object[] params = { "%" + item.getName() + "%", "%" + item.getType() + "%", begin, pageSize };

		return (List<Item>) qr.query(ConnectionContext.getInstance().getConnection(), sql, params,
				new BeanListHandler<Item>(Item.class, processor));
	}

	@Override
	public List<Item> find(String name) throws SQLException {
		QueryRunner qr = new QueryRunner();
		BeanProcessor bean = new GenerousBeanProcessor();
		RowProcessor processor = new BasicRowProcessor(bean);
		String sql = "select * from `item` where name like ?";

		return (List<Item>) qr.query(ConnectionContext.getInstance().getConnection(), sql,"%"+name+"%",
				new BeanListHandler<Item>(Item.class, processor));
	}

	@Override
	public Item find(int itemId) throws SQLException {

		QueryRunner qr = new QueryRunner();
		BeanProcessor bean = new GenerousBeanProcessor();
		RowProcessor processor = new BasicRowProcessor(bean);
		String sql = "select * from `item` where item_id=?";

		return (Item) qr.query(ConnectionContext.getInstance().getConnection(), sql, itemId,
				new BeanHandler<Item>(Item.class, processor));
	}

	@Override
	public void insert(Item item) throws SQLException {
		QueryRunner runner = new QueryRunner();
		String sql = "insert into `item`(name, type, stock, price,description,image_url) values(?, ?, ?, ?,?,?)";
		Object[] params = { item.getName(), item.getType(), item.getStock(), item.getPrice(), item.getDescription(),
				item.getImageUrl() };

		runner.update(ConnectionContext.getInstance().getConnection(), sql, params);
	}

	@Override
	public void update(Item item) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "update `item` set name=?,type=? ,stock=?,price=?,description=?,image_url=? where item_id=?";

		Object params[] = { item.getName(), item.getType(), item.getStock(), item.getPrice(), item.getDescription(),
				item.getImageUrl(), item.getItemId() };

		qr.update(ConnectionContext.getInstance().getConnection(), sql, params);
	}

	@Override
	public void delete(int itemId) throws SQLException {
		QueryRunner runner = new QueryRunner();
		String sql = "delete from `item` where item_id=?";

		runner.update(ConnectionContext.getInstance().getConnection(), sql, itemId);
	}

}
