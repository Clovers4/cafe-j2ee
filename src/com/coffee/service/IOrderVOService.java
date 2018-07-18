package com.coffee.service;

import java.sql.SQLException;
import java.util.List;

import com.coffee.domain.Order;
import com.coffee.domain.OrderDetailVO;
import com.coffee.domain.OrderVO;
import com.coffee.domain.Page;

/**
 * @InterfaceName: IOrderVOService
 * @Description:Service层接口，提供等服务。
 * 
 * @author: K
 */
public interface IOrderVOService extends IBaseService<OrderVO> {

	Page<OrderVO> get(int begin, int pageSize) throws SQLException;

	Page<OrderVO> get(OrderVO orderFeature, int begin, int pageSize) throws SQLException;

}
