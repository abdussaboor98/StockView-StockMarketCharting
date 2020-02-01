package com.cts.stockview.dao;

import java.util.List;

import com.cts.stockview.model.Company;

public interface CompanyDAO {

	public boolean addCompany(Company company);

	public boolean updateCompany(Company company);

	public boolean deleteCompany(Company company);

	public Company getCompanyById(int id);

	public List<Company> getAllCompanys();
	
}
