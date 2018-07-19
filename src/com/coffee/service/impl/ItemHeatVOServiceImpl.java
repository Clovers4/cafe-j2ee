package com.coffee.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.coffee.dao.IItemHeatVODao;
import com.coffee.dao.impl.ItemHeatVODaoImpl;
import com.coffee.domain.ItemHeatVO;
import com.coffee.service.IItemHeatVOService;

/**
 * @InterfaceName: ItemHeatVOServiceImpl
 * @Description:Service层,IItemHeatVOService的实现类，可以获取一个ItemHeatVO的列表，来供前端使用
 * 
 * @author: K
 */
public class ItemHeatVOServiceImpl implements IItemHeatVOService {
	private IItemHeatVODao itemHeatVODao = new ItemHeatVODaoImpl();

	@Override
	public List<ItemHeatVO> getAll() throws SQLException {
		return itemHeatVODao.findAll();
	}

}
