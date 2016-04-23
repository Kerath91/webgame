package com.mj.webapp.view;

import java.math.BigDecimal;
import java.util.Date;

public class SystemContractView {

	private boolean 	active;
	private BigDecimal 	amount;
	private String 		amountPeriod;
	private String 		amountType;
	private BigDecimal 	authorizationPercent;
	private Date 		fromDate;
	private String 		orderNumber;
	private String		request;
	private Date 		toDate;
	private String 		systemName;

	public SystemContractView(){}
	
	public SystemContractView(
			boolean		active,
			BigDecimal	amount,
			String 		amountPeriod,
			String 		amountType,
			BigDecimal 	authorizationPercent,
			Date 		fromDate,
			String 		orderNumber,
			String		request,
			Date 		toDate,
			String 		systemName)
	{
		this.setActive(active);
		this.setAmount(amount);
		this.setAmountPeriod(amountPeriod);
		this.setAmountType(amountType);
		this.setAuthorizationPercent(authorizationPercent);
		this.setFromDate(fromDate);
		this.setOrderNumber(orderNumber);
		this.setRequest(request);
		this.setToDate(toDate);
		this.setSystemName(systemName);
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

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	
    @Override
    public String toString()
    {
    	String returnString = "SystemContract [systemName = " + systemName + ", ";
    	returnString += "request = " + request + ", ";
    	returnString += "orderNumber = " + orderNumber + ", ";
    	returnString += "fromDate = " + fromDate + ", ";
    	returnString += "toDate = " + toDate + ", ";
    	returnString += "amount = " + amount + ", ";
    	returnString += "amountType = " + amountType + ", ";
    	returnString += "amountPeriod = " + amountPeriod + ", ";
    	returnString += "authorizationPercent = " + authorizationPercent + ", ";
    	returnString += "active = " + active + "]";
    	
        return returnString;
    }
	
}
