package com.coffee.web.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.coffee.util.WebUtils;

/**
 * 现用于manage-items的页面。最后希望做成获取itemsPage的然后返回之前的网页 方法一：filter，貌似不行
 * 方法而：将通用代码放入util中，做成泛型
 * 
 * 用于获得Item分页，每次切换页面都要访问
 * 
 * @author K
 */
@WebServlet(name = "GetItemsPageServlet", urlPatterns = "/servlet/getItemsPageServlet")
public class GetItemsPageServlet extends HttpServlet {
	private IItemService itemService = new ItemServiceImpl();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------------GetItemsPageServlet work start-----------");
		try {
			Page<Item> page = PageUtils.getPage(request, response, 5, itemService);
			request.setAttribute("itemsPage", page);
			System.out.println("Get ItemsPage:"+page);
			request.getRequestDispatcher("/pages/admin/manage-items.jsp").forward(request, response);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		System.out.println("------------GetItemsPageServlet work finished-----------");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
