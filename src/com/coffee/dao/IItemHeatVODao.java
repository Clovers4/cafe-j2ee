package com.coffee.dao;

import java.sql.SQLException;
import java.util.List;

import com.coffee.domain.ItemHeatVO;

/**
 * @InterfaceName: IItemHeatVODao
 * @Description:DAO层接口，提供查询item表与order_detail表的多表连接的结果，用于展示热度餐点的详细信息。由于order_detail没有item的详细信息，因此需要多表连接
 * 
 * @author: K
 */
public interface IItemHeatVODao {
	/**
	 * 查找所有的ItemHeatVO，即order_detail与item表连接后的，包含有订单小件数量，名称等信息的对象List.
	 * 
	 * @return
	 * @throws SQLException
	 */
	List<ItemHeatVO> findAll() throws SQLException;
}
