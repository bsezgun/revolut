package com.revolut.money.transfer.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author bsezgun
 *  Entity
 * 
 *  This class is in-memory <b>AccountDetailEuro</b> H2 Database table.. accountDetailId column is automatically increased if it is not set.
 *  .accountId is the foreign key of AccountEuro table.
 * 	@since 2018-09-19
 *  @version v.1.0.1
 */
@Entity
@Table(name = "account_detail_euro")
public class AccountDetailEuro implements Comparable<AccountDetailEuro> {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal accountDetailId;
	
	@Column
    private BigDecimal accountId;
	
	@Column
    private BigDecimal withDraw;

	@Column
    private BigDecimal deposit;

	@Column
    private Date changeDate;

	
	public BigDecimal getAccountDetailId() {
		return accountDetailId;
	}

	public void setAccountDetailId(BigDecimal accountDetailId) {
		this.accountDetailId = accountDetailId;
	}

	public BigDecimal getAccountId() {
		return accountId;
	}

	public void setAccountId(BigDecimal accountId) {
		this.accountId = accountId;
	}

	

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public BigDecimal getWithDraw() {
		return withDraw;
	}

	public void setWithDraw(BigDecimal withDraw) {
		this.withDraw = withDraw;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}
	
	@Override
	public String toString() {
		 return " Account Id:"+this.accountId
				 	+", 	Account Detail Id:"+this.accountDetailId
				 	+", 	WithDraw:"+this.withDraw
				 	+", 	Deposit:"+this.deposit
				 	+", 	Change Date:"+this.changeDate
				 	;
	}

	@Override
	public int compareTo(AccountDetailEuro acd) {
		return this.getAccountId().compareTo(acd.getAccountId());
	}

}
