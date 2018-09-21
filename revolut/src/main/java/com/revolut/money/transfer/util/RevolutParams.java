package com.revolut.money.transfer.util;
/**
 * 
 * @author bsezgun
 * @version v.1.0.1
 *  Static Variables
 * @since   2018-09-19
 *  This object is used for the final static variables. 
 */
public class RevolutParams {

	public final static String RESULT_STATU_SUCCESS="SUCCESS";
	
	/**
	 * {@value RevolutParams#RESULT_STATU_FAIL}
	 */
	public final static String RESULT_STATU_FAIL="FAIL";
	public final static String RESULT_STATU_RESTRICTED="RESTRICTED";
	
	
	public final static String DEPOSIT_TO_ACCOUNT_SUCCESS_MESSAGE="Deposit is been done successfully";
	public final static String DEPOSIT_TO_ACCOUNT_FAIL_MESSAGE="Deposit is NOT been done successfully. Please check your account";
	
	public final static String TRANSFER_TO_ACCOUNT_SUCCESS_MESSAGE="Your transaction is been done successfully. Thanks";
	public final static String TRANSFER_TO_ACCOUNT_FAIL_MESSAGE="Your transaction is Not been done successfully.  Please check your account";
	public final static String INSUFFICIENT_BALANCE="You have insufficient balance. Please check your account. Your transaction failed";
	public final static String TRANSFER_RESTRICTED="The transfer is restricted by rules. Your transaction failed";
	
	
	
	
	public final static String NO_ACCOUNT_FOUND="Account Not Found";
	public final static String ACCOUNT_FOUND="Account Found";
	
	/**
	 * {@value RevolutParams#ACCOUNT_TYPE_DOLAR}
	 */
	public final static int ACCOUNT_TYPE_DOLAR=1;
	/**
	 * {@value RevolutParams#ACCOUNT_TYPE_EURO}
	 */
	public final static int ACCOUNT_TYPE_EURO=2;
	
}
