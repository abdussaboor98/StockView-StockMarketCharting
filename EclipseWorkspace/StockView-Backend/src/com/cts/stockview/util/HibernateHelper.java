package com.cts.stockview.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateHelper {

	public static SessionFactory getSessionFactory() {
		
		Configuration cfg = new Configuration();
		cfg.configure();
		return cfg.buildSessionFactory();
	}
}
