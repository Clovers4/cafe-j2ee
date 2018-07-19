package com.coffee.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coffee.dao.IOrderDetailVODao;
import com.coffee.dao.IOrderVODao;
import com.coffee.dao.impl.OrderDetailVODaoImpl;
import com.coffee.dao.impl.OrderVODaoImpl;
import com.coffee.domain.Order;
import com.coffee.domain.OrderDetailVO;
import com.coffee.domain.OrderVO;
import com.coffee.domain.Page;
import com.coffee.service.IOrderVOService;

/**
 * @InterfaceName: OrderVOServiceImpl
 * @Description:Service层,IOrderVOService的实现类，提供获取OrderVO的分页对象，以在前端显示
 * 
 * @author: K
 */
public class OrderVOServiceImpl implements IOrderVOService {
	private IOrderVODao orderVODao = new OrderVODaoImpl();
	private IOrderDetailVODao orderDetailVODao = new OrderDetailVODaoImpl();

	@Override
	public Page<OrderVO> get(int begin, int pageSize) throws SQLException {
		List<OrderVO> list = (ArrayList<OrderVO>) orderVODao.find(begin, pageSize);

		Page<OrderVO> orderPage = new Page<>();
		orderPage.setList(list);
		orderPage.setTotalCount(orderVODao.findAll().size());

		return orderPage;
	}

	@Override
	public Page<OrderVO> get(OrderVO orderFeature, int begin, int pageSize) throws SQLException {
		List<OrderVO> list = (ArrayList<OrderVO>) orderVODao.find(orderFeature, begin, pageSize);

		Page<OrderVO> orderPage = new Page<>();
		orderPage.setList(list);
		orderPage.setTotalCount(orderVODao.find(orderFeature).size());

		return orderPage;
	}

}
