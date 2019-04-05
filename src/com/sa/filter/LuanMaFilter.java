package com.sa.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LuanMaFilter implements Filter {
	private String charSet = "utf-8";
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(charSet);
		response.setCharacterEncoding(charSet);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		if (filterConfig.getInitParameter("charSet") != null) {
			this.charSet = filterConfig.getInitParameter("charSet");
		}
	}

}
