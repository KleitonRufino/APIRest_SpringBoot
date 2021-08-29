package br.com.security;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;


public class JWTTokenFilter extends GenericFilterBean{

	
	@Autowired
	private JWTTokenProvider provider;
	

	public JWTTokenFilter(JWTTokenProvider provider) {
		this.provider = provider;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String token = provider.resolveToken((HttpServletRequest) request);
		if(token != null && provider.validateToken(token)) {
			Authentication authentication = provider.getAuthentication(token);
			if(authentication != null) {
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		chain.doFilter(request, response);
	}

}
