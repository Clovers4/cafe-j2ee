package com.coffee.util;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.sql.*;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

/**
 * @ClassName: JdbcUtils
 * @Description:数据库连接工具，用于从数据库连接池中取Connection。主要是保存在ConnectionContext。
 * @author: K
 */
public class JdbcUtils {
	private static DataSource ds = null;
	// 在静态代码块中创建数据库连接池
	static {
		try {
			// 加载dbcpconfig.properties配置文件
			InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("dbcp.properties");
			Properties prop = new Properties();
			prop.load(in);
			// 创建数据源
			ds = BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	/**
	 * @Method: getConnection
	 * @Description: 从数据源中取一个Connection传出。
	 * 
	 * @return Connection
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

}