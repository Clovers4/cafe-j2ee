package com.coffee.web.filter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

/**
 * @ClassName: CharacterEncodingFilter
 * @Description: 此过滤器用来解决中文乱码问题,需要放在第一个
 * @author: K
 */
/*
 * @WebFilter(filterName = "CharacterEncodingFilter", urlPatterns = "/*",
 * 
 * initParams = { @WebInitParam(name = "charset", value = "UTF-8") },
 * 
 * dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD })
 */
public class CharacterEncodingFilter implements Filter {
	private FilterConfig filterConfig = null;
	private String defaultCharset = "UTF-8";// 设置默认的字符编码

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// System.out.println("CharacterEncodingFilter.doFilter()---started---;");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		// 读取参数
		String charset = filterConfig.getInitParameter("charset");
		if (charset == null) {
			charset = defaultCharset;
		}

		// 设置编码
		request.setCharacterEncoding(charset);
		response.setCharacterEncoding(charset);
		response.setContentType("text/html;charset=" + charset);

		// 包装request，解决get方式获得的参数中文乱码问题
		MyCharacterEncodingRequest requestWrapper = new MyCharacterEncodingRequest(request);

		chain.doFilter(requestWrapper, response);
		// System.out.println("CharacterEncodingFilter.doFilter()---finished---;");
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// 得到过滤器的初始化配置信息
		this.filterConfig = filterConfig;
	}

	public void destroy() {

	}
}

/*
 * @Description:解决get方式获得的参数中文乱码问题
 */
class MyCharacterEncodingRequest extends HttpServletRequestWrapper {

	private HttpServletRequest request;

	public MyCharacterEncodingRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	@Override
	public String getParameter(String name) {
		try {
			// 获取参数的值
			String value = this.request.getParameter(name);
			if (value == null) {
				return null;
			}
			// 如果不是以get方式提交数据的，就直接返回获取到的值
			if (!this.request.getMethod().equalsIgnoreCase("get")) {
				return value;
			} else {
				// 如果是以get方式提交数据的，就对获取到的值进行转码处理
				// 有时候使用这个方式转码反而出现"？？"乱码？
				// value = new String(value.getBytes("ISO8859-1"),
				// this.request.getCharacterEncoding());
				System.out.println("name=" + name + ",value=" + value);
				return value;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}