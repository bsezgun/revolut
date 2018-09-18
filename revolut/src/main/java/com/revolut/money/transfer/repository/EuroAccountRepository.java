package com.revolut.money.transfer.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.revolut.money.transfer.entity.AccountDetailEuro;
import com.revolut.money.transfer.entity.AccountEuro;



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
public class EuroAccountRepository {

		private static EuroAccountRepository accountRepository;
		private SessionFactory sessionFactory;
		private Session session;
		
		private EuroAccountRepository() {
			sessionFactory= InitRepository.buildSessionFactory(AccountEuro.class);
			session=sessionFactory.openSession();
			
		}
		
		
		public static EuroAccountRepository getAccountRepository() {
			if(accountRepository==null)
				accountRepository= new EuroAccountRepository();
			return accountRepository;
		}
		
		public void closeSession() {
			sessionFactory.close();
		}
		
		public void depositAccount(BigDecimal accountId,BigDecimal deposit) {
			
			AccountEuro account=getAccount(accountId);
			if(account==null) {
				account=new AccountEuro();
				account.setAccountId(accountId);
				account.setAmount(new BigDecimal(0));
			}
			
			account.setAmount(new BigDecimal(account.getAmount().doubleValue()+deposit.doubleValue()));
			saveAccount(account);
			
			EuroAccountDetailRepository accountDetailRepository=EuroAccountDetailRepository.getAccountDetailRepository();
			AccountDetailEuro accountDetail=new AccountDetailEuro();
			accountDetail.setAccountId(accountId);
			accountDetail.setChangeDate(new Date());
			accountDetail.setDeposit(deposit);
			accountDetail.setWithDraw(new BigDecimal(0));
			accountDetailRepository.saveAccountDetail(accountDetail);
		
		}
		
		public void withDrawAccount(BigDecimal accountId,BigDecimal withDraw) {
			
			session.beginTransaction();
			AccountEuro account=getAccount(accountId);
			
			
			account.setAmount(new BigDecimal(account.getAmount().doubleValue()-withDraw.doubleValue()));
			saveAccount(account);
			
			EuroAccountDetailRepository accountDetailRepository=EuroAccountDetailRepository.getAccountDetailRepository();
			AccountDetailEuro accountDetail=new AccountDetailEuro();
			accountDetail.setAccountId(accountId);
			accountDetail.setChangeDate(new Date());
			accountDetail.setDeposit(new BigDecimal(0));
			accountDetail.setWithDraw(withDraw);
			accountDetailRepository.saveAccountDetail(accountDetail);
			session.getTransaction().commit();
			
			
		}
		
		
		private void saveAccount(AccountEuro account) {
			session.save(account);
		}
		
		public AccountEuro getAccount(BigDecimal accountId) {
			return session.get(AccountEuro.class, accountId);
		}
		
		public List<AccountDetailEuro> getAccountDetails(BigDecimal accountId) {
			EuroAccountDetailRepository accountDetailRepository=EuroAccountDetailRepository.getAccountDetailRepository();
			return accountDetailRepository.getAccountDetailsForAccount(accountId);
		}
		
		@SuppressWarnings("unchecked")
		public List<AccountEuro> getAllAccounts() {
			return (List<AccountEuro>)session.createQuery("from AccountEuro").list();
		}
}
