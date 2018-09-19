package com.revolut.money.transfer.repository;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 * 
 * @author bsezgun
 * @since 2018-09-19
 * @version v.1.0.1
 *
 */
public class InitRepository {

	/**
	 * @param Entity class of H2 Database table	 *   
	 * @return {@link SessionFactory}
	 * @comment
	 *  This method create new SessionFactory for related Class<br/> to bind with H2 Database table.
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public  static SessionFactory buildSessionFactory(Class clazz) {
		return new Configuration()
				.configure()
				.addAnnotatedClass(clazz)
				.buildSessionFactory();
	}
}
