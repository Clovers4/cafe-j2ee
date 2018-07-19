package com.coffee.web.controller;

import java.io.IOException;
import java.sql.SQLException;
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
import com.coffee.web.formbean.ModifyItemFormBean;

/**
 * 提供修改商品信息（除了图片）的功能
 * 
 * @author K
 */
@WebServlet(name = "ModifyItemServlet", urlPatterns = "/servlet/modifyItemServlet")
public class ModifyItemServlet extends HttpServlet {
	private IItemService itemService=new ItemServiceImpl();
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------------ModifyItemServlet work start-----------");
		//读取表单数据
		ModifyItemFormBean formBean = WebUtils.requestToBean(request, ModifyItemFormBean.class);
		System.out.println(formBean);
		
		//检验表单数据
		if (formBean.validate() == false) {
			throw new RuntimeException("商品信息修改表单有误.");
		}
		
		//修改商品信息
		try {
			modify(request, response, formBean);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		//回显
		request.setAttribute("operateSuccess", "修改成功！！");
		request.getRequestDispatcher("/servlet/getItemsPageServlet").forward(request, response);
	}

	private void modify(HttpServletRequest request, HttpServletResponse response, ModifyItemFormBean formBean) throws ServletException, IOException, SQLException {
		//通过itemId读取item的原始数据
		Item item = itemService.get(formBean.getItemId());
		try {
			//拷贝信息，覆盖原始数据(null字段不会覆盖掉原始数据)
			BeanUtils.copyProperties(item, formBean);
			System.out.println(item);
			
			//更新
			itemService.update(item);
		} catch (Exception e) {
			request.setAttribute("operateError", "操作失败！！");
			request.getRequestDispatcher("/servlet/getItemsPageServlet").forward(request, response);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
