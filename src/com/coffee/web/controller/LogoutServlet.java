package com.coffee.web.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * 登录操作，删除session信息，移除自动登录cookie
 * 
 * @author K
 */
@WebServlet(name = "LogoutServlet", urlPatterns = "/servlet/logoutServlet")
public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 移除存储在session中的user/admin
		request.getSession().removeAttribute("user");
		request.getSession().removeAttribute("admin");
		// 移除自动登录的cookie
		removeAutoLoginCookie(request, response);
		System.out.println("-------Logout succeed-------");
		// 注销用户后跳转到主页
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	/**
	 * @Method: removeAutoLoginCookie
	 * @Description: 删除自动登录cookie，
	 *               JavaWeb中删除cookie的方式就是新创建一个cookie，新创建的cookie与要删除的cookie同名，
	 *               设置新创建的cookie的cookie的有效期设置为0，有效路径与要删除的cookie的有效路径相同
	 *
	 * @param request
	 * @param response
	 */
	private void removeAutoLoginCookie(HttpServletRequest request, HttpServletResponse response) {
		// 创建一个名字为autologin的cookie
		Cookie cookie = new Cookie("autologin", "");
		// 将cookie的有效期设置为0，命令浏览器删除该cookie
		cookie.setMaxAge(0);
		// 设置要删除的cookie的path
		cookie.setPath(request.getContextPath());
		//添加到response中，由于有效期为0，起到删除效果
		response.addCookie(cookie);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}