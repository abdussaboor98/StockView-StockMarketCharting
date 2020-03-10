package com.cts.training.stockview.stockpriceservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockPricePerMonth {

	private String companyCode;
	private String stockExchange;
	private int month;
	private double avgPrice;
}
