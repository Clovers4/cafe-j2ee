package com.coffee.web.controller;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import org.apache.commons.beanutils.*;
import org.apache.commons.beanutils.locale.converters.*;

import com.coffee.domain.*;
import com.coffee.exception.UserExistException;
import com.coffee.service.IUserService;
import com.coffee.service.impl.UserServiceImpl;
import com.coffee.util.*;
import com.coffee.web.formbean.*;

/**
 * 处理用户注册的Servlet
 * 
 * @author K
 *
 */

@WebServlet(name = "RegisterServlet", urlPatterns = "/servlet/registerServlet")
public class RegisterServlet extends HttpServlet {
	private IUserService userService = new UserServiceImpl();

	/**
	 * 初始化连接池，初始化的位置有点问题，似乎是电脑重启之后就需要重新初始化连接池，而之后即使修改代码，也不会影响
	 */
	@Override
	public void init() throws ServletException {
		super.init();

		Connection connection = null;
		try {
			connection = JdbcUtils.getConnection();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 将客户端提交的表单数据封装到RegisterFormBean对象中
		RegisterFormBean formBean = WebUtils.requestToBean(request, RegisterFormBean.class);
		System.out.println(formBean);
		String orgUrl = WebUtils.getOrgServletPath(request);
		if (orgUrl.equals("/servlet/registerServlet")) {
			orgUrl = "/index.jsp";
		}
		System.out.println("OrgURL: " + orgUrl);

		// 校验用户注册填写的表单数据
		if (formBean.validate() == false) {// 如果校验失败
			// 将封装了用户填写的表单数据的formBean对象发送回register.jsp页面的form表单中进行显示
			request.setAttribute("registerFormBean", formBean);
			// 校验失败就说明是用户填写的表单数据有问题，那么就跳转刚才的页面

			request.setAttribute("registerError", "未全部填写/填写内容不符合要求！！");
			response.sendRedirect(request.getContextPath() + orgUrl);
			return;
		}

		User user = new User();
		try {
			// FormBean转domain
			ConvertUtils.register(new DateLocaleConverter(), Date.class);// 注册字符串到日期的转换器
			BeanUtils.copyProperties(user, formBean);
			System.out.println(user);

			userService.register(user);

			request.setAttribute("registerSuccess", "注册成功！！");
			request.getRequestDispatcher(orgUrl).forward(request, response);

		} catch (UserExistException e) {
			request.setAttribute("registerError", "用户名重复,请更换一个用户名！！");
			request.getRequestDispatcher(orgUrl).forward(request, response);
			throw e;
		} catch (Exception e) {
			request.setAttribute("message", "对不起，注册失败！！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			throw new RuntimeException(e);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}