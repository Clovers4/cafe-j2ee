package com.coffee.web.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coffee.domain.ItemHeatVO;
import com.coffee.service.IItemHeatVOService;
import com.coffee.service.impl.ItemHeatVOServiceImpl;

/**
 * 用于获得Item热度
 * 
 * @author K
 */
@WebServlet(name = "GetItemHeatServlet", urlPatterns = "/servlet/getItemHeatServlet")
public class GetItemHeatServlet extends HttpServlet {
	private IItemHeatVOService itemHeatVOService = new ItemHeatVOServiceImpl();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------------GetItemHeatServlet work start-----------");

		List<ItemHeatVO> list;
		try {
			list = itemHeatVOService.getAll();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		request.setAttribute("items", list);
		System.out.println(list);
		request.getRequestDispatcher("/pages/admin/items-heat.jsp").forward(request, response);

		System.out.println("------------GetItemHeatServlet work finished-----------");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
