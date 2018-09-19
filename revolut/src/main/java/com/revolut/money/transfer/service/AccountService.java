package com.revolut.money.transfer.service;

import java.math.BigDecimal;
import java.util.List;

import com.revolut.money.transfer.entity.Account;
import com.revolut.money.transfer.util.Result;

public interface AccountService {

	public Result depositAccount(BigDecimal accountId,BigDecimal deposit);
	public Result transferToAccount(BigDecimal toAccountId,BigDecimal fromAccountId,BigDecimal transferAmount);
	public Account getAccount(BigDecimal accountId);
	public List<Account> getAllAccounts();
}
