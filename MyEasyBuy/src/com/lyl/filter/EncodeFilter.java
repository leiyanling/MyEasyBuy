package com.lyl.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Servlet Filter implementation class EncodeFilter
 */
@WebFilter(urlPatterns = { "/*" }, initParams = { @WebInitParam(name = "encode", value = "utf-8") })
public class EncodeFilter implements Filter {
	String encode = null;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		if (request.getCharacterEncoding() == null)
			request.setCharacterEncoding(encode);// 统一解决post 提交的编码方式问题

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		encode = fConfig.getInitParameter("encode");

	}

}
