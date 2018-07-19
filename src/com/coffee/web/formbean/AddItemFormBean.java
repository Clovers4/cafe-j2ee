package com.coffee.web.formbean;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装商品注册表单bean。
 * 
 * @author K
 */
public class AddItemFormBean {
	private String name;
	private String type;
	private int stock;
	private double price;
	private String description;

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

	/**
	 * 存储校验不通过时给用户的错误提示信息
	 */
	private Map<String, String> errors = new HashMap<String, String>();

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	/*
	 * validate方法负责校验表单输入项 表单输入项校验规则
	 * 
	 * 已在前端进行验证，这里暂不写验证
	 */
	/**
	 * 验证是否符合要求
	 */
	public boolean validate() {
		boolean isOk = true;

		if (stock < 0) {
			isOk = false;
		}

		if (price <= 0) {
			isOk = false;
		}

		return isOk;
	}

	@Override
	public String toString() {
		return "AddItemFormBean [name=" + name + ",type=" + type + ",stock=" + stock + ",price=" + price
				+ ",description=" + description + "]";
	}

}
