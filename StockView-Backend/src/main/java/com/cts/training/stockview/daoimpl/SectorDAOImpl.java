package com.cts.training.stockview.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cts.training.stockview.dao.SectorDAO;
import com.cts.training.stockview.model.SectorEntity;

public class SectorDAOImpl implements SectorDAO {

	@Autowired
	SessionFactory sessionFactory;
	@Override
	public boolean addSector(SectorEntity sector) {
		try {
			sessionFactory.getCurrentSession().save(sector);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateSector(SectorEntity sector) {
		try {
			sessionFactory.getCurrentSession().update(sector);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteSector(SectorEntity sector) {
		try {
			sessionFactory.getCurrentSession().delete(sector);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public SectorEntity getSectorById(int id) {
		try {
			return sessionFactory.getCurrentSession().get(SectorEntity.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SectorEntity> getAllSectors() {
		try {
			return sessionFactory.getCurrentSession().createQuery("FROM SectorEntity").getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

}
