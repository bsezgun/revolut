package com.revolut.money.transfer.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import com.revolut.money.transfer.entity.Account;
import com.revolut.money.transfer.entity.AccountDetailDolar;
import com.revolut.money.transfer.entity.AccountDolar;
import com.revolut.money.transfer.facade.AccountFacade;
import com.revolut.money.transfer.facade.DolarAccountFacade;
import com.revolut.money.transfer.repository.DolarAccountRepository;
import com.revolut.money.transfer.util.Result;
import com.revolut.money.transfer.util.RevolutParams;
/**
 * 
 * @author bsezgun
 * @version v.1.0.1
 * @category Business Service
 * @since   2018-09-19
 * @comment Business Service to Transfer to Dolar account
 */
public class DolarAccountService implements AccountService {

	public DolarAccountService() {
	}
	
	
	public Result depositAccount(BigDecimal accountId,BigDecimal deposit) {
		Result result=new Result(RevolutParams.DEPOSIT_TO_ACCOUNT_SUCCESS_MESSAGE ,RevolutParams.RESULT_STATU_SUCCESS, null);
		try {
			DolarAccountRepository accountRepository=DolarAccountRepository.getAccountRepository();
			accountRepository.depositAccount(accountId, deposit);
		} catch (Exception e) {
			result=new Result(RevolutParams.DEPOSIT_TO_ACCOUNT_FAIL_MESSAGE ,RevolutParams.RESULT_STATU_FAIL, null);
		}
		return result;
	}
	
	public Result transferToAccount(BigDecimal toAccountId,BigDecimal fromAccountId,BigDecimal transferAmount) {
		AccountFacade accountFacade=new DolarAccountFacade();
		if(!accountFacade.isTransferNoRestricted(transferAmount)) {
			return new Result(RevolutParams.TRANSFER_RESTRICTED, RevolutParams.RESULT_STATU_RESTRICTED, null);
		}
		if(accountFacade.isSufficentBalance(fromAccountId, transferAmount)) {
			DolarAccountRepository accountRepository=DolarAccountRepository.getAccountRepository();
			accountRepository.withDrawAccount(fromAccountId, transferAmount);
			accountRepository.depositAccount(toAccountId, transferAmount);
			return new Result(RevolutParams.TRANSFER_TO_ACCOUNT_SUCCESS_MESSAGE ,RevolutParams.RESULT_STATU_SUCCESS, null);
		}else {
			return new Result(RevolutParams.INSUFFICIENT_BALANCE, RevolutParams.RESULT_STATU_FAIL, null);
		}
		
	}
	
	public Account getAccount(BigDecimal accountId) {
		DolarAccountRepository accountRepository=DolarAccountRepository.getAccountRepository();
		AccountDolar account=accountRepository.getAccount(accountId);
		account.setAccountDetails(getAccountDetails(accountId));
		Collections.sort(account.getAccountDetails());
		return accountRepository.getAccount(accountId);
	}
	
	private List<AccountDetailDolar> getAccountDetails(BigDecimal accountId) {
		DolarAccountRepository accountRepository=DolarAccountRepository.getAccountRepository();
		return accountRepository.getAccountDetails(accountId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Account> getAllAccounts() {
		DolarAccountRepository accountRepository=DolarAccountRepository.getAccountRepository();
		List<? extends Account> accounts=accountRepository.getAllAccounts();
		return (List<Account>)accounts;
	}
}
