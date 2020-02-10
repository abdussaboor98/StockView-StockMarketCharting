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

import com.cts.training.stockview.dao.IPODAO;
import com.cts.training.stockview.daoimpl.IPODAOImpl;
import com.cts.training.stockview.model.IPOEntity;

public class IPOEntityTest {

	private static AnnotationConfigApplicationContext context;
	private static IPODAO ipoDAO;
	private static IPOEntity ipo;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.cts.training.stockview");
		context.refresh();
		ipo = (IPOEntity) context.getBean("ipoEntity");
		ipoDAO = (IPODAO) context.getBean("ipoDAO");
	}

	@Test
	public void testGetAllIPOs() {
		List<IPOEntity> ipos = ipoDAO.getAllIPOs();
		assertEquals(1, ipos.size());
	}

	@Test
	public void testGetIPOByIdSuccess() {
		IPOEntity ipo = new IPOEntity(1, "XYZ", "BSE", 7868L, 768687L, LocalDateTime.now(), "Very profitable");
		assertEquals(ipo.getStockExchange(), ipoDAO.getIPOById(1).getStockExchange());
	}

	@Test
	public void testGetIPOByIdFail() {
		assertEquals(null, ipoDAO.getIPOById(50));
	}

	@Test
	public void testUpdateIPOSuccess() {
		IPOEntity ipo = ipoDAO.getIPOById(1);
		ipo.setOpenDateTime(LocalDateTime.now());
		assertEquals(true, ipoDAO.updateIPO(ipo));
	}

	@Test(expected = NullPointerException.class)
	public void testUpdateIPOFail() {
		IPOEntity ipo = ipoDAO.getIPOById(90);
		ipo.setOpenDateTime(LocalDateTime.now());
		assertEquals(true, ipoDAO.updateIPO(ipo));
	}

	@Test
//	@Ignore
	public void testAddIPOSuccess() {
		IPOEntity ipo = new IPOEntity(1, "XYZ", "BSE", 7868L, 768687L, LocalDateTime.now(), "Very profitable");
		assertEquals(true, ipoDAO.addIPO(ipo));
	}

	@Test(expected = DataIntegrityViolationException.class)
	@Ignore
	public void testAddIPOFail() {
		IPOEntity ipo = new IPOEntity(1, "XYZ", "BSE", 7868L, 768687L, LocalDateTime.now(), "Very profitable");
		assertEquals(true, ipoDAO.addIPO(ipo));
	}

	@Test
	@Ignore
	public void testDeleteIPOSuccess() {
		IPOEntity ipo = ipoDAO.getIPOById(103);
		assertEquals(true, ipoDAO.deleteIPO(ipo));
	}

	@Test(expected = IllegalArgumentException.class)
	@Ignore
	public void testDeleteIPOFail() {
		IPOEntity ipo = ipoDAO.getIPOById(23);
		assertEquals(true, ipoDAO.deleteIPO(ipo));
	}
}
