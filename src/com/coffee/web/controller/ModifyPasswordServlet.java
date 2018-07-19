package com.coffee.web.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import com.coffee.domain.User;
import com.coffee.service.IUserService;
import com.coffee.service.impl.UserServiceImpl;
import com.coffee.util.WebUtils;
import com.coffee.web.formbean.LoginFormBean;
import com.coffee.web.formbean.ModifyPasswordFormBean;

/**
 * 修改用户密码
 * 
 * @author K
 */
@WebServlet(name = "ModifyPasswordServlet", urlPatterns = "/servlet/modifyPasswordServlet")
public class ModifyPasswordServlet extends HttpServlet {
	private IUserService service = new UserServiceImpl();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 读取表单信息
		ModifyPasswordFormBean formBean = WebUtils.requestToBean(request, ModifyPasswordFormBean.class);
		System.out.println("------------ModifyPasswordServlet work start-----------");
		System.out.println(formBean);

		// 校验表单数据
		if (formBean.validate() == false) {
			System.out.println("URL: " + request.getParameter("url"));
			request.setAttribute("modifyPasswordFormBean", formBean);
			request.setAttribute("modifyPasswordError", "未全部填写/填写内容不符合要求！！");
			request.getRequestDispatcher("/user/modify-password.jsp").forward(request, response);
			return;
		}

		// 修改密码
		if (request.getSession().getAttribute("user") != null) {
			userModifyPassword(request, response, formBean);
		}

		// 回显
		request.setAttribute("modifyPasswordSuccess", "修改成功！！");
		request.getRequestDispatcher("/pages/user/modify-password.jsp").forward(request, response);
	}

	private void userModifyPassword(HttpServletRequest request, HttpServletResponse response,
			ModifyPasswordFormBean formBean) throws ServletException, IOException {
		// 从session中读取user
		User user = (User) request.getSession().getAttribute("user");
		try {
			// 拷贝字段
			BeanUtils.copyProperties(user, formBean);
			System.out.println(user);

			// 调用service层提供的注册用户服务实现用户修改
			service.update(user);
			request.getSession().setAttribute("user", user);
			resetAutoLoginCookie(request, response, user.getPassword());

		} catch (Exception e) {
			request.setAttribute("message", "对不起，修改失败！！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			throw new RuntimeException(e);
		}

	}

	/**
	 * @Method: sendAutoLoginCookie
	 * @Description: 设置新的密码，发送自动登录cookie给客户端浏览器
	 *
	 * @param request
	 * @param response
	 * @param formBean
	 */
	private void resetAutoLoginCookie(HttpServletRequest request, HttpServletResponse response, String password) {
		// 1.得到用户带过来的autologin的cookie
		String value = null;
		int logintime = 0;
		Cookie cookies[] = request.getCookies();
		for (int i = 0; cookies != null && i < cookies.length; i++) {
			if (cookies[i].getName().equals("autologin")) {
				value = cookies[i].getValue();
				logintime = cookies[i].getMaxAge();
			}
		}

		// 2.得到 cookie中的账号和密码和用户/管理员,没有的话filter的任务就结束了.
		if (value != null) {
			String account = value.split("\\.")[0];
			String status = value.split("\\.")[2];
			if (logintime != 0) {
				// 创建cookie,名字是autologin，值是用户登录的用户名和密码，用户名和密码之间使用.进行分割，密码明文，方便查错且非正规网站，就不做加密处理了
				Cookie cookie = new Cookie("autologin", account + "." + password + "." + status);

				cookie.setMaxAge(logintime);// 设置cookie的有效期
				cookie.setPath(request.getContextPath());// 设置cookie的有效路径
				response.addCookie(cookie);// 将cookie写入到客户端浏览器
			}
		}

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}