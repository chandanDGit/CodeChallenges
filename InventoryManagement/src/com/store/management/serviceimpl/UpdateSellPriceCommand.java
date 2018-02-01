package com.store.management.serviceimpl;

import java.util.Date;

import com.store.management.model.Product;
import com.store.management.model.Transaction;
import com.store.management.repository.StoreInventory;
import com.store.management.service.TransactionCommand;
import com.store.management.utility.Constants;
import com.store.management.utility.StoreException;
import com.store.management.utility.Utility;

public class UpdateSellPriceCommand implements TransactionCommand {

	private int commandParameter = 3;

	private String[] commandArr;

	private Product product;

	Transaction transaction;

	public UpdateSellPriceCommand(String[] commandArr) {
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
		transaction.setTransactionType(Constants._updateSellPrice);
		setTransaction(transaction);

		Product product = new Product();
		product.setProductName(getCommandArr()[1]);
		product.setSellingPrice(Utility.convertToTwoDecimal(getCommandArr()[2]));
		setProduct(product);
	}

	@Override
	public void saveTransaction() throws StoreException {
		StoreInventory storeInventory = StoreInventory.getStoreInventory();
		Product existingProduct = storeInventory.getProduct(product.getProductName());
		if (existingProduct == null) {
			throw new StoreException("Item not present in the Inventory.");
		}
		existingProduct.setSellingPrice(product.getSellingPrice());
		storeInventory.updateInventory(existingProduct);
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
