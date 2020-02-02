package com.cts.stockview.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.cts.stockview.dao.IPODAO;
import com.cts.stockview.model.IPO;
import com.cts.stockview.util.HibernateHelper;

public class IPODAOImpl implements IPODAO {

	@Override
	public boolean addIPO(IPO ipo) {
		try {
			SessionFactory sessionFactory = HibernateHelper.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(ipo);
			tx.commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateIPO(IPO ipo) {
		try {
			SessionFactory sessionFactory = HibernateHelper.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.update(ipo);
			tx.commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteIPO(IPO ipo) {
		try {
			SessionFactory sessionFactory = HibernateHelper.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.delete(ipo);
			tx.commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public IPO getIPOById(int id) {
		try {
			SessionFactory sessionFactory = HibernateHelper.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			IPO ipo = session.get(IPO.class, id);
			tx.commit();
			session.close();
			return ipo;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<IPO> getAllIPOs() {
		try {
			SessionFactory sessionFactory = HibernateHelper.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			List<IPO> ipos = session.createQuery("FROM IPO").list();
			tx.commit();
			session.close();
			return ipos;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}
}
