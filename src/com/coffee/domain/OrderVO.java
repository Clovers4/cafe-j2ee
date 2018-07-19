package com.coffee.domain;

/**
 * JavaBean:VO，字段对应order表与user表，通过user_id获取/显示user的account
 * 
 * @author K
 */
import java.util.Date;

public class OrderVO {
	private int orderId;
	private String account;//username
	private double orderTotal;
	private Date createdTime;
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getOrderId() {
		return orderId;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAccount() {
		return account;
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
		return "OrderVO [orderId=" + orderId + ",account=" + account + ",orderTotal=" + orderTotal + ",createdTime="
				+ createdTime + "]";
	}
}
