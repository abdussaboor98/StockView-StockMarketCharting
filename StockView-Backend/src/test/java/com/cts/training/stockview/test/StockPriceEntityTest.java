package com.cts.training.stockview.test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;

import com.cts.training.stockview.dao.StockPriceDAO;
import com.cts.training.stockview.daoimpl.StockPriceDAOImpl;
import com.cts.training.stockview.model.StockPriceEntity;

public class StockPriceEntityTest {

	private static AnnotationConfigApplicationContext context;
	private static StockPriceDAO stockPriceDAO;
	private static StockPriceEntity stockPrice;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.cts.training.stockview");
		context.refresh();
		stockPrice = (StockPriceEntity) context.getBean("stockPriceEntity");
		stockPriceDAO = (StockPriceDAO) context.getBean("stockPriceDAO");
	}

	@Test
	public void testGetAllStockPrices() {
		List<StockPriceEntity> stockPrices = stockPriceDAO.getAllStockPrices();
		assertEquals(1, stockPrices.size());
	}

	@Test
	public void testGetStockPriceByIdSuccess() {
		StockPriceEntity stockPrice = new StockPriceEntity("88766BS", "BSE", 8989L, LocalDate.now(), LocalTime.now());
		assertEquals(stockPrice.getStockExchange(), stockPriceDAO.getStockPriceById(1).getStockExchange());
	}

	@Test
	public void testGetStockPriceByIdFail() {
		assertEquals(null, stockPriceDAO.getStockPriceById(50));
	}

	@Test
	public void testUpdateStockPriceSuccess() {
		StockPriceEntity stockPrice = stockPriceDAO.getStockPriceById(1);
		stockPrice.setTime(LocalTime.now());
		assertEquals(true, stockPriceDAO.updateStockPrice(stockPrice));
	}

	@Test(expected = NullPointerException.class)
	public void testUpdateStockPriceFail() {
		StockPriceEntity stockPrice = stockPriceDAO.getStockPriceById(90);
		stockPrice.setTime(LocalTime.now());
		assertEquals(true, stockPriceDAO.updateStockPrice(stockPrice));
	}

	@Test
//	@Ignore
	public void testAddStockPriceSuccess() {
		StockPriceEntity stockPrice = new StockPriceEntity("88766BS", "BSE", 8989L, LocalDate.now(), LocalTime.now());
		assertEquals(true, stockPriceDAO.addStockPrice(stockPrice));
	}

	@Test(expected = DataIntegrityViolationException.class)
	@Ignore
	public void testAddStockPriceFail() {
		StockPriceEntity stockPrice = new StockPriceEntity("88766BS", "BSE", 8989L, LocalDate.now(), LocalTime.now());
		assertEquals(true, stockPriceDAO.addStockPrice(stockPrice));
	}

	@Test
	@Ignore
	public void testDeleteStockPriceSuccess() {
		StockPriceEntity stockPrice = stockPriceDAO.getStockPriceById(103);
		assertEquals(true, stockPriceDAO.deleteStockPrice(stockPrice));
	}

	@Test(expected = IllegalArgumentException.class)
	@Ignore
	public void testDeleteStockPriceFail() {
		StockPriceEntity stockPrice = stockPriceDAO.getStockPriceById(23);
		assertEquals(true, stockPriceDAO.deleteStockPrice(stockPrice));
	}
}
