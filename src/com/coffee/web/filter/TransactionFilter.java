package com.coffee.web.filter;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.coffee.util.*;

/**
 * @ClassName: TransactionFilter
 * @Description:ThreadLocal + Filter 统一处理数据库事务。负责Connection的获取和释放，事务的开启，提交，回滚。
 *                          使得Service不必为Dao层管理连接，专注于业务。Dao层也可以直接获得连接.
 * @author: K
 */
/*
 * @WebFilter(filterName = "TransactionFilter", urlPatterns = "/servlet/*",
 * dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD })
 */
public class TransactionFilter implements Filter {

	/**
	 * 初始化连接池
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// System.out.println("TransactionFilter.doFilter()---started---;");
		Connection connection = null;
		try {
			// 1、获取数据库连接对象Connection
			connection = JdbcUtils.getConnection();

			// 2、开启事务
			connection.setAutoCommit(false);

			// 3、利用ThreadLocal把获取数据库连接对象Connection和当前线程绑定
			ConnectionContext.getInstance().bind(connection);

			// 4、把请求转发给目标Servlet
			chain.doFilter(request, response);

			// 5、提交事务
			// System.out.println("-----commit-----");
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("-----roolback-----");
			// 6、回滚事务
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			// 出现异常之后跳转到错误页面
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			req.setAttribute("message", e.getMessage());
			req.getRequestDispatcher("/message.jsp").forward(req, res);
			// res.sendRedirect(req.getContextPath() + "/message.jsp");

		} finally {
			// System.out.println("TransactionFilter.doFilter()---finished---;");
			// 7、解除绑定
			ConnectionContext.getInstance().remove();
			// 8、关闭数据库连接
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void destroy() {

	}
}