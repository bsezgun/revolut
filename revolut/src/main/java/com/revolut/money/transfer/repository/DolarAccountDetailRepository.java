package com.revolut.money.transfer.repository;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.revolut.money.transfer.entity.AccountDetailDolar;
/**
 * 
 * @author bsezgun
 * @version v.1.0.1
 *  Repository
 * @since   2018-09-19
 * 
 */
public class DolarAccountDetailRepository {

	private static DolarAccountDetailRepository accountDetailRepository;
	private SessionFactory sessionFactory;
	private Session session;
	
	private DolarAccountDetailRepository() {
		sessionFactory= InitRepository.buildSessionFactory(AccountDetailDolar.class);
		session=sessionFactory.openSession();
	
	}
	
	/**
	 * 
	 * @return DolarAccountDetailRepository
	 *  The singleton pattern use by this static method. One instance of this class can create. Also the constructor is private. 
	 */
	public static DolarAccountDetailRepository getAccountDetailRepository() {
		if(accountDetailRepository==null)
			accountDetailRepository= new DolarAccountDetailRepository();
		return accountDetailRepository;
	}
	
	public void closeSession() {
		sessionFactory.close();
	}
	
	/**
	 * 
	 * @param accountDetail {@link AccountDetailDolar}
	 *  save detail of the transfer transaction
	 * 
	 */
	public void saveAccountDetail(AccountDetailDolar accountDetail) {
		session.save(accountDetail);
	}
	
	/**
	 * 
	 * @param accountDetailId : Unique Id of the account_detail_dolar table
	 * @return AccountDetailDolar  {@link AccountDetailDolar}
	 *  get detail of the transfer transaction 
	 * 
	 */
	public AccountDetailDolar getAccountDetail(BigDecimal accountDetailId) {
		AccountDetailDolar accountDetail= session.get(AccountDetailDolar.class, accountDetailId);
		return accountDetail;
	}
	
	/**
	 * 
	 * @param accountId Foreign key of the AccountDolar table in the AccountDetailDolar.
	 * @return {@link List}
	 *  The account details of the all transfer transactions
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<AccountDetailDolar> getAccountDetailsForAccount(BigDecimal accountId) {
		
		String hql = "from AccountDetailDolar s where s.accountId = :accountId";
		
		return (List<AccountDetailDolar>)session.createQuery(hql)
				.setParameter("accountId", accountId)
				.list();
	}
}
