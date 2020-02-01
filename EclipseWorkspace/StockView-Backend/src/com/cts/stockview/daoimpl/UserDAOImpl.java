package com.cts.stockview.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.cts.stockview.dao.UserDAO;
import com.cts.stockview.model.User;
import com.cts.stockview.util.HibernateHelper;

public class UserDAOImpl implements UserDAO {

	@Override
	public boolean addUser(User user) {
		try {
			SessionFactory sessionFactory = HibernateHelper.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(user);
			tx.commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateUser(User user) {
		try {
			SessionFactory sessionFactory = HibernateHelper.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.update(user);
			tx.commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteUser(User user) {
		try {
			SessionFactory sessionFactory = HibernateHelper.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.delete(user);
			tx.commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User getUserById(int id) {
		try {
			SessionFactory sessionFactory = HibernateHelper.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			User user = session.get(User.class, id);
			tx.commit();
			session.close();
			return user;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<User> getAllUsers() {
		try {
			SessionFactory sessionFactory = HibernateHelper.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			List<User> users = session.createQuery("FROM User").list();
			tx.commit();
			session.close();
			return users;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}
}
