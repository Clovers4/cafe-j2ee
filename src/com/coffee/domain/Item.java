package com.coffee.domain;

/**
 * JavaBean:PO，字段对应item表
 * 
 * @author K
 */
public class Item {
	private int itemId;
	private String name;
	private String type;
	private int stock;
	private double price;
	private String description;
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

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ",name=" + name + ",type=" + type + ",stock=" + stock + ",price=" + price
				+ ",description=" + description + ",imageUrl=" + imageUrl + "]";
	}

}
