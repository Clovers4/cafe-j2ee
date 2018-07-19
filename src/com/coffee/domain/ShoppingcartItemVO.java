package com.coffee.domain;

/**
 * JavaBean:PO，字段对应shoppingcart_item表与item表，获取更详细的购物车餐点信息
 * 
 * @author K
 */
public class ShoppingcartItemVO {
	private int itemId;
	private String name;
	private String type;
	private int stock;
	private int number;
	private double price;
	private String imageUrl;

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getStock() {
		return stock;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	public int getNumber() {
		return number;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	@Override
	public String toString() {
		return "ShoppingcartItemDetailVO [itemId=" + itemId + ",name=" + name + ",type=" + type + ",stock=" + stock
				+ ",number=" + number + ",price=" + price + ",imageUrl=" + imageUrl + "]";
	}

}
