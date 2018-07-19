package com.coffee.service;

import java.sql.SQLException;

import com.coffee.domain.Page;

/**
 * 提供获取Page的方法，如果Service提供分页查看的功能，就要继承它
 * 
 * @author K
 *
 * @param <T>
 */
public interface IPageService<T> {

	/**
	 * 得到begin到begin+pageSize之间的T对象，包装成Page返回
	 * 
	 * @param begin
	 * @param pageSize
	 * @return Page<T>
	 * @throws SQLException
	 */
	Page<T> get(int begin, int pageSize) throws SQLException;

	/**
	 * 根据T的特征进行搜索，得到begin到begin+pageSize之间的T对象，包装成Page返回
	 * 
	 * @param t
	 * @param begin
	 * @param pageSize
	 * @return Page<T>
	 * @throws SQLException
	 */
	Page<T> get(T t, int begin, int pageSize) throws SQLException;
}
