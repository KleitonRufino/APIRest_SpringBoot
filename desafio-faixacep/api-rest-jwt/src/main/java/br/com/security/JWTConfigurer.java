package br.com.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	@Autowired
	private JWTTokenProvider provider;

	public JWTConfigurer(JWTTokenProvider provider) {
		this.provider = provider;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		JWTTokenFilter filter = new JWTTokenFilter(provider);
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}
}
