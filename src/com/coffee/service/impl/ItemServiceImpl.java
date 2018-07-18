package com.coffee.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coffee.dao.IItemDao;
import com.coffee.dao.impl.ItemDaoImpl;
import com.coffee.domain.Item;
import com.coffee.domain.Page;
import com.coffee.service.IItemService;

/**
 * @InterfaceName: ItemServiceImpl
 * @Description:Service层,IItemService的实现类，提供添加商品，修改，删除等服务。
 * 
 * @author: K
 */
public class ItemServiceImpl implements IItemService {
	private IItemDao itemDao = new ItemDaoImpl();

	@Override
	public void add(Item item) throws Exception {
		itemDao.insert(item);
	}

	@Override
	public void update(Item item) throws SQLException {
		itemDao.update(item);
	}

	@Override
	public void delete(int itemId) throws SQLException {
		itemDao.delete(itemId);

	}

	@Override
	public List<Item> getAll() throws SQLException {
		List<Item> list = (ArrayList<Item>) itemDao.findAll();
		return list;
	}
	

	@Override
	public Item get(int itemId) throws SQLException {
		return itemDao.find(itemId);
	}


	@Override
	public Page<Item> get(int begin, int pageSize) throws SQLException {
		List<Item> list = (ArrayList<Item>) itemDao.find(begin, pageSize);

		Page<Item> itemPage = new Page<>();
		itemPage.setList(list);
		itemPage.setTotalCount(itemDao.findAll().size());

		return itemPage;
	}

	@Override
	public Page<Item> get(Item item, int begin, int pageSize) throws SQLException {
		List<Item> list = (ArrayList<Item>) itemDao.find(item, begin, pageSize);

		Page<Item> itemPage = new Page<>();
		itemPage.setList(list);
		itemPage.setTotalCount(itemDao.find(item).size());

		return itemPage;
	}

	@Override
	public boolean checkExist(int itemId) throws SQLException {
		if (itemDao.find(itemId) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void update(int itemId, String imageUrl) throws SQLException {
		Item item = itemDao.find(itemId);
		if (item != null) {
			item.setImageUrl(imageUrl);
			itemDao.update(item);
		}
	}

}
