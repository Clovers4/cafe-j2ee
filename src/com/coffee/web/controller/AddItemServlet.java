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

import com.coffee.domain.Item;
import com.coffee.service.IItemService;
import com.coffee.service.impl.ItemServiceImpl;
import com.coffee.util.WebUtils;
import com.coffee.web.formbean.AddItemFormBean;

/**
 * 添加餐点到数据库
 * 
 * @author K
 */
@WebServlet(name = "AddItemServlet", urlPatterns = "/servlet/addItemServlet")
public class AddItemServlet extends HttpServlet {
	private IItemService itemService = new ItemServiceImpl();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------------AddItemServlet work start-----------");
		// 封装表单
		AddItemFormBean formBean = WebUtils.requestToBean(request, AddItemFormBean.class);
		System.out.println(formBean);

		// 校验用户注册填写的表单数据
		if (formBean.validate() == false) {// 如果校验失败
			// 将封装了表单数据的formBean对象发送回form表单中进行显示
			request.setAttribute("addItemFormBean", formBean);
			// 校验失败就说明是用户填写的表单数据有问题，那么就跳转刚才的页面
			request.setAttribute("addItemError", "未全部填写/填写内容不符合要求！！");
			request.getRequestDispatcher("/pages/admin/manage-items.jsp").forward(request, response);
			return;
		}
		
		// FormBean转PO,并进行添加工作
		Item item = new Item();
		try {
			// 注册字符串到日期的转换器
			ConvertUtils.register(new DateLocaleConverter(), Date.class);
			// 表单数据拷贝到domain中
			BeanUtils.copyProperties(item, formBean);
			System.out.println(item);

			itemService.add(item);
			//回显
			request.setAttribute("operateSuccess", "修改成功！！");
			request.getRequestDispatcher("/pages/admin/manage-items.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("operateError", "对不起，添加商品失败！！");
			request.getRequestDispatcher("/pages/admin/manage-items.jsp").forward(request, response);
			throw new RuntimeException(e);
		}
		System.out.println("------------AddItemServlet work finished-----------");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
