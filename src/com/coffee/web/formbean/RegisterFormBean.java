package com.coffee.web.formbean;

import java.util.*;

/**
 * 封装用户注册表单
 * 
 * @author K
 */
public class RegisterFormBean {
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
	 * validate方法负责校验表单输入项 表单输入项校验规则： private String username; 用户名不能为空， private
	 * String password; 密码不能为空， private String passwordConfirm; 两次密码要一致
	 * 
	 * 已在前端进行验证，这里暂不写验证
	 */
	/**
	 * 注册验证是否符合要求
	 */
	public boolean validate() {
		boolean isOk = true;

		if (this.account == null||this.account.equals("")) {
			isOk = false;
		}
		if (this.password == null||this.password.equals("")) {
			isOk = false;
		}

		// 两次密码要一致，且确认密码不能为空
		if (this.passwordConfirm == null||this.passwordConfirm.equals("") || !this.passwordConfirm.equals(this.password)) {
			isOk = false;
			errors.put("passwordConfirm", "两次密码不一致！！");
		}

		if (this.tel == null||this.tel.equals("")) {
			isOk = false;
		}
		
		if (this.email == null||this.email.equals("")) {
			isOk = false;
		}
		return isOk;
	}

	@Override
	public String toString() {
		return "RegisterFormBean [account=" + account + ",password=" + password + ",tel=" + tel + ",email=" + email
				+ "]";
	}

}
