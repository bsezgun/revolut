package com.revolut.money.transfer.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import com.revolut.money.transfer.entity.Account;
import com.revolut.money.transfer.entity.AccountDetailEuro;
import com.revolut.money.transfer.entity.AccountEuro;
import com.revolut.money.transfer.facade.AccountFacade;
import com.revolut.money.transfer.facade.EuroAccountFacade;
import com.revolut.money.transfer.repository.EuroAccountRepository;
import com.revolut.money.transfer.util.Result;
import com.revolut.money.transfer.util.RevolutParams;

public class EuroAccountService implements AccountService{

	public Result depositAccount(BigDecimal accountId,BigDecimal deposit) {
		Result result=new Result(RevolutParams.DEPOSIT_TO_ACCOUNT_SUCCESS_MESSAGE ,RevolutParams.RESULT_STATU_SUCCESS, null);
		try {
			EuroAccountRepository accountRepository=EuroAccountRepository.getAccountRepository();
			accountRepository.depositAccount(accountId, deposit);
		} catch (Exception e) {
			result=new Result(RevolutParams.DEPOSIT_TO_ACCOUNT_FAIL_MESSAGE ,RevolutParams.RESULT_STATU_FAIL, null);
		}
		return result;
	}
	
	public Result transferToAccount(BigDecimal toAccountId,BigDecimal fromAccountId,BigDecimal transferAmount) {
		AccountFacade accountFacade=new EuroAccountFacade();
		if(!accountFacade.isTransferRestricted(transferAmount)) {
			return new Result(RevolutParams.TRANSFER_RESTRICTED, RevolutParams.RESULT_STATU_FAIL, null);
		}
		if(accountFacade.isSufficentBalance(fromAccountId, transferAmount)) {
			EuroAccountRepository accountRepository=EuroAccountRepository.getAccountRepository();
			accountRepository.withDrawAccount(fromAccountId, transferAmount);
			accountRepository.depositAccount(toAccountId, transferAmount);
			return new Result(RevolutParams.TRANSFER_TO_ACCOUNT_SUCCESS_MESSAGE ,RevolutParams.RESULT_STATU_SUCCESS, null);
		}else {
			return new Result(RevolutParams.INSUFFICIENT_BALANCE, RevolutParams.RESULT_STATU_FAIL, null);
		}
		
	}
	
	
	public Account getAccount(BigDecimal accountId) {
		EuroAccountRepository accountRepository=EuroAccountRepository.getAccountRepository();
		AccountEuro account=accountRepository.getAccount(accountId);
		account.setAccountDetails(getAccountDetails(accountId));
		Collections.sort(account.getAccountDetails());
		return accountRepository.getAccount(accountId);
	}
	
	private List<AccountDetailEuro> getAccountDetails(BigDecimal accountId) {
		EuroAccountRepository accountRepository=EuroAccountRepository.getAccountRepository();
		return accountRepository.getAccountDetails(accountId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Account> getAllAccounts() {
		EuroAccountRepository accountRepository=EuroAccountRepository.getAccountRepository();
		List<? extends Account> accounts=accountRepository.getAllAccounts();
		return (List<Account>)accounts;
	}
}
