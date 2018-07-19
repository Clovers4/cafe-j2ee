package com.coffee.util;

import java.security.MessageDigest;
import java.util.*;
import javax.servlet.http.*;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 与Web有关的工具类。功能有：
 * 把request对象中的表单封装到bean中
 * 生成UUID
 * MD5加密
 * 获取上个页面的url（切分成可重定向的字符串）
 * 
 * @author K
 */

public class WebUtils {
	/**
	 * 将request对象转换成T对象
	 * 用于包装表单数据，封装成FormBean
	 * 
	 * @param HttpServletRequest
	 * @param Class<T>
	 * @return Class<T>
	 */
	public static <T> T requestToBean(HttpServletRequest request, Class<T> clazz) {
		try {
			T bean = clazz.newInstance();
			Enumeration<String> e = request.getParameterNames();
			while (e.hasMoreElements()) {
				String name = (String) e.nextElement();
				String value = request.getParameter(name);
				BeanUtils.setProperty(bean, name, value);
			}
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 生成UUID
	 * 
	 * @return String
	 */
	public static String makeId() {
		return UUID.randomUUID().toString();
	}
	
	/**
	 * 把字节数组转成16进位制数
	 * 
	 * @param bytes
	 * @return
	 */
	public static String bytesToHex(byte[] bytes) {
		StringBuffer md5str = new StringBuffer();
		// 把数组每一字节换成16进制连成md5字符串
		int digital;
		for (int i = 0; i < bytes.length; i++) {
			digital = bytes[i];
			if (digital < 0) {
				digital += 256;
			}
			if (digital < 16) {
				md5str.append("0");
			}
			md5str.append(Integer.toHexString(digital));
		}
		return md5str.toString().toUpperCase();
	}

	/**
	 * 把字节数组转换成md5
	 * 
	 * @param input
	 * @return
	 */
	public static String bytesToMD5(byte[] input) {
		String md5str = null;
		try {
			// 创建一个提供信息摘要算法的对象，初始化为md5算法对象
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 计算后获得字节数组
			byte[] buff = md.digest(input);
			// 把数组每一字节换成16进制连成md5字符串
			md5str = bytesToHex(buff);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5str;
	}

	/**
	 * 把字符串转换成md5
	 * 
	 * @param str
	 * @return
	 */
	public static String strToMD5(String str) {
		byte[] input = str.getBytes();
		return bytesToMD5(input);
	}
	
	/**
	 * 用于从request中提取原来的地址(即请求方的地址)，以重定向回去.
	 * 获得项目名后面的相对路径，如http://localhost:8080/coffee/index.jsp，则获得index.jsp
	 * 
	 * @param request
	 * @return
	 */
	public static String getOrgServletPath(HttpServletRequest request) {
		String url = request.getHeader("Referer");
		int beginIndex = url.lastIndexOf(request.getContextPath())+request.getContextPath().length();
		return url.substring(beginIndex);
	}

}
