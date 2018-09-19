package com.revolut.money.transfer.facade;

import java.math.BigDecimal;
/**
 * 
 * @author bsezgun
 * @since 2018-09-19
 * @version v.1.0.1
 */
public interface AccountFacade {

	/**
	 * 
	 * @param accountId
	 * @param withDrawAmount
	 * @return boolean
	 * @comment This method responsibility is to control account balance<br/>
	 *  that the account has sufficient balance to transfer or not
	 */
	public boolean isSufficentBalance(BigDecimal accountId,BigDecimal withDrawAmount);
	
	/**
	 * 
	 * @param withDrawAmount
	 * @return boolean
	 * @comment This method responsibility is to control transfer amount<br/>
	 *  that the transfer amount is suitable according to the predefined rules.
	 */
	public boolean isTransferRestricted(BigDecimal withDrawAmount);
	
}
