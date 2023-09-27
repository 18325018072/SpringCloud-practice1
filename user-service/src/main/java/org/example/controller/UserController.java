package org.example.controller;

import org.example.service.feign.OrderFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
	OrderFeignClient orderFeignClient;

	@Autowired
	public UserController(OrderFeignClient orderFeignClient) {
		this.orderFeignClient = orderFeignClient;
	}

	@RequestMapping("ok")
	public ResponseEntity<Object> ok(){
		return orderFeignClient.ok();
	}

	@RequestMapping("error")
	public  ResponseEntity<Object> error(){
		return orderFeignClient.error();
	}

	@ExceptionHandler({RuntimeException.class})
	public ResponseEntity<String> handleException(Exception e) {
		return new ResponseEntity<>("成功出错",HttpStatus.OK);
	}
}
