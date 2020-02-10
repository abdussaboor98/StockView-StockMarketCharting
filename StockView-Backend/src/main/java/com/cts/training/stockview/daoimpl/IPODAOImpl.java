package com.cts.training.stockview.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.training.stockview.dao.IPODAO;
import com.cts.training.stockview.model.IPOEntity;

@Transactional
@Repository(value = "ipoDAO")
public class IPODAOImpl implements IPODAO {

	@Autowired
	SessionFactory sessionFactory;
	@Override
	public boolean addIPO(IPOEntity ipo) {
		try {
			sessionFactory.getCurrentSession().save(ipo);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateIPO(IPOEntity ipo) {
		try {
			sessionFactory.getCurrentSession().update(ipo);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteIPO(IPOEntity ipo) {
		try {
			sessionFactory.getCurrentSession().delete(ipo);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public IPOEntity getIPOById(int id) {
		try {
			return sessionFactory.getCurrentSession().get(IPOEntity.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IPOEntity> getAllIPOs() {
		try {
			return sessionFactory.getCurrentSession().createQuery("FROM IPOEntity").getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}
}
