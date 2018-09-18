package com.revolut.money.transfer.facade;

import java.math.BigDecimal;

import com.revolut.money.transfer.entity.Account;
import com.revolut.money.transfer.repository.AccountRepository;

public class Bank implements AccountFacade {

	public boolean isSufficentBalance(BigDecimal accountId, BigDecimal withDrawAmount) {
		AccountRepository accountRepository=AccountRepository.getAccountRepository();
		Account account=accountRepository.getAccount(accountId);
		if(account==null)
			return false;
		else if(account.getAmount().doubleValue()<withDrawAmount.doubleValue())
		  return false;
		
		return true;
	}

}
