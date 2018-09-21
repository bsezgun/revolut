package com.revolut.money.transfer.repository;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 * 
 * @author bsezgun
 * @since 2018-09-19
 * @version v.1.0.1
 *  Repository Provider
 *
 */
public class InitRepository {

	/**
	 * @param clazz: Entity class of H2 Database table	 *   
	 * @return {@link SessionFactory}
	 * 
	 *  This method create new SessionFactory for related Class. to bind with H2 Database table.
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
