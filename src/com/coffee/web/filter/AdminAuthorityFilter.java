package com.coffee.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 管理员未登录（未携带session）的情况，拒绝进入某些页面(需要登录才能操作的页面)
 * 
 * @author K
 */
public class AdminAuthorityFilter implements Filter {
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		// 管理员已经登录，放行
		if (request.getSession().getAttribute("admin") != null) {
			chain.doFilter(request, response);
			return;
		}

		response.sendRedirect(request.getContextPath() + "/index.jsp");

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}
}
