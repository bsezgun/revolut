package com.revolut.money.transfer.facade;

import java.math.BigDecimal;
/**
 * 
 * @author bsezgun
 * @since 2018-09-19
 *  Business Service Control Interface
 * @version v.1.0.1
 */
public interface AccountFacade {

	
	
	
	/**
	 * 
	 * @param accountId : Unique Id of the account
	 * @param withDrawAmount :Amount of the money to withdraw
	 * @return boolean
	 *  This method responsibility is to control account balance.
	 *  that the account has sufficient balance to transfer or not
	 */
	public boolean isSufficentBalance(BigDecimal accountId,BigDecimal withDrawAmount);
	
	
	
	/**
	 * 
	 * @param withDrawAmount :Amount of the money to withdraw from the account
	 * @return boolean
	 *  This method responsibility is to control transfer amount.
	 *  that the transfer amount is suitable according to the predefined rules.
	 */
	public boolean isTransferNoRestricted(BigDecimal withDrawAmount);
	
}
