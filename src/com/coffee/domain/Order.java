package com.coffee.domain;

import java.util.Date;

/**
 * JavaBean:PO，字段对应order表
 * 
 * @author K
 */
public class Order {
	private int orderId;
	private int userId;
	private double orderTotal;
	private Date createdTime;

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public double getOrderTotal() {
		return orderTotal;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ",userId=" + userId + ",orderTotal=" + orderTotal + ",createdTime="
				+ createdTime + "]";
	}

}
