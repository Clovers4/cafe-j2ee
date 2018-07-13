package com.coffee.web.formbean;

import java.util.HashMap;
import java.util.Map;

/**
 * 接收item.jsp中的购物信息表单
 * 
 * @author K
 */
public class ShoppingcartItemFormbean {
	private String itemId;
	private int number;

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
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
	 * 已在前端进行验证，这里暂不写验证
	 */
	/**
	 * 验证是否符合要求
	 */
	public boolean validate() {
		if (number <= 0) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "ShoppingcartItemFormbean [itemId=" + itemId + ",number=" + number + "]";
	}

}
