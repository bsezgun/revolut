package com.revolut.money.transfer.facade;

import java.math.BigDecimal;

import com.revolut.money.transfer.entity.AccountEuro;
import com.revolut.money.transfer.repository.EuroAccountRepository;

public class EuroAccountFacade implements AccountFacade {

	public boolean isSufficentBalance(BigDecimal accountId, BigDecimal withDrawAmount) {
		EuroAccountRepository accountRepository=EuroAccountRepository.getAccountRepository();
		AccountEuro account=accountRepository.getAccount(accountId);
		if(account==null)
			return false;
		else if(account.getAmount().doubleValue()<withDrawAmount.doubleValue())
		  return false;
		
		return true;
	}

}
