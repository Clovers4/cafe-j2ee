package com.coffee.service;

import java.sql.SQLException;
import java.util.List;

import com.coffee.domain.Order;
import com.coffee.domain.OrderDetailVO;
import com.coffee.domain.OrderVO;
import com.coffee.domain.Page;

/**
 * @InterfaceName: IOrderVOService
 * @Description:Service层接口，提供获取OrderVO的分页对象，以在前端显示
 * 
 * @author: K
 */
public interface IOrderVOService extends IPageService<OrderVO> {

	@Override
	Page<OrderVO> get(int begin, int pageSize) throws SQLException;

	@Override
	Page<OrderVO> get(OrderVO orderFeature, int begin, int pageSize) throws SQLException;

}
