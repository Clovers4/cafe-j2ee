package com.coffee.web.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coffee.service.IUserService;
import com.coffee.service.impl.UserServiceImpl;

/**
 * 移除一个User
 * 
 * @author K
 */
@WebServlet(name = "DeleteUserServlet", urlPatterns = "/servlet/deleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	private IUserService userService = new UserServiceImpl();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------------DeleteUserServlet work start-----------");
		String account = request.getParameter("account");
		System.out.println(account);

		// 账号不存在，或者SQl有问题:失败
		try {
			if (account == null || userService.checkExist(account) == false) {
				request.setAttribute("operateError", "操作失败！！");
			} else {
				userService.delete(account);
				request.setAttribute("operateSuccess", "操作成功！！");
			}
		} catch (SQLException e) {
			request.setAttribute("operateError", "操作失败！！");
			throw new RuntimeException(e);
		} finally {
			System.out.println("------------DeleteUserServlet work finished-----------");
			request.getRequestDispatcher("/pages/admin/manage-users.jsp").forward(request, response);
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
