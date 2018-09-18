package com.revolut.money.transfer.facade;

import java.math.BigDecimal;

public interface AccountFacade {

	public boolean isSufficentBalance(BigDecimal accountId,BigDecimal withDrawAmount);
	
	
}
