package com.store.management.model;

import java.util.Date;

import com.store.management.utility.Constants;

public class Transaction {

	String transactionType;
	String productName;
	long quantity;
	Double totalTransactionValue;
	Double profitorLossAmount;
	Date transactionTime;

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public Double getTotalTransactionValue() {
		return totalTransactionValue;
	}

	public void setTotalTransactionValue(Double totalTransactionValue) {
		this.totalTransactionValue = totalTransactionValue;
	}

	public Double getProfitorLossAmount() {
		return profitorLossAmount;
	}

	public void setProfitorLossAmount(Double profitorLossAmount) {
		this.profitorLossAmount = profitorLossAmount;
	}

	public Date getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getTransactionType()).append(Constants._tab).append(getTransactionTime()).append(Constants._tab)
				.append(getProfitorLossAmount());
		return sb.toString();
	}

}
