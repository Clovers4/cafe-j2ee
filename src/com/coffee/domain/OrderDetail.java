package com.coffee.domain;

/**
 * JavaBean:PO，字段对应order_detail表
 * 
 * @author K
 */
public class OrderDetail {
	private int orderId;
	private int itemId;
	private int number;

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ",itemId=" + itemId + ",number=" + number + "]";
	}

}
