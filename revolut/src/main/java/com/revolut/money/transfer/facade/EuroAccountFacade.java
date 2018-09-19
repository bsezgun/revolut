package com.revolut.money.transfer.facade;

import java.math.BigDecimal;

import com.revolut.money.transfer.RevolutApp;
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

	@Override
	public boolean isTransferRestricted(BigDecimal withDrawAmount) {
		BigDecimal maxRestriction=new BigDecimal(RevolutApp.applicationProperties.get("withdraw.euro.maximum").toString());
		BigDecimal minRestriction=new BigDecimal(RevolutApp.applicationProperties.get("withdraw.euro.minimum").toString());
		
		if(withDrawAmount.doubleValue()>maxRestriction.doubleValue())
			return false;
		else if (withDrawAmount.doubleValue()<minRestriction.doubleValue())
			return false;	
		
		return true;
	}
	
	

}
