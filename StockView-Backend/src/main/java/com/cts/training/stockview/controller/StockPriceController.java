package com.cts.training.stockview.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.cts.training.stockview.dao.StockPriceDAO;
import com.cts.training.stockview.daoimpl.StockPriceDAOImpl;
import com.cts.training.stockview.model.StockPrice;

public class StockPriceController {

	public static void main(String[] args) {
		LocalDate date;
		LocalTime time;
		String currentLine;
		StockPrice stockPrice = new StockPrice();
		StockPriceDAO spDAO = new StockPriceDAOImpl();
		
		try {
			File file = new File("C:/Users/LENOVO/Desktop/sample_stock_data.csv");
			FileReader fin = new FileReader(file);
			BufferedReader bin = new BufferedReader(fin);
			currentLine = bin.readLine();
			while((currentLine = bin.readLine()) != null) {
				System.out.println(currentLine);
				String row[] = currentLine.split(",");
				for(int i=0;i<row.length;i++) {
					row[i] = row[i].trim();
				}
				stockPrice.setCompanyCode(row[0]); 
				stockPrice.setStockExchange(row[1]);
				stockPrice.setCurrentPrice(Double.parseDouble(row[2]));
				date = LocalDate.parse(row[3],DateTimeFormatter.ofPattern("dd-MM-yyyy"));
				time = LocalTime.parse(row[4]);
				stockPrice.setDate(date);
				stockPrice.setTime(time);
				
				spDAO.addStockPrice(stockPrice);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
