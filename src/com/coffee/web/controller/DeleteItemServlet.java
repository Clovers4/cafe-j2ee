package com.coffee.web.controller;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coffee.service.IItemService;
import com.coffee.service.impl.ItemServiceImpl;

/**
 * 从数据库中删去一个商品
 * 
 * @author K
 */
@WebServlet(name = "DeleteItemServlet", urlPatterns = "/servlet/deleteItemServlet")
public class DeleteItemServlet extends HttpServlet {
	private IItemService itemService = new ItemServiceImpl();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------------DeleteItemServlet work start-----------");
		//获取request参数
		String itemId = request.getParameter("itemId");
		System.out.println("itemId:" + itemId + ",name:" + request.getParameter("name"));

		try {
			if (itemId == null || itemService.checkExist(Integer.parseInt(itemId)) == false) {
				request.setAttribute("operateError", "操作失败！！");
			} else {
				//删除操作
				itemService.delete(Integer.parseInt(itemId));
				request.setAttribute("operateSuccess", "操作成功！！");
			}
		} catch (Exception e) {
			request.setAttribute("operateError", "操作失败！！");
			throw new RuntimeException(e);
		} finally {
			System.out.println("------------DeleteItemServlet work finished-----------");
			request.getRequestDispatcher("/pages/admin/manage-items.jsp").forward(request, response);
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
