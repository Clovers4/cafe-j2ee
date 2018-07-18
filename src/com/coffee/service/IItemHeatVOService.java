package com.coffee.service;

import java.sql.SQLException;
import java.util.List;

import com.coffee.domain.ItemHeatVO;

public interface IItemHeatVOService {
	/**
	 * 获得所有ItemHeatVO，用于在网页端显示
	 * 
	 * @return
	 * @throws SQLException
	 */
	List<ItemHeatVO> getAll() throws SQLException;
}
