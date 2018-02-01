package com.store.management.service;

import com.store.management.utility.StoreException;

public interface TransactionCommand {
	
	public void validateCommand() throws StoreException;
	
	public void saveTransaction() throws StoreException;
	

}
