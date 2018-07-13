package com.coffee.web.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coffee.domain.Item;
import com.coffee.domain.Page;
import com.coffee.service.IItemService;
import com.coffee.service.impl.ItemServiceImpl;
import com.coffee.util.PageUtils;
import com.mchange.v2.sql.filter.FilterResultSet;

/**
 * 搜索商品，通过商品名、类型
 * 
 * @author K
 *
 */

@WebServlet(name = "SearchItemPageServlet", urlPatterns = "/servlet/searchItemPageServlet")
public class SearchItemPageServlet extends HttpServlet {
	private IItemService itemService = new ItemServiceImpl();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------------SearchItemPageServlet work start-----------");
		String nameKeyword = request.getParameter("nameKeyword");
		String type = request.getParameter("type");
		if (nameKeyword == null) {
			nameKeyword = "";
		}
		if (type == null) {
			type = "";
		}
		System.out.println("nameKeyword=" + nameKeyword + ",type=" + type);

		try {
			// 设置要搜索的特征对象
			Item featureItem = new Item();
			featureItem.setName(nameKeyword);
			featureItem.setType(type);
			// 通过service和特征对象进行搜索
			Page<Item> page = PageUtils.getPage(request, response, 6, itemService, featureItem);

			request.setAttribute("itemsPage", page);
			System.out.println("Get ItemsPage:" + page);
			request.getRequestDispatcher("/search-items.jsp").forward(request, response);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		System.out.println("------------SearchItemPageServlet work finished-----------");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
