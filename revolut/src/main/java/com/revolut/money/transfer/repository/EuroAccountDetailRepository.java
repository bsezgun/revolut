package com.revolut.money.transfer.repository;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.revolut.money.transfer.entity.AccountDetailEuro;
/**
 * 
 * @author bsezgun
 * @version v.1.0.1
 *  Repository
 * @since   2018-09-19
 * 
 */
public class EuroAccountDetailRepository {

	private static EuroAccountDetailRepository accountDetailRepository;
	private SessionFactory sessionFactory;
	private Session session;
	
	private EuroAccountDetailRepository() {
		sessionFactory= InitRepository.buildSessionFactory(AccountDetailEuro.class);
		session=sessionFactory.openSession();
	
	}
	
	/**
	 * 
	 * @return EuroAccountDetailRepository
	 *  The singleton pattern use by this static method. One instance of this class can create. Also the constructor is private. 
	 */
	public static EuroAccountDetailRepository getAccountDetailRepository() {
		if(accountDetailRepository==null)
			accountDetailRepository= new EuroAccountDetailRepository();
		return accountDetailRepository;
	}
	
	public void closeSession() {
		sessionFactory.close();
	}
	
	/**
	 * 
	 * @param accountDetail {@link AccountDetailEuro}
	 * save detail of the transfer transaction
	 * 
	 */
	public void saveAccountDetail(AccountDetailEuro accountDetail) {
		session.save(accountDetail);
	}
	
 /**
  * 
  * @param accountDetailId : Unique id of the account_detail_euro table
  * @return AccountDetailEuro 
  */
	public AccountDetailEuro getAccountDetail(BigDecimal accountDetailId) {
		AccountDetailEuro accountDetail= session.get(AccountDetailEuro.class, accountDetailId);
		return accountDetail;
	}
	

	/**
	 * 
	 * @param accountId Foreign key of the AccountEuro table in the AccountDetailEuro.
	 * @return {@link List}
	 *  The account details of the all transfer transactions
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<AccountDetailEuro> getAccountDetailsForAccount(BigDecimal accountId) {
		
		String hql = "from AccountDetailEuro s where s.accountId = :accountId";
		
		return (List<AccountDetailEuro>)session.createQuery(hql)
				.setParameter("accountId", accountId)
				.list();
	}
}
