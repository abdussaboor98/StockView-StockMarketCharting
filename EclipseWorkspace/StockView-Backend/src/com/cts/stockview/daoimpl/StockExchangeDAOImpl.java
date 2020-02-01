package com.cts.stockview.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.cts.stockview.dao.StockExchangeDAO;
import com.cts.stockview.model.StockExchange;
import com.cts.stockview.model.User;
import com.cts.stockview.util.HibernateHelper;

public class StockExchangeDAOImpl implements StockExchangeDAO {

	@Override
	public boolean addStockExchange(StockExchange stockExchange) {
		try {
			SessionFactory sessionFactory = HibernateHelper.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(stockExchange);
			tx.commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateStockExchange(StockExchange stockExchange) {
		try {
			SessionFactory sessionFactory = HibernateHelper.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.update(stockExchange);
			tx.commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteStockExchange(StockExchange stockExchange) {
		try {
			SessionFactory sessionFactory = HibernateHelper.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.delete(stockExchange);
			tx.commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public StockExchange getStockExchangeById(int id) {
		try {
			SessionFactory sessionFactory = HibernateHelper.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			StockExchange stockExchange = session.get(StockExchange.class, id);
			tx.commit();
			session.close();
			return stockExchange;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<StockExchange> getAllStockExchanges() {
		try {
			SessionFactory sessionFactory = HibernateHelper.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			List <StockExchange> stockExchanges = session.createQuery("FROM StockExchange").list();
			tx.commit();
			session.close();
			return stockExchanges;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

}
