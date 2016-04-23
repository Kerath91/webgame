package com.mj.webapp.model;

import java.math.BigDecimal;
import java.util.Date;

public class SystemContract {

	private int		 	contractId;
	private boolean 	active;
	private BigDecimal 	amount;
	private String 		amountPeriod;
	private String 		amountType;
	private BigDecimal 	authorizationPercent;
	private Date 		fromDate;
	private String 		orderNumber;
	private String		request;
	private Date 		toDate;
	private long 		systemId;

	public SystemContract(){}
	
	public SystemContract(
			int			contractId,
			boolean		active,
			BigDecimal	amount,
			String 		amountPeriod,
			String 		amountType,
			BigDecimal 	authorizationPercent,
			Date 		fromDate,
			String 		orderNumber,
			String		request,
			Date 		toDate,
			long 		systemId)
	{
		this.setContractId(contractId);
		this.setActive(active);
		this.setAmount(amount);
		this.setAmountPeriod(amountPeriod);
		this.setAmountType(amountType);
		this.setAuthorizationPercent(authorizationPercent);
		this.setFromDate(fromDate);
		this.setOrderNumber(orderNumber);
		this.setRequest(request);
		this.setToDate(toDate);
		this.setSystemId(systemId);
	}

	public int getContractId() {
		return contractId;
	}

	public void setContractId(int id) {
		this.contractId = id;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getAmountPeriod() {
		return amountPeriod;
	}

	public void setAmountPeriod(String amountPeriod) {
		this.amountPeriod = amountPeriod;
	}

	public String getAmountType() {
		return amountType;
	}

	public void setAmountType(String amountType) {
		this.amountType = amountType;
	}

	public BigDecimal getAuthorizationPercent() {
		return authorizationPercent;
	}

	public void setAuthorizationPercent(BigDecimal authorizationPercent) {
		this.authorizationPercent = authorizationPercent;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public long getSystemId() {
		return systemId;
	}

	public void setSystemId(long systemId) {
		this.systemId = systemId;
	}
	
	
}
