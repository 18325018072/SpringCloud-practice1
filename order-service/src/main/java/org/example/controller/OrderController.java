package org.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {
	@RequestMapping("ok")
	public ResponseEntity<Object> right() {
		System.out.println("order-ok");
		return ResponseEntity.ok().build();
	}

	@RequestMapping("error")
	public ResponseEntity<Object> error() {
		try {
			throw new RuntimeException("Order处理异常");
		} catch (Exception ign) {
			return ResponseEntity.status(513).body("Order处理异常");
		}
	}
}
