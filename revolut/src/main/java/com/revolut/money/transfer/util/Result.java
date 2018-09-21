package com.revolut.money.transfer.util;
/**
 * 
 * @author bsezgun
 * @version v.1.0.1
 *  Result 
 * @since   2018-09-19
 *  This object is used for the responses.
 */
public class Result {

	private String resultMessage;
	private String resultStatu;
	/**
	 *  Any type of response object can set to this attribute.
	 */
	private Object resultObj;
	
	public Result() {
	}

	public Result(String resultMessage, String resultStatu, Object resultObj) {
		this.resultMessage = resultMessage;
		this.resultStatu = resultStatu;
		this.resultObj = resultObj;
	}
	
	public String getResultStatu() {
		return resultStatu;
	}
	public void setResultStatu(String resultStatu) {
		this.resultStatu = resultStatu;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	public Object getResultObj() {
		return resultObj;
	}
	public void setResultObj(Object resultObj) {
		this.resultObj = resultObj;
	}
	
	@Override
	public String toString() {
		 return "Result Message:"+this.resultMessage+",    Result Statu :"+this.resultStatu;
	}
}
