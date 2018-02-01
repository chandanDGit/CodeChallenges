package com.store.management;

import com.store.management.service.TransactionCommand;
import com.store.management.serviceimpl.CreateCommand;
import com.store.management.serviceimpl.DeleteCommand;
import com.store.management.serviceimpl.ReportCommand;
import com.store.management.serviceimpl.UpdateBuyCommand;
import com.store.management.serviceimpl.UpdateSellCommand;
import com.store.management.serviceimpl.UpdateSellPriceCommand;
import com.store.management.utility.Constants;

/**
 * Each Command initialize it's respective object
 *
 */
public class CommandFactory {

	
	public TransactionCommand getTransactionCommand(String command) {
		if (command == null || command.trim().equals(Constants._blank)) {
			return null;
		} else {
			String[] commandArr = command.split(Constants._space);
			if (commandArr[0].equals(Constants._create)) {
				return new CreateCommand(commandArr);
			} else if (commandArr[0].equals(Constants._updateBuy)) {
				return new UpdateBuyCommand(commandArr);
			} else if (commandArr[0].equals(Constants._updateSell)) {
				return new UpdateSellCommand(commandArr);
			} else if (commandArr[0].equals(Constants._delete)) {
				return new DeleteCommand(commandArr);
			} else if (commandArr[0].equals(Constants._report)) {
				return new ReportCommand(commandArr);
			} else if (commandArr[0].equals(Constants._updateSellPrice)) {
				return new UpdateSellPriceCommand(commandArr);
			} else {
				return null;
			}
		}

	}

}
