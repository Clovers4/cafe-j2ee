package com.coffee.web.formbean;

import java.util.HashMap;
import java.util.Map;

/**
 *封装管理员直接修改用户个人信息&密码表单
 * 
 * @author K
 */
public class ModifyUserFormBean {
	private String account;
	private String password;
	private String passwordConfirm;
	private String tel;
	private String email;

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccount() {
		return account;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getTel() {
		return tel;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
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
	 * 已在前端进行验证，这里暂不写验证,仅写确认密码验证
	 */
	/**
	 * 验证是否符合要求
	 */
	public boolean validate() {
		if (password.equals(passwordConfirm) && password != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "ModifyUserFormBean [account=" + account + ",password=" + password + ",tel=" + tel + ",email=" + email
				+ "]";
	}

}
