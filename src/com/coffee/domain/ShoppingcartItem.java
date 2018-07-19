package com.coffee.domain;

/**
 * JavaBean:PO，字段对应shoppingcart_item表
 * 
 * @author K
 */
public class ShoppingcartItem {
	private int userId;
	private int itemId;
	private int number;

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
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
		return "ShoppingcartItem [userId=" + userId + ",itemId=" + itemId + ",number=" + number + "]";
	}
}
