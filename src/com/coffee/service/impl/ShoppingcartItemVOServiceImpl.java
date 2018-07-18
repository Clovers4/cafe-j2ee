package com.coffee.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.coffee.dao.IShoppingcartItemVODao;
import com.coffee.dao.impl.ShoppingcartItemDaoImpl;
import com.coffee.dao.impl.ShoppingcartItemVODaoImpl;
import com.coffee.domain.ShoppingcartItemVO;
import com.coffee.service.IShoppingcartItemVOService;

/**
 * @InterfaceName: ShoppingcartItemVOServiceImpl
 * @Description:Service层,IShoppingcartItemVOService的实现类，提供添加商品，修改，删除等服务。
 * 
 * @author: K
 */
public class ShoppingcartItemVOServiceImpl implements IShoppingcartItemVOService {
	private IShoppingcartItemVODao dao = new ShoppingcartItemVODaoImpl();

	@Override
	public List<ShoppingcartItemVO> get(int userId) throws SQLException {
		return dao.find(userId);
	}

	@Override
	public ShoppingcartItemVO get(int userId, int itemId) throws SQLException {
		return dao.find(userId, itemId);
	}

}
