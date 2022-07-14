package com.gla.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class HibernateUtilMySql {
	
	
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			return new Configuration().configure("config/hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		// Close caches and connection pools
		HibernateUtilMySql.getSessionFactory().close();
	
	}

}
