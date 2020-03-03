package com.cts.training.stockview.stockexchangeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StockExchangeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockExchangeServiceApplication.class, args);
	}

}
