package com.coffee.web.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coffee.domain.User;
import com.coffee.service.IUserService;
import com.coffee.service.impl.UserServiceImpl;

/**
 * 获取一个包含所有User的列表，之前用来做无分页的用户管理，已弃用
 * 
 * @author K
 */
@WebServlet(name = "GetAllUsersServlet", urlPatterns = "/servlet/getAllUsersServlet")
public class GetAllUsersServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------------GetAllUsersServlet work start-----------");

		IUserService service = new UserServiceImpl();
		List<User> users = null;
		try {
			users = service.getAll();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		if (users != null) {
			request.getSession().setAttribute("users", users);
			System.out.println("--------Get All Users succeed-----------");
			response.sendRedirect(request.getContextPath() + "/pages/admin/manage-users.jsp");
		}else {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
		System.out.println("------------GetAllUsersServlet work finished-----------");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
