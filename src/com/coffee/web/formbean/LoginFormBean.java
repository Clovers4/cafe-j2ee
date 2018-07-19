package com.coffee.web.formbean;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装用户登录表单bean
 * 
 * @author K
 *
 */
public class LoginFormBean {
	private String account;
	private String password;
	private String status;
	private int logintime;

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

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setLogintime(int logintime) {
		this.logintime = logintime;
	}

	public int getLogintime() {
		return logintime;
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

	@Override
	public String toString() {
		return "LoginFormBean [account=" + account + ",password=" + password + ",status=" + status + ",logintime="
				+ logintime + "]";
	}

}
