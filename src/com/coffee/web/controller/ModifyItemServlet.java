package com.coffee.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coffee.util.WebUtils;
import com.coffee.web.formbean.ModifyItemFormBean;

/**
 * 提供修改商品信息（除了图片）的功能
 * 
 * @author K
 */
@WebServlet(name = "ModifyItemServlet", urlPatterns = "/servlet/modifyItemServlet")
public class ModifyItemServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModifyItemFormBean formBean = WebUtils.requestToBean(request, ModifyItemFormBean.class);
		System.out.println("------------ModifyItemServlet work start-----------");
		System.out.println(formBean);
		if (formBean.validate() == false) {
			throw new RuntimeException("商品信息修改表单有误.");
		}

	}

	private void modify(HttpServletRequest request, HttpServletResponse response,ModifyItemFormBean formBean) {
	
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
