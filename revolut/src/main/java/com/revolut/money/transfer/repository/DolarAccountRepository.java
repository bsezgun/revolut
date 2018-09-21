package com.revolut.money.transfer.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.revolut.money.transfer.entity.AccountDetailDolar;
import com.revolut.money.transfer.entity.AccountDolar;
import com.revolut.money.transfer.util.RevolutParams;


/**
 * 
 * 	@author bsezgun
 * 	 Repository of the H2 Table.
 *   Repository
 * 	@since 2018-09-19
 *  @version v.1.0.1
 */
public class DolarAccountRepository {

		private static DolarAccountRepository accountRepository;
		private SessionFactory sessionFactory;
		private Session session;
		
		private DolarAccountRepository() {
			sessionFactory= InitRepository.buildSessionFactory(AccountDolar.class);
			session=sessionFactory.openSession();
			
		}
		
		/**
		 * 
		 * @return DolarAccountRepository
		 *  The singleton pattern use by this static method. One instance of this class can create. Also the constructor is private. 
		 */
		public static DolarAccountRepository getAccountRepository() {
			if(accountRepository==null)
				accountRepository= new DolarAccountRepository();
			return accountRepository;
		}
		
		public void closeSession() {
			sessionFactory.close();
		}
		/**
		 * 
		 * @param accountId :Unique id of the AccountDolar table
		 * @param deposit : Amount of the money to deposit
		 */
		public void depositAccount(BigDecimal accountId,BigDecimal deposit) {
			
			AccountDolar account=getAccount(accountId);
			if(account==null) {
				account=new AccountDolar();
				account.setCurrency("USD");
				account.setAccountType(new BigDecimal(RevolutParams.ACCOUNT_TYPE_DOLAR));
				account.setAccountId(accountId);
				account.setAmount(new BigDecimal(0));
			}
			
			account.setAmount(new BigDecimal(account.getAmount().doubleValue()+deposit.doubleValue()));
			saveAccount(account);
			
			DolarAccountDetailRepository accountDetailRepository=DolarAccountDetailRepository.getAccountDetailRepository();
			AccountDetailDolar accountDetail=new AccountDetailDolar();
			accountDetail.setAccountId(accountId);
			accountDetail.setChangeDate(new Date());
			accountDetail.setDeposit(deposit);
			accountDetail.setWithDraw(new BigDecimal(0));
			accountDetailRepository.saveAccountDetail(accountDetail);
		
		}
		
		/**
		 * 
		 * @param accountId : Unique id of the AccountDolar table
		 * @param withDraw  : Amount of the money to withdraw
		 */
		public void withDrawAccount(BigDecimal accountId,BigDecimal withDraw) {
			
			session.beginTransaction();
			AccountDolar account=getAccount(accountId);
			
			account.setAmount(new BigDecimal(account.getAmount().doubleValue()-withDraw.doubleValue()));
			saveAccount(account);
			
			DolarAccountDetailRepository accountDetailRepository=DolarAccountDetailRepository.getAccountDetailRepository();
			AccountDetailDolar accountDetail=new AccountDetailDolar();
			accountDetail.setAccountId(accountId);
			accountDetail.setChangeDate(new Date());
			accountDetail.setDeposit(new BigDecimal(0));
			accountDetail.setWithDraw(withDraw);
			accountDetailRepository.saveAccountDetail(accountDetail);
			session.getTransaction().commit();
		}
		
		/**
		 * 
		 * @param account {@link AccountDolar}
		 * @return void
		 *  save of the transfer transaction
		 */
		private void saveAccount(AccountDolar account) {
			session.save(account);
		}
		
		/**
		 * 
		 * @param accountId :Unique id of the AccountDolar table
		 * @return AccountDolar {@link AccountDolar}
		 */
		public AccountDolar getAccount(BigDecimal accountId) {
			return session.get(AccountDolar.class, accountId);
		}
		
		/**
		 * 
		 * @param accountId  :Unique id of the AccountDolar table
		 * @return List  {@link AccountDetailDolar}
		 *  get detail of the transfer transaction 
		 * 
		 */
		public List<AccountDetailDolar> getAccountDetails(BigDecimal accountId) {
			DolarAccountDetailRepository accountDetailRepository=DolarAccountDetailRepository.getAccountDetailRepository();
			return accountDetailRepository.getAccountDetailsForAccount(accountId);
		}
		
		/**
		 * 
		 * @return {@link List}
		 *  this method is to test-purpose. To verify all accounts properly created. 
		 */
		@SuppressWarnings("unchecked")
		public List<AccountDolar> getAllAccounts() {
			return (List<AccountDolar>)session.createQuery("from AccountDolar").list();
		}
}
