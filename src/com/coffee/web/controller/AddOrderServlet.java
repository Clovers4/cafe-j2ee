package com.coffee.web.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coffee.domain.Order;
import com.coffee.domain.User;
import com.coffee.service.IOrderService;
import com.coffee.service.IShoppingcartItemService;
import com.coffee.service.impl.OrderServiceImpl;
import com.coffee.service.impl.ShoppingcartItemServiceImpl;

/**
 * 生成订单，订单详情，库存减少等
 * 
 * @author K
 *
 */

@WebServlet(name = "AddOrderServlet", urlPatterns = "/servlet/addOrderServlet")
public class AddOrderServlet extends HttpServlet {
	private IOrderService orderService = new OrderServiceImpl();
	private IShoppingcartItemService shoppingcartItemService = new ShoppingcartItemServiceImpl();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------------AddOrderServlet work start-----------");
		// 设置订单详情
		Order order = new Order();
		User user = (User) request.getSession().getAttribute("user");
		try {
			order.setOrderTotal(shoppingcartItemService.sumTotalMoney(user.getUserId()));
		} catch (SQLException e) {
			request.setAttribute("operateError", "下单失败！！");
			throw new RuntimeException(e);
		}
		order.setUserId(user.getUserId());
		order.setCreatedTime(new Date());
		System.out.println(order);

		try {
			// 下订单
			orderService.add(order);
			// 回显
			request.setAttribute("operateSuccess", "下单成功！！");
			request.getRequestDispatcher("/servlet/getShoppingcartServlet").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("operateError", "下单失败，餐点库存可能不够了！！");
			request.getRequestDispatcher("/servlet/getShoppingcartServlet").forward(request, response);
			throw new RuntimeException(e);
		}
		System.out.println("------------AddOrderServlet work finished-----------");

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
