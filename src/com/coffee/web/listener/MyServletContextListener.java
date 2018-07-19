package com.coffee.web.listener;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

import com.coffee.util.JdbcUtils;

/**
 * @ClassName: MyServletContextListener
 * @Description: 监听web服务器的启动和关闭.主要负责在web服务器启动时，对连接池配置进行初始化
 * 
 * @author: K
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("ServletContext对象创建");
		Connection connection = null;
		try {
			connection = JdbcUtils.getConnection();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("ServletContext对象销毁");
	}
}