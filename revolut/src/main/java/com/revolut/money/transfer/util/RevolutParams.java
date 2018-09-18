package com.revolut.money.transfer.util;

import java.math.BigDecimal;

public class RevolutParams {

	
	public final static String RESULT_STATU_SUCCESS="SUCCESS";
	public final static String RESULT_STATU_FAIL="FAIL";
	
	
	public final static String DEPOSIT_TO_ACCOUNT_SUCCESS_MESSAGE="Deposit is been done successfully";
	public final static String DEPOSIT_TO_ACCOUNT_FAIL_MESSAGE="Deposit is NOT been done successfully. Please check your account";
	
	public final static String TRANSFER_TO_ACCOUNT_SUCCESS_MESSAGE="Your transaction is been done successfully. Thanks";
	public final static String TRANSFER_TO_ACCOUNT_FAIL_MESSAGE="Your transaction is Not been done successfully.  Please check your account";
	public final static String INSUFFICIENT_BALANCE="You have insufficient balance. Please check your account. Your transaction failed";
	
	public final static String NO_ACCOUNT_FOUND="Account Not Found";
	public final static String ACCOUNT_FOUND="Account Found";
	
	public final static int ACCOUNT_TYPE_DOLAR=1;
	public final static int ACCOUNT_TYPE_EURO=2;
	
}
