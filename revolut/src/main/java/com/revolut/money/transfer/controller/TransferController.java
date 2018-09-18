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
import com.revolut.money.transfer.util.Result;
import com.revolut.money.transfer.util.RevolutParams;

@Path("/bank")
public class TransferController {

	@POST
    @Path("/transfer/{toAccountId}/{fromAccountId}/{amount}")
    @Produces(MediaType.TEXT_PLAIN)
    public String transfer(@PathParam(value="toAccountId") BigDecimal toAccountId
    					,@PathParam(value="fromAccountId") BigDecimal fromAccountId
    					,@PathParam(value="amount") BigDecimal amount) throws JsonProcessingException {
        
		
		AccountService accountService=AccountService.getAccountService();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		return ow.writeValueAsString(accountService.transferToAccount(toAccountId, fromAccountId, amount));
    }
	
	@POST
    @Path("/deposit/{toAccountId}/{deposit}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deposit(@PathParam(value="toAccountId") BigDecimal toAccountId
    					,@PathParam(value="deposit") BigDecimal deposit) throws JsonProcessingException {
        
		AccountService accountService=AccountService.getAccountService();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		return ow.writeValueAsString(accountService.depositAccount(toAccountId, deposit));
    }
	
	@GET
    @Path("/account/{accountId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String findAccount(@PathParam(value="accountId") BigDecimal accountId) throws JsonProcessingException {
		Result result=null;
		AccountService accountService=AccountService.getAccountService();
		Account account=accountService.getAccount(accountId);
		if(account==null) {
			result=new Result(RevolutParams.NO_ACCOUNT_FOUND,RevolutParams.RESULT_STATU_FAIL, null);
		}else {
			result=new Result(RevolutParams.ACCOUNT_FOUND,RevolutParams.RESULT_STATU_SUCCESS, account);
		}
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		return ow.writeValueAsString(result);
    }
}
