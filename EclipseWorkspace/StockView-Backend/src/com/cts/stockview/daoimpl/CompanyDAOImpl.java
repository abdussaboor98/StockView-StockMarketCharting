package com.cts.stockview.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.cts.stockview.dao.CompanyDAO;
import com.cts.stockview.model.Company;
import com.cts.stockview.util.HibernateHelper;

public class CompanyDAOImpl implements CompanyDAO {

	@Override
	public boolean addCompany(Company company) {
		try {
			SessionFactory sessionFactory = HibernateHelper.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(company);
			tx.commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateCompany(Company company) {
		try {
			SessionFactory sessionFactory = HibernateHelper.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.update(company);
			tx.commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteCompany(Company company) {
		try {
			SessionFactory sessionFactory = HibernateHelper.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.delete(company);
			tx.commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Company getCompanyById(int id) {
		try {
			SessionFactory sessionFactory = HibernateHelper.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Company company = session.get(Company.class,id);
			tx.commit();
			session.close();
			return company;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Company> getAllCompanys() {
		try {
			SessionFactory sessionFactory = HibernateHelper.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			List<Company> companies = session.createQuery("FROM Company").list();
			tx.commit();
			session.close();
			return companies;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

}
