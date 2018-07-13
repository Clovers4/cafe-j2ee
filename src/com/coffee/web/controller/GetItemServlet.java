package com.coffee.web.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coffee.domain.Item;
import com.coffee.service.IItemService;
import com.coffee.service.impl.ItemServiceImpl;

/**
 * 用于获得Item，来在商品详情页面上显示
 * 
 * @author K
 */
@WebServlet(name = "GetItemServlet", urlPatterns = "/servlet/getItemServlet")
public class GetItemServlet extends HttpServlet {
	private IItemService itemService = new ItemServiceImpl();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------------GetItemServlet work start-----------");
		int itemId=getItemId(request);
		System.out.println(itemId);
		Item item=null;
		try {
			item=itemService.get(itemId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		request.setAttribute("item", item);
		System.out.println("item:"+item);
		request.getRequestDispatcher("/item.jsp").forward(request, response);
		
		System.out.println("------------GetItemServlet work finished-----------");
	}

	private int getItemId(HttpServletRequest request) {
		String itemId = request.getParameter("itemId");
		if (itemId == null) {
			itemId = "0";
		}
		return Integer.parseInt(itemId);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
