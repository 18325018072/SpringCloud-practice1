package org.example.filter;

import org.example.util.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthenticationJwtFilter extends AbstractGatewayFilterFactory<Object> {

	@Override
	public GatewayFilter apply(Object config) {
		return (exchange, chain) -> {
			List<String> list = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
			if (list != null && JwtUtil.verifyJwt(list.get(0))) {
				return chain.filter(exchange);
			} else {
				exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
				return exchange.getResponse().setComplete();
			}
		};
	}
}
