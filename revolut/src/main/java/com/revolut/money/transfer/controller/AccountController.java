package com.revolut.money.transfer.controller;

import java.math.BigDecimal;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.revolut.money.transfer.service.AccountTypes;
import com.revolut.money.transfer.service.DolarAccountService;
import com.revolut.money.transfer.service.EuroAccountService;
import com.revolut.money.transfer.util.Result;
import com.revolut.money.transfer.util.RevolutParams;

@Path("/account")
public class AccountController {

	

	@PUT
    @Path("/open/{accountType}/{accountId}/{amount}")
    @Produces(MediaType.TEXT_PLAIN)
	public String test(
		@PathParam(value="accountType") BigDecimal accountType
		,@PathParam(value="accountId") BigDecimal accountId
		,@PathParam(value="amount") BigDecimal deposit) throws JsonProcessingException {

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		AccountTypes accountTypes=null;
		if(accountType.intValue()==RevolutParams.ACCOUNT_TYPE_DOLAR)
			accountTypes=new DolarAccountService();
		else if(accountType.intValue()==RevolutParams.ACCOUNT_TYPE_EURO){
			accountTypes=new EuroAccountService();
		}
		else{
			return ow.writeValueAsString(new Result(RevolutParams.NO_ACCOUNT_FOUND, RevolutParams.RESULT_STATU_FAIL, null));
		}
		
		return ow.writeValueAsString(accountTypes.depositAccount(accountId, deposit));
	}
	
}
