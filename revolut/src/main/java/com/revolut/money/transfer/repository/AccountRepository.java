package com.revolut.money.transfer.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.revolut.money.transfer.entity.Account;
import com.revolut.money.transfer.entity.AccountDetail;

/**
 * 
 * 	@author bsezgun
 * 	@comment
 *  This class create new SessionFactory for related Class<br/> to bind with H2 Database table.
 * 	@since
 *  18.09.2018
 *  @version
 *  v.1.0.1
 */

public class AccountRepository {

	private static AccountRepository accountRepository;
	private SessionFactory sessionFactory;
	private Session session;
	
	private AccountRepository() {
		sessionFactory= InitRepository.buildSessionFactory(Account.class);
		session=sessionFactory.openSession();
		
	}
	
	
	public static AccountRepository getAccountRepository() {
		if(accountRepository==null)
			accountRepository= new AccountRepository();
		return accountRepository;
	}
	
	public void closeSession() {
		sessionFactory.close();
	}
	
	public void depositAccount(BigDecimal accountId,BigDecimal deposit) {
		
		Account account=getAccount(accountId);
		if(account==null) {
			account=new Account();
			account.setAccountId(accountId);
			account.setAmount(new BigDecimal(0));
		}
		
		account.setAmount(new BigDecimal(account.getAmount().doubleValue()+deposit.doubleValue()));
		saveAccount(account);
		
		AccountDetailRepository accountDetailRepository=AccountDetailRepository.getAccountDetailRepository();
		AccountDetail accountDetail=new AccountDetail();
		accountDetail.setAccountId(accountId);
		accountDetail.setChangeDate(new Date());
		accountDetail.setDeposit(deposit);
		accountDetail.setWithDraw(new BigDecimal(0));
		accountDetailRepository.saveAccountDetail(accountDetail);
	
	}
	
	public void withDrawAccount(BigDecimal accountId,BigDecimal withDraw) {
		
		session.beginTransaction();
		Account account=getAccount(accountId);
		
		
		account.setAmount(new BigDecimal(account.getAmount().doubleValue()-withDraw.doubleValue()));
		saveAccount(account);
		
		AccountDetailRepository accountDetailRepository=AccountDetailRepository.getAccountDetailRepository();
		AccountDetail accountDetail=new AccountDetail();
		accountDetail.setAccountId(accountId);
		accountDetail.setChangeDate(new Date());
		accountDetail.setDeposit(new BigDecimal(0));
		accountDetail.setWithDraw(withDraw);
		accountDetailRepository.saveAccountDetail(accountDetail);
		session.getTransaction().commit();
		
		
	}
	
	
	private void saveAccount(Account account) {
		session.save(account);
	}
	
	public Account getAccount(BigDecimal accountId) {
		return session.get(Account.class, accountId);
	}
	
	public List<AccountDetail> getAccountDetails(BigDecimal accountId) {
		AccountDetailRepository accountDetailRepository=AccountDetailRepository.getAccountDetailRepository();
		return accountDetailRepository.getAccountDetailsForAccount(accountId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Account> getAllAccounts() {
		return (List<Account>)session.createQuery("from Account").list();
	}
	
}
