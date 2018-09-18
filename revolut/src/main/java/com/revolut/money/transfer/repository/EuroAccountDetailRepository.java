package com.revolut.money.transfer.repository;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.revolut.money.transfer.entity.AccountDetailEuro;

public class EuroAccountDetailRepository {

	private static EuroAccountDetailRepository accountDetailRepository;
	private SessionFactory sessionFactory;
	private Session session;
	
	private EuroAccountDetailRepository() {
		sessionFactory= InitRepository.buildSessionFactory(AccountDetailEuro.class);
		session=sessionFactory.openSession();
	
	}
	
	
	public static EuroAccountDetailRepository getAccountDetailRepository() {
		if(accountDetailRepository==null)
			accountDetailRepository= new EuroAccountDetailRepository();
		return accountDetailRepository;
	}
	
	public void closeSession() {
		sessionFactory.close();
	}
	
	public void saveAccountDetail(AccountDetailEuro accountDetail) {
		session.save(accountDetail);
	}
	
	public AccountDetailEuro getAccountDetail(BigDecimal accountDetailId) {
		AccountDetailEuro accountDetail= session.get(AccountDetailEuro.class, accountDetailId);
		return accountDetail;
	}
	
	@SuppressWarnings("unchecked")
	public List<AccountDetailEuro> getAccountDetailsForAccount(BigDecimal accountId) {
		
		String hql = "from AccountDetailEuro s where s.accountId = :accountId";
		
		return (List<AccountDetailEuro>)session.createQuery(hql)
				.setParameter("accountId", accountId)
				.list();
	}
}
