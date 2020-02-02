package com.cts.stockview.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.cts.stockview.dao.SectorDAO;
import com.cts.stockview.model.Sector;
import com.cts.stockview.model.Sector;
import com.cts.stockview.util.HibernateHelper;

public class SectorDAOImpl implements SectorDAO {

	@Override
	public boolean addSector(Sector sector) {
		try {
			SessionFactory sessionFactory = HibernateHelper.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(sector);
			tx.commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateSector(Sector sector) {
		try {
			SessionFactory sessionFactory = HibernateHelper.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.update(sector);
			tx.commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteSector(Sector sector) {
		try {
			SessionFactory sessionFactory = HibernateHelper.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.delete(sector);
			tx.commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Sector getSectorById(int id) {
		try {
			SessionFactory sessionFactory = HibernateHelper.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Sector sector = session.get(Sector.class, id);
			tx.commit();
			session.close();
			return sector;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Sector> getAllSectors() {
		try {
			SessionFactory sessionFactory = HibernateHelper.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			List<Sector> sectors = session.createQuery("FROM Sector").list();
			tx.commit();
			session.close();
			return sectors;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

}
