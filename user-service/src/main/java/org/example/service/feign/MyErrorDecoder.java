package org.example.service.feign;

import feign.Response;
import feign.codec.ErrorDecoder;

public class MyErrorDecoder implements ErrorDecoder {
	@Override
	public Exception decode(String methodKey, Response response) {
		if (response.status() == 513) {
			return new RuntimeException("513");
		} else {
			return new RuntimeException("nnnnnnnnnnnnnn");
		}
	}
}
