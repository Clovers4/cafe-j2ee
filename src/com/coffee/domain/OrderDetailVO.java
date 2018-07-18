package com.coffee.domain;

public class OrderDetailVO {
	private String name;
	private String type;
	private int number;
	private double price;
	private String imageUrl;

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
		return "OrderDetailVO [name=" + name + ",type=" + type + ",number=" + number + ",price=" + price + ",imageUrl="
				+ imageUrl + "]";
	}
}
