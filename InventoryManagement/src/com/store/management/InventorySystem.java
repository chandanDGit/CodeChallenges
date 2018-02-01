package com.store.management;

import java.util.Scanner;

import com.store.management.service.TransactionCommand;

public class InventorySystem {

	public static void main(String[] args) {

		CommandFactory commandFactory = new CommandFactory();
		try (Scanner scanLine = new Scanner(System.in)) {
			while (scanLine.hasNextLine()) {
				String line = scanLine.nextLine();
				if (line.contains("stop")) {
					break;
				}
				try {
					TransactionCommand tranCommand = commandFactory.getTransactionCommand(line);
					if (tranCommand != null) {
						tranCommand.validateCommand();
						tranCommand.saveTransaction();
					}
				} catch (Exception e) {
					System.out.println("Exception Occurred. Aborting.");
					e.printStackTrace();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
