package com.revolut.money.transfer.repository;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AccountRepository {

	public  static SessionFactory buildSessionFactory(Class clazz) {
		return new Configuration()
				.configure()
				.addAnnotatedClass(clazz)
				.buildSessionFactory();
	}
}
