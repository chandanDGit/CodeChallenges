package com.store.management.serviceimpl;

import java.util.Date;

import com.store.management.model.Product;
import com.store.management.model.Transaction;
import com.store.management.repository.StoreInventory;
import com.store.management.service.TransactionCommand;
import com.store.management.utility.Constants;
import com.store.management.utility.StoreException;
import com.store.management.utility.Utility;

public class DeleteCommand implements TransactionCommand {

	private int commandParameter = 2;

	private String[] commandArr;

	Transaction transaction;

	public DeleteCommand(String[] commandArr) {
		this.commandArr = commandArr;
	}

	@Override
	public void validateCommand() throws StoreException {
		if (getCommandArr().length != commandParameter) {
			throw new StoreException("Invalid parameters for Command:" + getCommandArr()[0]);
		}
		Transaction transaction = new Transaction();
		transaction.setProductName(getCommandArr()[1]);
		transaction.setTransactionTime(new Date());
		transaction.setTransactionType(Constants._delete);
		setTransaction(transaction);
	}

	@Override
	public void saveTransaction() throws StoreException {
		StoreInventory storeInventory = StoreInventory.getStoreInventory();
		Product product = storeInventory.getProduct(transaction.getProductName());
		if (product == null) {
			throw new StoreException("Item not present in the Inventory.");
		}
		transaction.setTotalTransactionValue(
				-Utility.convertToTwoDecimal((product.getAvailableQuantity() * product.getBuyingPrice())));
		transaction.setProfitorLossAmount(transaction.getTotalTransactionValue());

		storeInventory.removeProductFromInventory(product);
		storeInventory.addTransaction(transaction);
	}

	public int getCommandParameter() {
		return commandParameter;
	}

	public void setCommandParameter(int commandParameter) {
		this.commandParameter = commandParameter;
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
