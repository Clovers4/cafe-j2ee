package com.coffee.domain;

public class ItemHeatVO {
	private String name;
	private String type;
	private double percent;
	
	
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
	
	public void setPercent(double percent) {
		this.percent = percent;
	}
	public double getPercent() {
		return percent;
	}
	
	@Override
	public String toString() {
		return "ItemHeatVO [name=" + name + ",type=" + type + ",percent=" + percent + "]";
}
}
