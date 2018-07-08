package com.coffee.web.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: NoCacheFilter
 * @Description: 禁止浏览器缓存所有动态页面
 * @author: K
 */
/*
 * @WebFilter(filterName = "NoCacheFilter", urlPatterns = "*.jsp",
 * 
 * dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD })
 */
public class NoCacheFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		//System.out.println("NoCacheFilter.doFilter()---started---;");
		// 把ServletRequest/Response强转成HttpServletRequest/Response
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		// 禁止浏览器缓存所有动态页面
		response.setDateHeader("Expires", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		chain.doFilter(request, response);
		//System.out.println("NoCacheFilter.doFilter()---finished---;");
	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void destroy() {

	}
}