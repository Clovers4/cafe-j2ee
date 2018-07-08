package com.coffee.exception;

/**
 * @ClassName: UserExistException
 * @Description:用于抛出"该用户名已存在"异常
 * 
 * @author: K
 */
public class UserExistException extends RuntimeException {
	public UserExistException() {
		super("该用户名已存在");
	}

	public UserExistException(String message) {
		super(message);
	}

}
