package com.revolut.money.transfer.facade;

import java.math.BigDecimal;

import com.revolut.money.transfer.entity.AccountDolar;
import com.revolut.money.transfer.repository.DolarAccountRepository;

public class DolarAccountFacade implements AccountFacade {

	public boolean isSufficentBalance(BigDecimal accountId, BigDecimal withDrawAmount) {
		DolarAccountRepository accountRepository=DolarAccountRepository.getAccountRepository();
		AccountDolar account=accountRepository.getAccount(accountId);
		if(account==null)
			return false;
		else if(account.getAmount().doubleValue()<withDrawAmount.doubleValue())
		  return false;
		
		return true;
	}

}
