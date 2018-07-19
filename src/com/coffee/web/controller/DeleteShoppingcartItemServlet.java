package com.coffee.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.coffee.domain.ShoppingcartItem;
import com.coffee.service.IShoppingcartItemService;
import com.coffee.service.impl.ShoppingcartItemServiceImpl;
import com.coffee.util.WebUtils;
import com.coffee.web.formbean.ShoppingcartItemFormBean;

/**
 * 移除购物车中的商品
 * 
 * @author K
 */
@WebServlet(name = "DeleteShoppingcartItemServlet", urlPatterns = "/servlet/deleteShoppingcartItemServlet")
public class DeleteShoppingcartItemServlet extends HttpServlet {
	private IShoppingcartItemService service = new ShoppingcartItemServiceImpl();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------------DeleteShoppingcartItemServlet work start-----------");

		try {
			service.delete(Integer.parseInt(request.getParameter("userId")),
					Integer.parseInt(request.getParameter("itemId")));
		} catch (Exception e) {
			request.setAttribute("operateError", "移除失败！！");
			throw new RuntimeException(e);
		}

		request.setAttribute("operateSuccess", "移除成功！！");
		request.getRequestDispatcher("/servlet/getShoppingcartServlet").forward(request, response);

		System.out.println("------------DeleteShoppingcartItemServlet work finished-----------");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
