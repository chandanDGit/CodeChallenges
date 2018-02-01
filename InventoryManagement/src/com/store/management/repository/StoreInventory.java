package com.store.management.repository;

import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

import com.store.management.model.Product;
import com.store.management.model.Transaction;
import com.store.management.utility.StoreException;

public class StoreInventory {
	
	static StoreInventory storeInventory;
	
	private Map<String,Product> inventory;
	
	// can be list and iterated in reverse order
	// If list then all the transaction can be kept in memory, however not required for this problems
	private Stack<Transaction> transactionList;
	
	private StoreInventory() {
		this.inventory = new ConcurrentHashMap<String, Product>();
		this.transactionList = new Stack<Transaction>();
	}

	public Map<String, Product> getInventory() {
		return inventory;
	}
	
	public Product getProduct(String productName) {
		return getInventory().get(productName);
	}
	

	public static StoreInventory getStoreInventory() {
		if(storeInventory == null ) {
			storeInventory = new StoreInventory();
		}
		return storeInventory;
	}
	
	public void addInventory(Product product) throws StoreException {
		if(getProduct(product.getProductName()) == null ) {
			this.inventory.put(product.getProductName(), product);
		} else {
			throw new StoreException("Product already present in inventory.");
		}
	}

	public void updateInventory(Product product) {
		inventory.put(product.getProductName(), product);
	}

	public void addTransaction(Transaction transaction) {
		transactionList.push(transaction);
	}

	public Stack<Transaction> getTransactionList() {
		return transactionList;
	}

	public void removeProductFromInventory(Product product) {
		inventory.remove(product.getProductName());
	}

	
}
