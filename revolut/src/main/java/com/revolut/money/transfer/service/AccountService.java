package com.revolut.money.transfer.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.revolut.money.transfer.entity.Account;
import com.revolut.money.transfer.repository.AccountRepository;

public class AccountService {

	private static AccountService accountService;
	
	private AccountService() {
		
	}
	
	public static AccountService getAccountService() {
		if(accountService==null)
			accountService= new AccountService();
		return accountService;
	}
	
	public void saveAccount(Account account) {
		SessionFactory sessionFactory= AccountRepository.buildSessionFactory(Account.class);
		Session session=sessionFactory.openSession();
		session.save(account);
		sessionFactory.close();
	}
	
	public Account getAccount(Long accountId) {
		SessionFactory sessionFactory= AccountRepository.buildSessionFactory(Account.class);
		Session session=sessionFactory.openSession();
		Account account= session.get(Account.class, accountId);
		sessionFactory.close();
		return account;
	}
	
	
}
