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
 * 	@comment
 *  This class create new SessionFactory for related Class<br/> to bind with H2 Database table.
 * 	@since
 *  18.09.2018
 *  @version
 *  v.1.0.1
 */
public class DolarAccountRepository {

		private static DolarAccountRepository accountRepository;
		private SessionFactory sessionFactory;
		private Session session;
		
		private DolarAccountRepository() {
			sessionFactory= InitRepository.buildSessionFactory(AccountDolar.class);
			session=sessionFactory.openSession();
			
		}
		
		
		public static DolarAccountRepository getAccountRepository() {
			if(accountRepository==null)
				accountRepository= new DolarAccountRepository();
			return accountRepository;
		}
		
		public void closeSession() {
			sessionFactory.close();
		}
		
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
		
		
		private void saveAccount(AccountDolar account) {
			session.save(account);
		}
		
		public AccountDolar getAccount(BigDecimal accountId) {
			return session.get(AccountDolar.class, accountId);
		}
		
		public List<AccountDetailDolar> getAccountDetails(BigDecimal accountId) {
			DolarAccountDetailRepository accountDetailRepository=DolarAccountDetailRepository.getAccountDetailRepository();
			return accountDetailRepository.getAccountDetailsForAccount(accountId);
		}
		
		@SuppressWarnings("unchecked")
		public List<AccountDolar> getAllAccounts() {
			return (List<AccountDolar>)session.createQuery("from AccountDolar").list();
		}
}
