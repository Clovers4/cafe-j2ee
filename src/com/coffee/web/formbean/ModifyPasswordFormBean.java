package com.coffee.web.formbean;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装用户修改密码表单
 * 
 * @author K
 */
public class ModifyPasswordFormBean {
	private String password;
	private String passwordConfirm;

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
		return "ModifyInfoFormBean [password=" + password + ",passwordConfirm=" + passwordConfirm + "]";
	}

}
