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
 * 	 Repository of the H2 Table.
 *   Repository
 * 	@since 2018-09-19
 *  @version v.1.0.1
 */
public class EuroAccountRepository {

		private static EuroAccountRepository accountRepository;
		private SessionFactory sessionFactory;
		private Session session;
		
		private EuroAccountRepository() {
			sessionFactory= InitRepository.buildSessionFactory(AccountEuro.class);
			session=sessionFactory.openSession();
			
		}
		
		/**
		 * 
		 * @return EuroAccountRepository
		 *  The singleton pattern use by this static method. One instance of this class can create. Also the constructor is private. 
		 */
		public static EuroAccountRepository getAccountRepository() {
			if(accountRepository==null)
				accountRepository= new EuroAccountRepository();
			return accountRepository;
		}
		
		public void closeSession() {
			sessionFactory.close();
		}
		
		/**
		 * 
		 * @param accountId :Unique id of the AccountEuro table
		 * @param deposit : Amount of the money to deposit
		 */
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
		
		/**
		 * 
		 * @param accountId : Unique id of the AccountEuro table
		 * @param withDraw  : Amount of the money to withdraw
		 */
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
		
		/**
		 * 
		 * @param account {@link AccountEuro}
		 * @return void
		 *  save of the transfer transaction
		 */
		private void saveAccount(AccountEuro account) {
			session.save(account);
		}
		
		/**
		 * 
		 * @param accountId :Unique id of the AccountEuro table
		 * @return AccountEuro {@link AccountEuro}
		 */
		public AccountEuro getAccount(BigDecimal accountId) {
			return session.get(AccountEuro.class, accountId);
		}
		
		/**
		 * 
		 * @param accountId  :Unique id of the AccountEuro table
		 * @return List  {@link AccountDetailEuro}
		 *  get detail of the transfer transaction 
		 * 
		 */
		public List<AccountDetailEuro> getAccountDetails(BigDecimal accountId) {
			EuroAccountDetailRepository accountDetailRepository=EuroAccountDetailRepository.getAccountDetailRepository();
			return accountDetailRepository.getAccountDetailsForAccount(accountId);
		}
		
		/**
		 * 
		 * @return List  {@link AccountEuro}
		 *  this method is to test-purpose. To verify all accounts properly created. 
		 */
		@SuppressWarnings("unchecked")
		public List<AccountEuro> getAllAccounts() {
			return (List<AccountEuro>)session.createQuery("from AccountEuro").list();
		}
}
