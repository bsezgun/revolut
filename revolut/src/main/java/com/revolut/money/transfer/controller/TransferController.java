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
import com.revolut.money.transfer.service.AccountTypes;
import com.revolut.money.transfer.service.DolarAccountService;
import com.revolut.money.transfer.service.EuroAccountService;
import com.revolut.money.transfer.util.Result;
import com.revolut.money.transfer.util.RevolutParams;

@Path("/bank")
public class TransferController {

	@POST
    @Path("/transfer/{accountType}/{toAccountId}/{fromAccountId}/{amount}")
    @Produces(MediaType.TEXT_PLAIN)
    public String transfer(@PathParam(value="accountType") BigDecimal accountType
    					,@PathParam(value="toAccountId") BigDecimal toAccountId
    					,@PathParam(value="fromAccountId") BigDecimal fromAccountId
    					,@PathParam(value="amount") BigDecimal amount) throws JsonProcessingException {
        
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		AccountTypes accountTypes=null;
		if(accountType.intValue()==RevolutParams.ACCOUNT_TYPE_DOLAR)
			accountTypes=new DolarAccountService();
		else if(accountType.intValue()==RevolutParams.ACCOUNT_TYPE_EURO)
			accountTypes=new EuroAccountService();
		else{
			return ow.writeValueAsString(new Result(RevolutParams.NO_ACCOUNT_FOUND, RevolutParams.RESULT_STATU_FAIL, null));
		}
		return ow.writeValueAsString(accountTypes.transferToAccount(toAccountId, fromAccountId, amount));
    }
	
	@POST
    @Path("/deposit/{accountType}/{toAccountId}/{deposit}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deposit(@PathParam(value="accountType") BigDecimal accountType
    					,@PathParam(value="toAccountId") BigDecimal toAccountId
    					,@PathParam(value="deposit") BigDecimal deposit) throws JsonProcessingException {
        
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		AccountTypes accountTypes=null;
		if(accountType.intValue()==RevolutParams.ACCOUNT_TYPE_DOLAR)
			accountTypes=new DolarAccountService();
		else if(accountType.intValue()==RevolutParams.ACCOUNT_TYPE_EURO)
			accountTypes=new EuroAccountService();
		else{
			return ow.writeValueAsString(new Result(RevolutParams.NO_ACCOUNT_FOUND, RevolutParams.RESULT_STATU_FAIL, null));
		}
		
		return ow.writeValueAsString(accountTypes.depositAccount(toAccountId, deposit));
    }
	
	@GET
    @Path("/account/{accountType}/{accountId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String findAccount(@PathParam(value="accountType") BigDecimal accountType,@PathParam(value="accountId") BigDecimal accountId) throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		AccountTypes accountTypes=null;
		if(accountType.intValue()==RevolutParams.ACCOUNT_TYPE_DOLAR)
			accountTypes=new DolarAccountService();
		else if(accountType.intValue()==RevolutParams.ACCOUNT_TYPE_EURO)
			accountTypes=new EuroAccountService();
		else{
			return ow.writeValueAsString(new Result(RevolutParams.NO_ACCOUNT_FOUND, RevolutParams.RESULT_STATU_FAIL, null));
		}
		
		Account account=accountTypes.getAccount(accountId);
		if(account==null) {
			return ow.writeValueAsString(new Result(RevolutParams.NO_ACCOUNT_FOUND,RevolutParams.RESULT_STATU_FAIL, null));
		}else {
			return ow.writeValueAsString(new Result(RevolutParams.ACCOUNT_FOUND,RevolutParams.RESULT_STATU_SUCCESS, account));
		}
		
		
    }
}
