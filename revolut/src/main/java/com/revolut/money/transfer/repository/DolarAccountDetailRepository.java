package com.revolut.money.transfer.repository;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.revolut.money.transfer.entity.AccountDetailDolar;

public class DolarAccountDetailRepository {

	private static DolarAccountDetailRepository accountDetailRepository;
	private SessionFactory sessionFactory;
	private Session session;
	
	private DolarAccountDetailRepository() {
		sessionFactory= InitRepository.buildSessionFactory(AccountDetailDolar.class);
		session=sessionFactory.openSession();
	
	}
	
	
	public static DolarAccountDetailRepository getAccountDetailRepository() {
		if(accountDetailRepository==null)
			accountDetailRepository= new DolarAccountDetailRepository();
		return accountDetailRepository;
	}
	
	public void closeSession() {
		sessionFactory.close();
	}
	
	public void saveAccountDetail(AccountDetailDolar accountDetail) {
		session.save(accountDetail);
	}
	
	public AccountDetailDolar getAccountDetail(BigDecimal accountDetailId) {
		AccountDetailDolar accountDetail= session.get(AccountDetailDolar.class, accountDetailId);
		return accountDetail;
	}
	
	@SuppressWarnings("unchecked")
	public List<AccountDetailDolar> getAccountDetailsForAccount(BigDecimal accountId) {
		
		String hql = "from AccountDetailDolar s where s.accountId = :accountId";
		
		return (List<AccountDetailDolar>)session.createQuery(hql)
				.setParameter("accountId", accountId)
				.list();
	}
}
