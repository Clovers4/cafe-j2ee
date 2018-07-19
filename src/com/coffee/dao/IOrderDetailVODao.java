package com.coffee.dao;

import java.sql.SQLException;
import java.util.List;
import com.coffee.domain.OrderDetailVO;

/**
 * @InterfaceName: IOrderDetailVODao
 * @Description:DAO层接口，提供了item表与order_detail表的多表连接查询功能，提供更详细的订单信息；插入/更新/删除原则上不应该使用
 * 
 * @author: K
 */
public interface IOrderDetailVODao {
	/**
	 * 通过orderId查找，返回某个订单所包含的所有小件的各详细信息的List
	 * 
	 * @param orderId
	 * @return List<OrderDetailVO>
	 * @throws SQLException
	 */
	List<OrderDetailVO> findByOrderId(int orderId) throws SQLException;
}
