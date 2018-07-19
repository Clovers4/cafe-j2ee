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
 * 用户注册
 * 
 * @author K
 */
@WebServlet(name = "RegisterServlet", urlPatterns = "/servlet/registerServlet")
public class RegisterServlet extends HttpServlet {
	private IUserService userService = new UserServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取表单
		RegisterFormBean formBean = WebUtils.requestToBean(request, RegisterFormBean.class);
		System.out.println(formBean);

		// 由于各个页面都有header，随时可以注册，注册完后需要重定向回去,
		// 但直接使用request中的refer参数重定向的话，无法实现连续注册（url没有变化）
		// 获取回送地址，之后重定向到该地址
		String forwardUrl = request.getParameter("forwardUrl");
		System.out.println("forwardUrl: " + forwardUrl);

		// 校验用户注册填写的表单数据
		if (formBean.validate() == false) {
			request.setAttribute("registerFormBean", formBean);
			request.setAttribute("registerError", "未全部填写/填写内容不符合要求！！");
			request.getRequestDispatcher(forwardUrl).forward(request, response);
			return;
		}

		// 注册
		User user = new User();
		try {
			// 表单数据拷贝
			BeanUtils.copyProperties(user, formBean);

			userService.register(user);

			request.setAttribute("registerSuccess", "注册成功！！");
			request.getRequestDispatcher(forwardUrl).forward(request, response);
		} catch (UserExistException e) {
			request.setAttribute("registerError", "用户名重复,请更换一个用户名！！");
			request.getRequestDispatcher(forwardUrl).forward(request, response);
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