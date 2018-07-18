package com.coffee.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coffee.dao.IItemDao;
import com.coffee.dao.IOrderDao;
import com.coffee.dao.IOrderDetailDao;
import com.coffee.dao.IOrderDetailVODao;
import com.coffee.dao.IOrderVODao;
import com.coffee.dao.IShoppingcartItemDao;
import com.coffee.dao.impl.ItemDaoImpl;
import com.coffee.dao.impl.OrderDaoImpl;
import com.coffee.dao.impl.OrderDetailDaoImpl;
import com.coffee.dao.impl.OrderDetailVODaoImpl;
import com.coffee.dao.impl.OrderVODaoImpl;
import com.coffee.dao.impl.ShoppingcartItemDaoImpl;
import com.coffee.domain.Item;
import com.coffee.domain.Order;
import com.coffee.domain.OrderDetail;
import com.coffee.domain.OrderDetailVO;
import com.coffee.domain.OrderVO;
import com.coffee.domain.Page;
import com.coffee.domain.ShoppingcartItem;
import com.coffee.service.IOrderService;

/**
 * @InterfaceName: OrderServiceImpl
 * @Description:Service层,IOrderService的实现类，提供下订单（库存修改，验证，订单生成，详情生成），查询的功能
 * 
 * @author: K
 */
public class OrderServiceImpl implements IOrderService {
	private IItemDao itemDao = new ItemDaoImpl();
	private IShoppingcartItemDao shoppingcartItemDao = new ShoppingcartItemDaoImpl();
	private IOrderDao orderDao = new OrderDaoImpl();
	private IOrderDetailDao orderDetailDao = new OrderDetailDaoImpl();
	private IOrderDetailVODao orderDetailVODao=new OrderDetailVODaoImpl();

	@Override
	public void add(Order order) throws Exception {
		int orderId = orderDao.insert(order).intValue();
		List<ShoppingcartItem> shoppingcartItems = shoppingcartItemDao.find(order.getUserId());
		for (ShoppingcartItem shoppingcartItem : shoppingcartItems) {
			// 插入到订单详情表
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrderId(orderId);
			orderDetail.setItemId(shoppingcartItem.getItemId());
			orderDetail.setNumber(shoppingcartItem.getNumber());
			orderDetailDao.insert(orderDetail);
			// 库存减少
			Item item = itemDao.find(shoppingcartItem.getItemId());
			item.setStock(item.getStock() - shoppingcartItem.getNumber());
			if (item.getStock() < 0) {
				// 库存不足，抛出异常，回滚事务
				throw new RuntimeException();
			}
			itemDao.update(item);
			// 从购物车中去除
			shoppingcartItemDao.delete(shoppingcartItem.getUserId(), shoppingcartItem.getItemId());

		}

	}

	@Override
	public List<Order> getAll() throws SQLException {
		return (ArrayList<Order>) orderDao.findAll();
	}

	@Override
	public List<Order> get(Order orderFeature) throws SQLException {
		return (ArrayList<Order>) orderDao.find(orderFeature);
	}

	@Override
	public Page<Order> get(int begin, int pageSize) throws SQLException {
		List<Order> list = (ArrayList<Order>) orderDao.find(begin, pageSize);

		Page<Order> orderPage = new Page<>();
		orderPage.setList(list);
		orderPage.setTotalCount(orderDao.findAll().size());

		return orderPage;
	}

	@Override
	public Page<Order> get(Order orderFeature, int begin, int pageSize) throws SQLException {
		List<Order> list = (ArrayList<Order>) orderDao.find(orderFeature, begin, pageSize);

		Page<Order> orderPage = new Page<>();
		orderPage.setList(list);
		orderPage.setTotalCount(orderDao.find(orderFeature).size());

		return orderPage;
	}

	@Override
	public List<OrderDetailVO> getOrderDetailVOByOrderId(int orderId) throws SQLException {
		return orderDetailVODao.findByOrderId(orderId);
	}



}
