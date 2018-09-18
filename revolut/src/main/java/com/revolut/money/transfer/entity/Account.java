package com.revolut.money.transfer.entity;


import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * 
 * @author bsezgun
 * @comment
 *  This class is in-memory <b>Account</b> H2 Database table.<br/> AccountId column is automatically increased if it is not set.
 * 	@since
 *  18.09.2018
 *  @version
 *  v.1.0.1
 */
@Entity
@Table(name = "account")
public class Account implements Comparable<Account>{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal accountId;
	
	@Column
    private String accountName;
	
	@Column
    private String userId;
    
	@Column
    private BigDecimal amount;

	@Transient
	private List<AccountDetail> accountDetails; 
	
	public List<AccountDetail> getAccountDetails() {
		return accountDetails;
	}

	public void setAccountDetails(List<AccountDetail> accountDetails) {
		this.accountDetails = accountDetails;
	}

	public BigDecimal getAccountId() {
		return accountId;
	}

	public void setAccountId(BigDecimal accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}



	@Override
	public String toString() {
		 return "Account Id:"+this.accountId+",  Amount:"+this.amount;
	}

	@Override
	public int compareTo(Account acc) {
		return this.getAccountId().compareTo(acc.getAccountId());
	}
	    
	
	
    
    
}
