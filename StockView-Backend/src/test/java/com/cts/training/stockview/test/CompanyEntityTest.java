package com.cts.training.stockview.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;

import com.cts.training.stockview.dao.CompanyDAO;
import com.cts.training.stockview.daoimpl.CompanyDAOImpl;
import com.cts.training.stockview.model.CompanyEntity;

public class CompanyEntityTest {

	private static AnnotationConfigApplicationContext context;
	private static CompanyDAO companyDAO;
	private static CompanyEntity company;
	private static List<String> directors;
	private static List<String> stockExchanges;
	private static List<String> stockCodes;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.cts.training.stockview");
		context.refresh();
		company = (CompanyEntity) context.getBean("companyEntity");
		companyDAO = (CompanyDAO) context.getBean("companyDAO");
		directors = new ArrayList<String>();
		directors.add("Director 1");
		directors.add("Director 2");
		directors.add("Director 3");
		stockExchanges = new ArrayList<String>();
		stockExchanges.add("BSE");
		stockExchanges.add("NASDAQ");
		stockExchanges.add("NSE");
		stockCodes = new ArrayList<String>();
		stockCodes.add("78787G");
		stockCodes.add("KJH8");
		stockCodes.add("883637");
	}

	@Test
	public void testGetAllCompanys() {
		List<CompanyEntity> companys = companyDAO.getAllCompanys();
		assertEquals(1, companys.size());
	}

	@Test
	public void testGetCompanyByIdSuccess() {
		CompanyEntity c = new CompanyEntity(1, "XYZ", "The CEO", directors, stockExchanges, "Technology",
				"Some Company", stockCodes, 33123123L);
		assertEquals(c.getSector(), companyDAO.getCompanyById(1).getSector());
	}

	@Test
	public void testGetCompanyByIdFail() {
		assertEquals(null, companyDAO.getCompanyById(50));
	}

	@Test
	public void testUpdateCompanySuccess() {
		CompanyEntity c = companyDAO.getCompanyById(1);
		c.setCeoName("LMNPO");
		assertEquals(true, companyDAO.updateCompany(c));
	}

	@Test(expected = NullPointerException.class)
	public void testUpdateCompanyFail() {
		CompanyEntity c = companyDAO.getCompanyById(90);
		c.setCeoName("LMNPO");
		assertEquals(true, companyDAO.updateCompany(c));
	}

	@Test
//	@Ignore
	public void testAddCompanySuccess() {
		CompanyEntity c = new CompanyEntity(1, "XYZ", "The CEO", directors, stockExchanges, "Technology",
				"Some Company", stockCodes, 33123123L);
		assertEquals(true, companyDAO.addCompany(c));
	}

	@Test(expected = DataIntegrityViolationException.class)
	@Ignore
	public void testAddCompanyFail() {
		CompanyEntity c = new CompanyEntity(1, "XYZ", "The CEO", directors, stockExchanges, "Technology",
				"Some Company", stockCodes, 33123123L);
		assertEquals(true, companyDAO.addCompany(c));
	}

	@Test
	@Ignore
	public void testDeleteCompanySuccess() {
		CompanyEntity c = companyDAO.getCompanyById(103);
		assertEquals(true, companyDAO.deleteCompany(c));
	}

	@Test(expected = IllegalArgumentException.class)
	@Ignore
	public void testDeleteCompanyFail() {
		CompanyEntity c = companyDAO.getCompanyById(23);
		assertEquals(true, companyDAO.deleteCompany(c));
	}
}
