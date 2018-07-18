package com.coffee.web.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coffee.domain.Admin;
import com.coffee.domain.Order;
import com.coffee.domain.OrderVO;
import com.coffee.domain.Page;
import com.coffee.domain.User;
import com.coffee.service.IOrderService;
import com.coffee.service.IOrderVOService;
import com.coffee.service.IUserService;
import com.coffee.service.impl.OrderServiceImpl;
import com.coffee.service.impl.OrderVOServiceImpl;
import com.coffee.service.impl.UserServiceImpl;
import com.coffee.util.PageUtils;

/**
 * 用于获得用户的分页，每次切换页面都要访问
 * 
 * @author K
 */
@WebServlet(name = "GetHistoryOrdersPageServlet", urlPatterns = "/servlet/getHistoryOrdersPageServlet")
public class GetHistoryOrdersPageServlet extends HttpServlet {
	private IOrderService orderService = new OrderServiceImpl();
	private IOrderVOService orderVOService = new OrderVOServiceImpl();
	private IUserService userService = new UserServiceImpl();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------------GetHistoryOrdersPageServlet work start-----------");

		if (request.getSession().getAttribute("user") != null) {
			userGet(request, response);
		} else if (request.getSession().getAttribute("admin") != null) {
			try {
				adminGet(request, response);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		System.out.println("------------GetHistoryOrdersPageServlet work finished-----------");
	}

	private void userGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		Order orderFeature = new Order();
		orderFeature.setUserId(user.getUserId());
		try {
			Page<Order> page = PageUtils.getPage(request, response, 5, orderService, orderFeature);
			request.setAttribute("ordersPage", page);
			System.out.println("Get ordersPage:" + page);
			request.getRequestDispatcher("/pages/user/history-orders.jsp").forward(request, response);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void adminGet(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		OrderVO orderFeature = new OrderVO();
		String status = request.getParameter("status");
		System.out.println(status);
		if (status != null && status.equals("orderId")) {
			String orderId = request.getParameter("keyword");
			if (orderId != null) {
				orderFeature.setOrderId(Integer.parseInt(orderId));
			}
		}
		if (status != null && status.equals("account")) {
			String account = request.getParameter("keyword");
			if (account != null) {
				orderFeature.setAccount(account);
			}
		}

		try {
			Page<OrderVO> page = null;
			System.out.println(orderFeature);
			if (orderFeature.getOrderId() == 0 && orderFeature.getAccount() == null) {
				page = PageUtils.getPage(request, response, 5, orderVOService);
				System.out.println("全搜索");
			} else {
				page = PageUtils.getPage(request, response, 5, orderVOService, orderFeature);
				System.out.println("特征搜索");
			}
			request.setAttribute("ordersPage", page);
			System.out.println("Get ordersPage:" + page);
			request.getRequestDispatcher("/pages/admin/history-orders.jsp").forward(request, response);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
