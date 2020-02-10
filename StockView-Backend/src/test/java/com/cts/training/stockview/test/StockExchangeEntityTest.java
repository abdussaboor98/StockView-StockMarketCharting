package com.cts.training.stockview.test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;

import com.cts.training.stockview.dao.StockExchangeDAO;
import com.cts.training.stockview.daoimpl.StockExchangeDAOImpl;
import com.cts.training.stockview.model.StockExchangeEntity;

public class StockExchangeEntityTest {

	private static AnnotationConfigApplicationContext context;
	private static StockExchangeDAO stockExchangeDAO;
	private static StockExchangeEntity stockExchange;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.cts.training.stockview");
		context.refresh();
		stockExchange = (StockExchangeEntity) context.getBean("stockExchangeEntity");
		stockExchangeDAO = (StockExchangeDAO) context.getBean("stockExchangeDAO");
	}

	@Test
	public void testGetAllStockExchanges() {
		List<StockExchangeEntity> stockExchanges = stockExchangeDAO.getAllStockExchanges();
		assertEquals(1, stockExchanges.size());
	}

	@Test
	public void testGetStockExchangeByIdSuccess() {
		StockExchangeEntity stockExchange = new StockExchangeEntity(1, "BSE", "Mumbai", "akaak", "good");
		assertEquals(stockExchange.getContractAddress(), stockExchangeDAO.getStockExchangeById(1).getContractAddress());
	}

	@Test
	public void testGetStockExchangeByIdFail() {
		assertEquals(null, stockExchangeDAO.getStockExchangeById(50));
	}

	@Test
	public void testUpdateStockExchangeSuccess() {
		StockExchangeEntity stockExchange = stockExchangeDAO.getStockExchangeById(1);
		stockExchange.setBrief("Located in mumbai india");
		assertEquals(true, stockExchangeDAO.updateStockExchange(stockExchange));
	}

	@Test(expected = NullPointerException.class)
	public void testUpdateStockExchangeFail() {
		StockExchangeEntity stockExchange = stockExchangeDAO.getStockExchangeById(90);
		stockExchange.setBrief("Located in mumbai india");
		assertEquals(true, stockExchangeDAO.updateStockExchange(stockExchange));
	}

	@Test
//	@Ignore
	public void testAddStockExchangeSuccess() {
		StockExchangeEntity stockExchange = new StockExchangeEntity(1, "BSE", "Mumbai", "akaak", "good");
		assertEquals(true, stockExchangeDAO.addStockExchange(stockExchange));
	}

	@Test(expected = DataIntegrityViolationException.class)
	@Ignore
	public void testAddStockExchangeFail() {
		StockExchangeEntity stockExchange = new StockExchangeEntity(1, "BSE", "Mumbai", "akaak", "good");
		assertEquals(true, stockExchangeDAO.addStockExchange(stockExchange));
	}

	@Test
	@Ignore
	public void testDeleteStockExchangeSuccess() {
		StockExchangeEntity stockExchange = stockExchangeDAO.getStockExchangeById(103);
		assertEquals(true, stockExchangeDAO.deleteStockExchange(stockExchange));
	}

	@Test(expected = IllegalArgumentException.class)
	@Ignore
	public void testDeleteStockExchangeFail() {
		StockExchangeEntity stockExchange = stockExchangeDAO.getStockExchangeById(23);
		assertEquals(true, stockExchangeDAO.deleteStockExchange(stockExchange));
	}
}
