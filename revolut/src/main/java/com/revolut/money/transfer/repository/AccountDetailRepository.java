package com.revolut.money.transfer.repository;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.revolut.money.transfer.entity.AccountDetail;

public class AccountDetailRepository {

	private static AccountDetailRepository accountDetailRepository;
	private SessionFactory sessionFactory;
	private Session session;
	
	private AccountDetailRepository() {
		sessionFactory= InitRepository.buildSessionFactory(AccountDetail.class);
		session=sessionFactory.openSession();
	
	}
	
	
	public static AccountDetailRepository getAccountDetailRepository() {
		if(accountDetailRepository==null)
			accountDetailRepository= new AccountDetailRepository();
		return accountDetailRepository;
	}
	
	public void closeSession() {
		sessionFactory.close();
	}
	
	public void saveAccountDetail(AccountDetail accountDetail) {
		session.save(accountDetail);
	}
	
	public AccountDetail getAccountDetail(BigDecimal accountDetailId) {
		AccountDetail accountDetail= session.get(AccountDetail.class, accountDetailId);
		return accountDetail;
	}
	
	@SuppressWarnings("unchecked")
	public List<AccountDetail> getAccountDetailsForAccount(BigDecimal accountId) {
		
		String hql = "from AccountDetail s where s.accountId = :accountId";
		
		return (List<AccountDetail>)session.createQuery(hql)
				.setParameter("accountId", accountId)
				.list();
	}
}
