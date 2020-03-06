//package com.cts.training.stockview.stockpriceservice.feignproxy;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//@FeignClient("zuul-api-gateway-server")
//public interface CompanyServiceProxy {
//
//	@GetMapping(value = "/company-service/companies/getCompanyByStockCodeAndExchangeName/{stockCode}/{stockExchangeName}", produces = "application/json")
//	public boolean getCompanyByStockCodeAndExchangeName(@PathVariable("stockCode") String stockCode, @PathVariable("stockExchangeName") String stockExchangeName);
//}
