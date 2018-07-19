package com.coffee.web.UI;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * 为用户提供注册的用户界面的Servlet
 * 
 * RegisterUIServlet负责为用户输出注册界面
 * 当用户访问RegisterUIServlet时，就跳转到WEB-INF/pages目录下的register.jsp页面
 * 
 * 实际上没有用到
 * 
 * @author K
 */
@WebServlet(name ="RegisterUIServlet",urlPatterns = "/register")
public class RegisterUIServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/register.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}