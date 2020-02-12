package com.cts.training.stockview.daoimpl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cts.training.stockview.dao.CompanyDAO;
import com.cts.training.stockview.model.CompanyEntity;

@Repository(value = "companyDAO")
public class CompanyDAOImpl implements CompanyDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
//	@Autowired
//	private HibernateTransactionManager transactionManager;

	@Override
	public boolean addCompany(CompanyEntity company) {
		try {
			sessionFactory.getCurrentSession().save(company);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateCompany(CompanyEntity company) {
		try {
			sessionFactory.getCurrentSession().update(company);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteCompany(CompanyEntity company) {
		try {
			sessionFactory.getCurrentSession().delete(company);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public CompanyEntity getCompanyById(int id) {
		try {
			return sessionFactory.getCurrentSession().get(CompanyEntity.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<CompanyEntity> getAllCompanys() {
		try {
			List<CompanyEntity> companies = sessionFactory.getCurrentSession().createQuery("FROM CompanyEntity").list();
			for(CompanyEntity company:companies) {
				Hibernate.initialize(company.getDirectors());
				Hibernate.initialize(company.getStockExchanges());
				Hibernate.initialize(company.getStockCodes());
			}
			return companies;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

}
