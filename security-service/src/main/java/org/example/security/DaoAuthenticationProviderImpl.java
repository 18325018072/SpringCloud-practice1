package org.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//@Component
public class DaoAuthenticationProviderImpl extends DaoAuthenticationProvider {

	@Autowired
	public DaoAuthenticationProviderImpl(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
		super();
		setPasswordEncoder(passwordEncoder);
		setUserDetailsService(userDetailsService);
	}

}
