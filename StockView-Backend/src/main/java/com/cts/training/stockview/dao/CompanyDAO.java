package com.cts.training.stockview.dao;

import java.util.List;

import com.cts.training.stockview.model.CompanyEntity;

public interface CompanyDAO {

	public boolean addCompany(CompanyEntity company);

	public boolean updateCompany(CompanyEntity company);

	public boolean deleteCompany(CompanyEntity company);

	public CompanyEntity getCompanyById(int id);

	public List<CompanyEntity> getAllCompanys();
	
}
