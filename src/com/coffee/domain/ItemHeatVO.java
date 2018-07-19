package com.coffee.domain;

/**
 * JavaBean:VO，字段对应item表与order_detail表，利用order_detail表来获得某餐点的点餐次数百分比
 * 
 * @author K
 */
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
