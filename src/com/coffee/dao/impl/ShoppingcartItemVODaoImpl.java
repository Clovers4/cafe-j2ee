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

import com.coffee.dao.IShoppingcartItemVODao;
import com.coffee.domain.ShoppingcartItem;
import com.coffee.domain.ShoppingcartItemVO;
import com.coffee.util.ConnectionContext;

/**
 * @ClassName: ShoppingcartItemVODaoImpl
 * @Description:DAO层,IShoppingcartItemVODao的实现类，提供了查询shoppingcart_item表与item表的多表连接的结果，提供更详细的购物车内的餐点的信息
 * 
 * @author: K
 */
public class ShoppingcartItemVODaoImpl implements IShoppingcartItemVODao {

	@Override
	public List<ShoppingcartItemVO> find(int userId) throws SQLException {
		QueryRunner qr = new QueryRunner();
		BeanProcessor bean = new GenerousBeanProcessor();
		RowProcessor processor = new BasicRowProcessor(bean);
		String sql = "select * from `shoppingcart_item` inner join `item` on `shoppingcart_item`.item_id=`item`.item_id where user_id=?";

		return (List<ShoppingcartItemVO>) qr.query(ConnectionContext.getInstance().getConnection(), sql, userId,
				new BeanListHandler<ShoppingcartItemVO>(ShoppingcartItemVO.class, processor));
	}

	@Override
	public ShoppingcartItemVO find(int userId, int itemId) throws SQLException {
		QueryRunner qr = new QueryRunner();
		BeanProcessor bean = new GenerousBeanProcessor();
		RowProcessor processor = new BasicRowProcessor(bean);
		String sql = "select * from `shoppingcart_item` inner join `item` on `shoppingcart_item`.item_id=`item`.item_id  where user_id=? and item_id=? ";

		Object[] params = { userId, itemId };

		return (ShoppingcartItemVO) qr.query(ConnectionContext.getInstance().getConnection(), sql, params,
				new BeanHandler<ShoppingcartItemVO>(ShoppingcartItemVO.class, processor));

	}

}
