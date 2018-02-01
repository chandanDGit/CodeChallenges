package com.store.management.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

import com.store.management.model.Product;
import com.store.management.model.Transaction;
import com.store.management.repository.StoreInventory;
import com.store.management.service.TransactionCommand;
import com.store.management.utility.Constants;
import com.store.management.utility.StoreException;
import com.store.management.utility.Utility;

public class ReportCommand implements TransactionCommand {

	private int commandParameter = 1;

	private String[] commandArr;

	Transaction transaction;

	public ReportCommand(String[] commandArr) {
		this.commandArr = commandArr;
	}

	@Override
	public void validateCommand() throws StoreException {
		if (getCommandArr().length != commandParameter) {
			throw new StoreException("Invalid parameters for Command:" + getCommandArr()[0]);
		}
		Transaction transaction = new Transaction();
		transaction.setTransactionTime(new Date());
		transaction.setTransactionType(Constants._report);
		setTransaction(transaction);
	}

	@Override
	public void saveTransaction() throws StoreException {
		final Map<String, Product> inventory = StoreInventory.getStoreInventory().getInventory();
		List<String> productKeys = inventory.keySet().stream().sorted().collect(Collectors.toList());

		Double totalValue = 0.0;

		System.out.println("          INVENTORY REPORT");
		System.out.println("Item Name    Bought At     Sold At       AvailableQty       Value");
		System.out.println("---------    ---------     -------       -------------      -------");

		for (String productName : productKeys) {
			Product product = inventory.get(productName);
			Double productValue = product.getAvailableQuantity() * product.getBuyingPrice();
			totalValue += productValue;
			System.out.println(product + "" + productValue);
		}
		System.out.println("--------------------------------------------------------------------");
		System.out.println("Total Value                                           "
				+ String.format("%.2f", Utility.convertToTwoDecimal(totalValue)));

		final Stack<Transaction> transactionList = StoreInventory.getStoreInventory().getTransactionList();
		Double profitSinceLastReport = 0.0;

		while (!transactionList.isEmpty()) {
			Transaction tr = transactionList.pop();
			if (tr == null || tr.getTransactionType().equals(Constants._report)) {
				break;
			} else if (tr.getTransactionType().equals(Constants._updateSell)
					|| tr.getTransactionType().equals(Constants._delete)) {
				profitSinceLastReport += tr.getProfitorLossAmount();
			}
		}

		System.out.print("Profit since previous report                          ");
		System.out.println(String.format("%.2f", Utility.convertToTwoDecimal(profitSinceLastReport)));
		StoreInventory.getStoreInventory().addTransaction(transaction);
	}

	public String[] getCommandArr() {
		return commandArr;
	}

	public void setCommandArr(String[] commandArr) {
		this.commandArr = commandArr;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

}
