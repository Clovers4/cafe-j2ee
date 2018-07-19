package com.coffee.web.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coffee.domain.OrderDetailVO;
import com.coffee.domain.ShoppingcartItemVO;
import com.coffee.service.IOrderService;
import com.coffee.service.impl.OrderServiceImpl;

/**
 * 用于获得订单详情，来在查看历史订单页面上显示
 * 
 * @author K
 */
@WebServlet(name = "GetOrderDetailServlet", urlPatterns = "/servlet/getOrderDetailServlet")
public class GetOrderDetailServlet extends HttpServlet {
	private IOrderService orderService = new OrderServiceImpl();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------------GetOrderDetailServlet work start-----------");

		int orderId = Integer.parseInt(request.getParameter("orderId"));
		try {
			//通过orderId，来获取一个该订单中的小件详情.
			List<OrderDetailVO> list = orderService.getOrderDetailVOByOrderId(orderId);
			System.out.println(list);
			
			// 获取订单总价
			double total = 0;
			for (OrderDetailVO item : list) {
				total += item.getPrice() * item.getNumber();
			}

			//回显
			request.setAttribute("total", total);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/servlet/getHistoryOrdersPageServlet").forward(request, response);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		System.out.println("------------GetOrderDetailServlet work finished-----------");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
