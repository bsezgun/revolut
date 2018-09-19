package com.revolut.money.transfer.controller;

import java.math.BigDecimal;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.revolut.money.transfer.entity.Account;
import com.revolut.money.transfer.service.AccountService;
import com.revolut.money.transfer.service.DolarAccountService;
import com.revolut.money.transfer.service.EuroAccountService;
import com.revolut.money.transfer.util.Result;
import com.revolut.money.transfer.util.RevolutParams;
/**
 * 
 * @author bsezgun
 * @version v.1.0.1
 * @category Rest Controller
 * @since   2018-09-19
 * @comment The rest end point.
 */
@Path("/bank")
public class TransferController {

	/**
	 * @param accountType : Type of account USD:1, EUR:2
	 * @param toAccountId : Unique Id of the requester account
	 * @param fromAccountId : Unique Id of the sender account
	 * @param amount : Amount of the money to transfer
	 * @return JSON String of the Result object
	 * @comment This rest end point transfer money from the account to another account. 
	 */
	@Produces(MediaType.APPLICATION_JSON)
	@POST
    @Path("/transfer/{accountType}/{toAccountId}/{fromAccountId}/{amount}")
    public Result transfer(@PathParam(value="accountType") BigDecimal accountType
    					,@PathParam(value="toAccountId") BigDecimal toAccountId
    					,@PathParam(value="fromAccountId") BigDecimal fromAccountId
    					,@PathParam(value="amount") BigDecimal amount)  {
        AccountService accountTypes=null;
		if(accountType.intValue()==RevolutParams.ACCOUNT_TYPE_DOLAR)
			accountTypes=new DolarAccountService();
		else if(accountType.intValue()==RevolutParams.ACCOUNT_TYPE_EURO)
			accountTypes=new EuroAccountService();
		else{
			return new Result(RevolutParams.NO_ACCOUNT_FOUND, RevolutParams.RESULT_STATU_FAIL, null);
		}
		return accountTypes.transferToAccount(toAccountId, fromAccountId, amount);
    }
	
	/**
	 * @param accountType : Type of account USD:1, EUR:2
	 * @param toAccountId : Unique Id of the account
	 * @param deposit : Amount of the money to deposit
	 * @return JSON String of the Result object
	 * @comment This end point for the testing purposes. You can deposit the account. If system not found the account than system will create automatically.
	 */
	@Produces(MediaType.APPLICATION_JSON)
	@POST
    @Path("/deposit/{accountType}/{toAccountId}/{deposit}")
    public Result deposit(@PathParam(value="accountType") BigDecimal accountType
    					,@PathParam(value="toAccountId") BigDecimal toAccountId
    					,@PathParam(value="deposit") BigDecimal deposit)  {
        
		AccountService accountTypes=null;
		if(accountType.intValue()==RevolutParams.ACCOUNT_TYPE_DOLAR)
			accountTypes=new DolarAccountService();
		else if(accountType.intValue()==RevolutParams.ACCOUNT_TYPE_EURO)
			accountTypes=new EuroAccountService();
		else{
			return new Result(RevolutParams.NO_ACCOUNT_FOUND, RevolutParams.RESULT_STATU_FAIL, null);
		}
		
		return accountTypes.depositAccount(toAccountId, deposit);
    }
	
	/**
	 * 
	 * @param accountType :Type of account USD:1, EUR:2
	 * @param accountId : Unique Id of the account
	 * @return JSON String of the Result object
	 * @comment This end point for the testing purposes. You can view account details by this end point.
	 */
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/account/{accountType}/{accountId}")
    public Result findAccount(@PathParam(value="accountType") BigDecimal accountType,@PathParam(value="accountId") BigDecimal accountId)  {
		AccountService accountTypes=null;
		if(accountType.intValue()==RevolutParams.ACCOUNT_TYPE_DOLAR)
			accountTypes=new DolarAccountService();
		else if(accountType.intValue()==RevolutParams.ACCOUNT_TYPE_EURO)
			accountTypes=new EuroAccountService();
		else{
			return new Result(RevolutParams.NO_ACCOUNT_FOUND, RevolutParams.RESULT_STATU_FAIL, null);
		}
		
		Account account=accountTypes.getAccount(accountId);
		if(account==null) {
			return new Result(RevolutParams.NO_ACCOUNT_FOUND,RevolutParams.RESULT_STATU_FAIL, null);
		}else {
			return new Result(RevolutParams.ACCOUNT_FOUND,RevolutParams.RESULT_STATU_SUCCESS, account);
		}
		
		
    }
}
