package com.coffee.util;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coffee.domain.Item;
import com.coffee.domain.Page;
import com.coffee.service.IBaseService;

/**
 * 包含请求Page（分页结果）的通用代码，负责提供一个相关Page给Servlet再提供给前端
 * 
 * @author K
 */
public class PageUtils {

	public static <T> Page<T> getPage(HttpServletRequest request, HttpServletResponse response, int pageSize,
			IBaseService<T> service) throws SQLException {
		int currentPage = getCurrentPage(request);
		int begin = (currentPage - 1) * pageSize; // 计算每页的开头

		Page<T> page = service.get(begin, pageSize);
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setUrl(getURL(request));

		return page;
	}

	public static <T> Page<T> getPage(HttpServletRequest request, HttpServletResponse response, int pageSize,
			IBaseService<T> service, T t) throws SQLException {
		int currentPage = getCurrentPage(request);
		int begin = (currentPage - 1) * pageSize; // 计算每页的开头

		Page<T> page = service.get(t, begin, pageSize);
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setUrl(getURL(request));

		return page;
	}

	/**
	 * 从request中提取要访问的页数
	 * 
	 * @param request
	 * @return
	 */
	private static int getCurrentPage(HttpServletRequest request) {
		String currentPage = request.getParameter("currentPage");
		if (currentPage == null || isNum(currentPage) == false) {
			return 1;
		} else {
			return Integer.parseInt(currentPage);
		}
	}

	/**
	 * 使用正则验证输入的是否为数字
	 * 
	 * @param string
	 * @return
	 */
	private static boolean isNum(String string) {
		Pattern pattern = Pattern.compile("[1-9]{1}\\d*");
		Matcher matcher = pattern.matcher(string);
		return matcher.matches();
	}

	/**
	 * 这是request请求的URI,即"被请求"的方的地址
	 * 利用这个URL，可以在JSP中直接使用page.url来完成超链接，与使用完整路径来访问GetUsersPageServlet等是相同的功能
	 */
	private static String getURL(HttpServletRequest request) {
		String contextPath = request.getContextPath(); // 项目名
		String servletPath = request.getServletPath(); // servlet路径，即/*Servlet
														// 建议使用request.getRequestURI()获得一个包含项目名+Servlet的路径
		String queryString = request.getQueryString(); // ?后面的参数
		if (queryString == null) {
			queryString = "";
		}
		queryString = queryString.replaceAll("&currentPage=\\d+", "");
		return contextPath + servletPath + "?" + queryString;
	}
}
