package org.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private JwtAccessTokenConverter jwtAccessTokenConverter;

	/**
	 * 配置令牌端点上的安全约束。
	 *
	 * @param security a fluent configurer for security features
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) {
		security.tokenKeyAccess("permitAll()")
				.checkTokenAccess("permitAll()")
				.allowFormAuthenticationForClients();
	}

	/**
	 * 配置客户端的详细信息服务。
	 *
	 * @param clients the client details configurer
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
				.withClient("client")
				.secret(passwordEncoder.encode("123456"))
				.authorizedGrantTypes("refresh_token", "client_credentials")
				.scopes("all")
				.accessTokenValiditySeconds(60 * 600)
				.refreshTokenValiditySeconds(60 * 600 * 24);
	}

	/**
	 * 配置授权和令牌端点以及令牌服务。
	 *
	 * @param endpoints the endpoints configurer
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints.tokenStore(tokenStore)
				.accessTokenConverter(jwtAccessTokenConverter)
				.exceptionTranslator(e -> {
					e.printStackTrace();
					Map<Object, Object> res = new HashMap<>();
					res.put("result", e.getMessage());
					return new ResponseEntity(res, HttpStatus.OK);
				});
	}
}
