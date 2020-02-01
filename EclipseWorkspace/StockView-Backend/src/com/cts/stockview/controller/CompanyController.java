package com.cts.stockview.controller;

import java.util.ArrayList;
import java.util.List;

import com.cts.stockview.dao.CompanyDAO;
import com.cts.stockview.daoimpl.CompanyDAOImpl;
import com.cts.stockview.model.Company;

public class CompanyController {

	public static void main(String[] args) {
		List<String> directors = new ArrayList<String>();
		directors.add("Syed Ehsan");
		directors.add("Aneena Thomas");

		List<String> stockExchanges = new ArrayList<String>();
		stockExchanges.add("BSE");
		stockExchanges.add("NSE");

		List<String> stockCodes = new ArrayList<String>();
		stockCodes.add("ABCD");
		stockCodes.add("ABCE");

		Company company = new Company(101, "ABC", "Abdus Saboor", directors, stockExchanges, "Technology",
				"This is a company.", stockCodes, 737646759L);
		
		CompanyDAO companyDAO = new CompanyDAOImpl();
		companyDAO.addCompany(company);
	}
}
