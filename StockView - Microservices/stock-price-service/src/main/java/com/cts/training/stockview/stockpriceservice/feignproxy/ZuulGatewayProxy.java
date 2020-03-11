package com.cts.training.stockview.stockpriceservice.feignproxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import feign.RequestInterceptor;

@FeignClient(name = "zuul-api-gateway-server",configuration = FeignClientConfiguration.class)
public interface ZuulGatewayProxy {

	@GetMapping(value = "/stock-exchange-service/stockExchanges/getAllStockExchangesNames", produces = "application/json")
	public List<String> getAllStockExchangesNames();

	@GetMapping(value = "/company-service/companies/getCompanyByStockCodeAndExchangeName/{stockCode}/{stockExchangeName}", produces = "application/json")
	public boolean getCompanyByStockCodeAndExchangeName(@PathVariable("stockCode") String stockCode,
			@PathVariable("stockExchangeName") String stockExchangeName);
}
