package com.cts.training.stockview.stockpriceservice;


import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

import com.cts.training.stockview.stockpriceservice.repo.StockPriceRepository;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class StockPriceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockPriceServiceApplication.class, args);
	}

}
