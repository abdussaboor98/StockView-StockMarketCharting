package com.cts.training.stockview.stockpriceservice.feignproxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("zuul-api-gateway-server")
public interface ZuulGatewayProxy {

	@GetMapping(value = "/stock-exchange-service/stockExchanges/getAllStockExchangesNames", produces = "application/json")
	public List<String> getAllStockExchangesNames();
	
	@GetMapping(value = "/company-service/companies/getCompanyByStockCodeAndExchangeName/{stockCode}/{stockExchangeName}", produces = "application/json")
	public boolean getCompanyByStockCodeAndExchangeName(@PathVariable("stockCode") String stockCode, @PathVariable("stockExchangeName") String stockExchangeName);
}
