package com.coffee.service;

import java.sql.SQLException;

import com.coffee.domain.Page;

public interface IBaseService<T> {

	/**
	 * 得到这个区间内的item
	 * 
	 * @param begin
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	Page<T> get(int begin, int pageSize) throws SQLException;
	

	/**
	 * 得到这个区间内的item
	 * 
	 * @param begin
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	Page<T> get(T t,int begin, int pageSize) throws SQLException;
}
