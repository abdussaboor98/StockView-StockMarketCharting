package com.cts.training.stockview.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.training.stockview.dao.StockExchangeDAO;
import com.cts.training.stockview.model.StockExchangeEntity;

@Transactional
@Repository(value = "stockExchangeDAO")
public class StockExchangeDAOImpl implements StockExchangeDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean addStockExchange(StockExchangeEntity stockExchange) {
		try {
			sessionFactory.getCurrentSession().save(stockExchange);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateStockExchange(StockExchangeEntity stockExchange) {
		try {
			sessionFactory.getCurrentSession().update(stockExchange);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteStockExchange(StockExchangeEntity stockExchange) {
		try {
			sessionFactory.getCurrentSession().delete(stockExchange);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public StockExchangeEntity getStockExchangeById(int id) {
		try {
			return sessionFactory.getCurrentSession().get(StockExchangeEntity.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StockExchangeEntity> getAllStockExchanges() {
		try {
			return sessionFactory.getCurrentSession().createQuery("FROM StockExchangeEntity").getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

}
