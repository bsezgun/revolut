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
 *  This class is in-memory <b>AccountEuro</b> H2 Database table.<br/> accountId column is automatically increased if it is not set.
 * 	@since 2018-09-19
 *  @version v.1.0.1
 */
@Entity
@Table(name = "account_euro")
public class AccountEuro extends Account implements Comparable<AccountEuro>{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal accountId;
	
	@Column
    private final String accountName="EURO ACCOUNT";
   
	@Column
    private BigDecimal accountType;
    
	@Column
    private String currency;
    
	@Column
    private BigDecimal amount;

	@Transient
	private List<AccountDetailEuro> accountDetails; 
	
	public BigDecimal getAccountType() {
		return accountType;
	}

	public void setAccountType(BigDecimal accountType) {
		this.accountType = accountType;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public List<AccountDetailEuro> getAccountDetails() {
		return accountDetails;
	}

	public void setAccountDetails(List<AccountDetailEuro> accountDetails) {
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		 return "Account Id:"+this.accountId+",  Amount:"+this.amount+",  ACCOUNT NAME: "+this.accountName+", ACCOUNT TYPE:"+this.accountType+", CURRENCY:"+this.currency;
	}

	@Override
	public int compareTo(AccountEuro acc) {
		return this.getAccountId().compareTo(acc.getAccountId());
	}

}
