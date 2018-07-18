package com.coffee.web.controller;

import java.io.IOException;
import java.util.Date;

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
 * 处理添加商品到购物车的Servlet
 * 
 * @author K
 *
 */

@WebServlet(name = "AddShoppingcartItemServlet", urlPatterns = "/servlet/addShoppingcartItemServlet")
public class AddShoppingcartItemServlet extends HttpServlet {
	private IShoppingcartItemService service = new ShoppingcartItemServiceImpl();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------------AddItemToShoppingcartServlet work start-----------");
		ShoppingcartItemFormBean formBean = WebUtils.requestToBean(request, ShoppingcartItemFormBean.class);
		System.out.println(formBean);

		ShoppingcartItem item = new ShoppingcartItem();
		try {
			BeanUtils.copyProperties(item, formBean);
			service.add(item);
			System.out.println(item);

		} catch (Exception e) {
			request.setAttribute("operateError", "添加失败！！");
			throw new RuntimeException(e);
		}

		request.setAttribute("operateSuccess", "添加成功！！");
		request.getRequestDispatcher("/servlet/getItemServlet?itemId=" + formBean.getItemId()).forward(request,
				response);

		System.out.println("------------AddItemToShoppingcartServlet work finished-----------");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
