package com.coffee.util;

import java.sql.Connection;

/**
 * @ClassName: ConnectionContext
 * @Description:数据库连接工具,用于将一个Connecion绑定到ThreadLocal上，使DAO层可直接通过该类获取正在进行中的事务的Connection。
 * @author: K
 */
public class ConnectionContext {
	private volatile static ConnectionContext uniqueInstance;// 单例对象实例
	private ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<Connection>();// 存储数据库连接对象

	// 构造方法私有化，将ConnectionContext设计成单例
	private ConnectionContext() {
	}

	/**
	 * @Method: getInstance
	 * @Description:获取ConnectionContext实例对象
	 * 
	 * @return ConnectionContext
	 */
	public static ConnectionContext getInstance() {
		if (uniqueInstance == null) {
			synchronized (ConnectionContext.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new ConnectionContext();
				}
			}
		}
		return uniqueInstance;
	}

	/**
	 * @Method: bind
	 * @Description:利用ThreadLocal把获取数据库连接对象Connection和当前线程绑定
	 * 
	 * @param connection
	 */
	public void bind(Connection connection) {
		connectionThreadLocal.set(connection);
	}

	/**
	 * @Method: getConnection
	 * @Description:从当前线程中取出Connection对象
	 * 
	 * @return Connection
	 */
	public Connection getConnection() {
		return connectionThreadLocal.get();
	}

	/**
	 * @Method: remove
	 * @Description: 解除当前线程上绑定Connection
	 */
	public void remove() {
		connectionThreadLocal.remove();
	}
}