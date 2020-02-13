package com.cts.training.stockview.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.training.stockview.dao.StockPriceDAO;
import com.cts.training.stockview.model.StockPriceEntity;

@Transactional
@Repository(value = "stockPriceDAO")
public class StockPriceDAOImpl implements StockPriceDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean addStockPrice(StockPriceEntity stockPrice) {
		try {
			sessionFactory.getCurrentSession().save(stockPrice);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateStockPrice(StockPriceEntity stockPrice) {
		try {
			sessionFactory.getCurrentSession().update(stockPrice);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteStockPrice(StockPriceEntity stockPrice) {
		try {
			sessionFactory.getCurrentSession().delete(stockPrice);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public StockPriceEntity getStockPriceById(int id) {
		try {
			return sessionFactory.getCurrentSession().get(StockPriceEntity.class,id);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StockPriceEntity> getAllStockPrices() {
		try {
			return sessionFactory.getCurrentSession().createQuery("FROM StockPriceEntity").getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean addOrUpdateStockPrice(StockPriceEntity stockPrice) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(stockPrice);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}

}
