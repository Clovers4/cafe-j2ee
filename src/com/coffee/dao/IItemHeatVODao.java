package com.coffee.dao;

import java.sql.SQLException;
import java.util.List;

import com.coffee.domain.ItemHeatVO;

public interface IItemHeatVODao {
	/**
	 * 查找所有的ItemHeatVO
	 * 
	 * @return
	 * @throws SQLException
	 */
	List<ItemHeatVO> findAll() throws SQLException;
}
