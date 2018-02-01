package com.store.management.serviceimpl;

import com.store.management.model.Product;
import com.store.management.repository.StoreInventory;
import com.store.management.service.TransactionCommand;
import com.store.management.utility.StoreException;
import com.store.management.utility.Utility;

public class CreateCommand implements TransactionCommand {

	private int commandParameter = 4;

	private Product product;

	private String[] commandArr;

	public CreateCommand(String[] commandArr) {
		this.commandArr = commandArr;
	}

	@Override
	public void validateCommand() throws StoreException {
		if (getCommandArr().length != commandParameter) {
			throw new StoreException("Invalid parameters for Command:" + getCommandArr()[0]);
		}
		Product product = new Product();
		product.setProductName(getCommandArr()[1]);
		product.setBuyingPrice(Utility.convertToTwoDecimal(getCommandArr()[2]));
		product.setSellingPrice(Utility.convertToTwoDecimal(getCommandArr()[3]));
		product.setAvailableQuantity(0);
		setProduct(product);

	}

	@Override
	public void saveTransaction() throws StoreException {
		StoreInventory.getStoreInventory().addInventory(product);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String[] getCommandArr() {
		return commandArr;
	}

	public void setCommandArr(String[] commandArr) {
		this.commandArr = commandArr;
	}

}
