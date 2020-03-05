package com.cts.training.stockview.companyservice.feignproxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("zuul-api-gateway-server")
public interface IPOServiceProxy {

	@GetMapping(value = "/initial-public-offering-service/ipos", produces = "application/json")
	public ResponseEntity<?> getAllIPOs();
}
