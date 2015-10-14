package model;

import java.util.ArrayList;
import java.util.List;

public class BankState {
	private int customersWaiting;
	private final int TELLERS;
	private final int LINES;
	private long currentTicks;
	private long currentCustomerID;
	private List<ArrayList<Customer>> customersInLines = new ArrayList<ArrayList<Customer>>();
	private List<Teller> tellers = new ArrayList<Teller>();

	public BankState(int tellers, int lines) {
		this.customersWaiting = 0;
		this.TELLERS = tellers;
		this.LINES = lines;
		currentTicks = 0;
		this.currentCustomerID = 1;
		populateCustomersInLines();
		populateTellers();
	}

	public long getCurrentCustomerID() {
		return currentCustomerID;
	}

	private void populateTellers() {
		for (int x = 0; x < TELLERS; x++) {
			tellers.add(new Teller(x));
		}
	}

	public void incrementCustomerID() {
		this.currentCustomerID++;
	}

	public boolean getTellerServicing(int tellerID) {
		boolean isServicing = true;
//		if(TELLERS > 1 && LINES == 1){
//			for(int x = 0; x < TELLERS; x++){
//				if(!tellers.get(x).isServicing()){
//					isServicing = false;
//				}
//			}
//		} else {
			if (tellerID < TELLERS) {
				isServicing = tellers.get(tellerID).isServicing();
			} else {
				throw new IndexOutOfBoundsException();
			}
//		}

		return isServicing;

	}

	public void setTellerServicing(boolean isServicing, int tellerID) {
		if (tellerID < TELLERS) {
			tellers.get(tellerID).setServicing(isServicing);
		}
	}

	private void populateCustomersInLines() {
		for (int x = 0; x < LINES; x++) {
			customersInLines.add(x, new ArrayList<Customer>());
		}
	}

	public void setCurrentTicks(long ticks) {
		this.currentTicks = ticks;
	}

	public long getCurrentTicks() {
		return currentTicks;
	}

	public List<Integer> getShortestLines() {
		List<Integer> shortestLines = new ArrayList<Integer>();
		int shortestLineLength = Integer.MAX_VALUE;
		if (!customersInLines.isEmpty()) {
			shortestLineLength = customersInLines.get(0).size();
		}
		for (int x = 1; x < customersInLines.size(); x++) {
			if (customersInLines.get(x).size() < shortestLineLength) {
				shortestLineLength = customersInLines.get(x).size();
			}
		}
		for (int x = 0; x < customersInLines.size(); x++) {
			if (customersInLines.get(x).size() == shortestLineLength) {
				shortestLines.add(x);
			}
		}
		return shortestLines;
	}

	public Customer getNextCustomer(int lineNumber) {
		Customer c = null;
		if (lineNumber <= LINES) {
			ArrayList<Customer> line = customersInLines.get(lineNumber);
			if (!line.isEmpty()) {
				c = line.get(lineNumber);
			}
		}
		return c;
	}

	public void addCustomerToLine(int lineNumber, Customer c) {
		if (lineNumber <= LINES) {
			customersInLines.get(lineNumber).add(c);
		}
	}

	public void removeCustomerFromLine(int lineNumber, Customer c) {
		if (lineNumber <= LINES) {
			customersInLines.get(lineNumber).remove(c);
		}
	}

	public int getCustomersWaiting() {
		return customersWaiting;
	}

	public int getTellers() {
		return TELLERS;
	}

	public int getLines() {
		return LINES;
	}

	public long getLineLength(int line) {
		long lineLength = 0;
		if(TELLERS > 1 && LINES == 1){
			lineLength = customersInLines.get(0).size();
		} else {
			if (line < LINES) {
				lineLength = customersInLines.get(line).size();
			}
		}
		return lineLength;
	}

	public void removeFirstCustomerFromLine(int line) {
		if(LINES == 1 && customersInLines.get(0).size() > 0){
			customersInLines.get(0).remove(0);
		} else {
			if (line < LINES && customersInLines.get(line).size() > 0) {
				customersInLines.get(line).remove(0);
			}
		}
		
	}
	
	public int getOpenTeller() {
		int openTeller = 0;
		for(int x = 0; x < TELLERS; x++){
			if(!getTellerServicing(x)){
				openTeller = x;
			}
		}
		return openTeller;
	}

	@Override
	public String toString() {
		String newline = System.getProperty("line.separator");
		String lines = "";
		if (TELLERS > 1 && LINES == 1) {
			lines += getTellerServicing(0) + " - Line 0:" ; 
			ArrayList<Customer> currentLine = customersInLines.get(0);
			for (int y = 0; y < currentLine.size(); y++) {
				lines += currentLine.get(y).ID + " ";
			}
			lines += newline;
			for (int x = 1; x < TELLERS; x++) {
				lines += getTellerServicing(x);
				lines += newline;
			}
		} else {
			for (int x = 0; x < LINES; x++) {
				lines += getTellerServicing(x) + " - Line " + x + ": ";
				ArrayList<Customer> currentLine = customersInLines.get(x);
				for (int y = 0; y < currentLine.size(); y++) {
					lines += currentLine.get(y).ID + " ";
				}
				lines += newline;
			}

		}
		return lines;
	}
}
