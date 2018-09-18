package com.revolut.money.transfer.repository;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class InitRepository {

	/**
	 * @comment
	 *  This method create new SessionFactory for related Class<br/> to bind with H2 Database table.
	 * @param Entity class of H2 Database table	 *   
	 * @return {@link SessionFactory}
	 */
	@SuppressWarnings("rawtypes")
	public  static SessionFactory buildSessionFactory(Class clazz) {
		return new Configuration()
				.configure()
				.addAnnotatedClass(clazz)
				.buildSessionFactory();
	}
}
