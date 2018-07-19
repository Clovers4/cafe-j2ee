package com.coffee.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.coffee.dao.IItemHeatVODao;
import com.coffee.domain.Item;
import com.coffee.domain.ItemHeatVO;
import com.coffee.util.ConnectionContext;

/**
 * @ClassName: ItemDaoImpl
 * @Description:DAO层,IItemDao的实现类，提供查询item表与order_detail表的多表连接结果，用于展示热度餐点的详细信息。由于order_detail没有item的详细信息，因此需要多表连接
 * 
 * @author: K
 */
public class ItemHeatVODaoImpl implements IItemHeatVODao {

	@Override
	public List<ItemHeatVO> findAll() throws SQLException {
		QueryRunner qr = new QueryRunner();
		BeanProcessor bean = new GenerousBeanProcessor();
		RowProcessor processor = new BasicRowProcessor(bean);
		String sql = "select name,type,CONVERT(sum(number)/dt.total,DECIMAL(10,2)) percent "
				+ "from `order_detail` inner join `item` on `order_detail`.item_id=`item`.item_id join (select sum(number) total from `order_detail`) dt "
				+ "group by `order_detail`.item_id " + "order by percent desc;";

		return (List<ItemHeatVO>) qr.query(ConnectionContext.getInstance().getConnection(), sql,
				new BeanListHandler<ItemHeatVO>(ItemHeatVO.class, processor));
	}

}
