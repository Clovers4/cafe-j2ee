package com.coffee.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.coffee.dao.IItemDao;
import com.coffee.dao.IShoppingcartItemDao;
import com.coffee.dao.impl.ItemDaoImpl;
import com.coffee.dao.impl.ShoppingcartItemDaoImpl;
import com.coffee.domain.Item;
import com.coffee.domain.ShoppingcartItem;
import com.coffee.service.IShoppingcartItemService;

/**
 * @InterfaceName: ShoppingcartItemServiceImpl
 * @Description:Service层,IShoppingcartItemService的实现类，提供将餐点添加到购物车 / 修改餐点数量（更新）/
 *                                                                从购物车中删除该餐点 /
 *                                                                得到所有用户购物车餐点列表/
 *                                                                得到某个用户购物车餐点列表/
 *                                                                得到某个用户的购物车总金额/
 *                                                                获取某个ShoppingcartItem对象/
 *                                                                检查某用户购物车内是否已经存在某商品
 *                                                                等功能
 * 
 * @author: K
 */
public class ShoppingcartItemServiceImpl implements IShoppingcartItemService {
	private IShoppingcartItemDao shoppingcartItemDao = new ShoppingcartItemDaoImpl();
	private IItemDao itemDao = new ItemDaoImpl();

	@Override
	public void add(ShoppingcartItem item) throws Exception {
		ShoppingcartItem orgItem = shoppingcartItemDao.find(item.getUserId(), item.getItemId());
		if (orgItem == null) {
			shoppingcartItemDao.insert(item);
		} else {
			item.setNumber(item.getNumber() + orgItem.getNumber());
			shoppingcartItemDao.update(item);
		}
	}

	@Override
	public void update(ShoppingcartItem item) throws SQLException {
		shoppingcartItemDao.update(item);
	}

	@Override
	public void delete(int userId, int itemId) throws SQLException {
		shoppingcartItemDao.delete(userId, itemId);
	}

	@Override
	public List<ShoppingcartItem> getAll() throws SQLException {
		return shoppingcartItemDao.findAll();
	}

	@Override
	public List<ShoppingcartItem> get(int userId) throws SQLException {
		return shoppingcartItemDao.find(userId);
	}

	@Override
	public double sumTotalMoney(int userId) throws SQLException {
		List<ShoppingcartItem> list = shoppingcartItemDao.find(userId);
		double total = 0;
		for (ShoppingcartItem item : list) {
			total += item.getNumber() * itemDao.find(item.getItemId()).getPrice();
		}
		return total;
	}

	@Override
	public ShoppingcartItem get(int userId, int itemId) throws SQLException {
		return shoppingcartItemDao.find(userId, itemId);
	}

	@Override
	public boolean checkExist(int userId, int itemId) throws SQLException {
		if (shoppingcartItemDao.find(userId, itemId) != null) {
			return true;
		} else {
			return false;
		}
	}

}
