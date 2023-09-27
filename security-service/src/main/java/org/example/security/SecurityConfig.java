package org.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String[] URL_ACCESS_WHITELIST = {"/login"};

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf()
				.disable()
				.httpBasic()
				.and()
				.authorizeRequests()
				// 配置可以直接访问的资源路径
				.antMatchers(URL_ACCESS_WHITELIST)
				.permitAll()
				// 其余所有请求都要通过认证鉴权
				.anyRequest()
				.authenticated();
	}

	/**
	 * AuthenticationManager
	 */
//	@Bean
	public AuthenticationManager authenticationManager(DaoAuthenticationProvider daoAuthenticationProvider) {
		return new ProviderManager(Collections.singletonList(daoAuthenticationProvider));
	}
}
