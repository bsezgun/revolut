package com.revolut.money.transfer.service;

import java.math.BigDecimal;
import java.util.List;

import com.revolut.money.transfer.entity.Account;
import com.revolut.money.transfer.util.Result;

/**
 * 
 * @author bsezgun
 * @version v.1.0.1
 *  Business Service Interface
 * @since   2018-09-19
 *  The service interface. 
 */
public interface AccountService {

	/**
	 * 
	 * @param accountId :Unique Id of the account
	 * @param deposit : Amount of the money to deposit
	 * @return JSON String of the Result object {@link Result}
	 *  This service method for the test-purpose. You can deposit the account. If system not found the account than system will create automatically.
	 */
	public Result depositAccount(BigDecimal accountId,BigDecimal deposit);
	
	/**
	 * 
	 * @param toAccountId : Unique Id of the requester account
	 * @param fromAccountId : Unique Id of the sender account
	 * @param transferAmount : Amount of the money to transfer
	 * @return {@link Result}
	 */
	public Result transferToAccount(BigDecimal toAccountId,BigDecimal fromAccountId,BigDecimal transferAmount);
	
	/**
	 * 
	 * @param accountId : Unique Id of the requested account
	 * @return Account
	 */
	public Account getAccount(BigDecimal accountId);
	
	/**
	 * 
	 * @return {@link List}
	 *  this service method for the test-purpose for the test cases.
	 */
	public List<Account> getAllAccounts();
}
