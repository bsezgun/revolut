package com.revolut.money.transfer.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.revolut.money.transfer.entity.Account;
import com.revolut.money.transfer.entity.AccountDetail;
import com.revolut.money.transfer.facade.AccountFacade;
import com.revolut.money.transfer.facade.Bank;
import com.revolut.money.transfer.repository.AccountDetailRepository;
import com.revolut.money.transfer.repository.AccountRepository;
import com.revolut.money.transfer.repository.InitRepository;
import com.revolut.money.transfer.util.Result;
import com.revolut.money.transfer.util.RevolutParams;
/**
 * @author bsezgun
 * @comment
 *  AccountService class is immutable singleton class.
 *  <br/>It creates SessionFactory by Account class and Session when it is instantiated.
 *  <br/>The Account table, create-drop by AccountService object when it is instantiated all time. 
 *  Please look at <b>hibernate.properties</b> file under the resources folder. 
 *  <br/>Session is not close until the application terminated.    
 * 	@since
 *  18.09.2018
 *  @version
 *  v.1.0.1
 */
public class AccountService {

	
	private static AccountService accountService;
	
	private AccountService() {
		
		
	}
	
	
	public static AccountService getAccountService() {
		if(accountService==null)
			accountService= new AccountService();
		return accountService;
	}
	

	public synchronized Result depositAccount(BigDecimal accountId,BigDecimal deposit) {
		Result result=new Result(RevolutParams.DEPOSIT_TO_ACCOUNT_SUCCESS_MESSAGE ,RevolutParams.RESULT_STATU_SUCCESS, null);
		try {
			AccountRepository accountRepository=AccountRepository.getAccountRepository();
			accountRepository.depositAccount(accountId, deposit);
		} catch (Exception e) {
			result=new Result(RevolutParams.DEPOSIT_TO_ACCOUNT_FAIL_MESSAGE ,RevolutParams.RESULT_STATU_FAIL, null);
		}
		return result;
	}
	
	public synchronized Result transferToAccount(BigDecimal toAccountId,BigDecimal fromAccountId,BigDecimal transferAmount) {
		Result result=new Result(RevolutParams.TRANSFER_TO_ACCOUNT_SUCCESS_MESSAGE ,RevolutParams.RESULT_STATU_SUCCESS, null);
		AccountFacade accountFacade=new Bank();
		if(accountFacade.isSufficentBalance(fromAccountId, transferAmount)) {
			AccountRepository accountRepository=AccountRepository.getAccountRepository();
			accountRepository.withDrawAccount(fromAccountId, transferAmount);
			accountRepository.depositAccount(toAccountId, transferAmount);
		}else {
			result=new Result(RevolutParams.INSUFFICIENT_BALANCE, RevolutParams.RESULT_STATU_FAIL, null);
		}
		return result;
	}
	
	
	public Account getAccount(BigDecimal accountId) {
		AccountRepository accountRepository=AccountRepository.getAccountRepository();
		Account account=accountRepository.getAccount(accountId);
		account.setAccountDetails(getAccountDetails(accountId));
		Collections.sort(account.getAccountDetails());
		return accountRepository.getAccount(accountId);
	}
	
	private List<AccountDetail> getAccountDetails(BigDecimal accountId) {
		AccountRepository accountRepository=AccountRepository.getAccountRepository();
		return accountRepository.getAccountDetails(accountId);
	}
	
	public List<Account> getAllAccounts() {
		AccountRepository accountRepository=AccountRepository.getAccountRepository();
		return accountRepository.getAllAccounts();
	}
	
	
	
	
	
}
