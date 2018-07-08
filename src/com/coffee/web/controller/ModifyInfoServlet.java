package com.coffee.web.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
import com.coffee.web.formbean.ModifyInfoFormBean;

@WebServlet(name = "ModifyInfoServlet", urlPatterns = "/servlet/modifyInfoServlet")
public class ModifyInfoServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModifyInfoFormBean formBean = WebUtils.requestToBean(request, ModifyInfoFormBean.class);
		System.out.println("------------ModifyInfoServlet work start-----------");
		System.out.println(formBean);

		if (request.getSession().getAttribute("user") != null) {
			userModifyInfo(request, response, formBean);
		}
		request.setAttribute("modifyInfoSuccess", "修改成功！！");
		request.getRequestDispatcher("/pages/user/modify-info.jsp").forward(request, response);
	}

	private void userModifyInfo(HttpServletRequest request, HttpServletResponse response, ModifyInfoFormBean formBean)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		// FormBean转domain
		try {
			ConvertUtils.register(new DateLocaleConverter(), Date.class);// 注册字符串到日期的转换器
			BeanUtils.copyProperties(user, formBean);
			System.out.println(user);

			// 调用service层提供的注册用户服务实现用户修改
			IUserService service = new UserServiceImpl();
			service.update(user);
			request.getSession().setAttribute("user", user);

		} catch (Exception e) {
			e.printStackTrace(); // 在后台记录异常
			request.setAttribute("message", "对不起，修改失败！！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			throw new RuntimeException(e);
		}

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
