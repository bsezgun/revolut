package com.revolut.money.transfer.controller;

import java.math.BigDecimal;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.revolut.money.transfer.service.AccountService;
import com.revolut.money.transfer.service.DolarAccountService;
import com.revolut.money.transfer.service.EuroAccountService;
import com.revolut.money.transfer.util.Result;
import com.revolut.money.transfer.util.RevolutParams;
/**
 * @author bsezgun
 * @version v.1.0.1
 *  Rest Controller
 * @since   2018-09-19
 *  The rest end point.
 *
 */
@Path("/account")
public class AccountController {

	
	/**
	 * 
	 * @param accountType : Type of account USD:1, EUR:2
	 * @param accountId : Unique Id of the account
	 * @param deposit : Amount of the money to deposit
	 * @return JSON String of the Result object
	 *  This end point for the test-purpose. You can deposit the account. If system not found the account than system will create automatically.
	 */
	@Produces(MediaType.APPLICATION_JSON)
	@PUT
    @Path("/open/{accountType}/{accountId}/{amount}")
    public Result openAccount(
		@PathParam(value="accountType") BigDecimal accountType
		,@PathParam(value="accountId") BigDecimal accountId
		,@PathParam(value="amount") BigDecimal deposit) {

		AccountService accountTypes=null;
		if(accountType.intValue()==RevolutParams.ACCOUNT_TYPE_DOLAR)
			accountTypes=new DolarAccountService();
		else if(accountType.intValue()==RevolutParams.ACCOUNT_TYPE_EURO)
			accountTypes=new EuroAccountService();
		else
			return new Result(RevolutParams.NO_ACCOUNT_FOUND, RevolutParams.RESULT_STATU_FAIL, null);
		
		return accountTypes.depositAccount(accountId, deposit);
	}
	
}
