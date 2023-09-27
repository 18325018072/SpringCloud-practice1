package org.example.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("order-service")
public interface OrderFeignClient {
	@GetMapping("/order/ok")
	ResponseEntity<Object> ok();

	@GetMapping("/order/error")
	ResponseEntity<Object> error();
}
