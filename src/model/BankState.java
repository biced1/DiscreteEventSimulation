package model;

import java.util.ArrayList;
import java.util.List;

public class BankState {
	private int customersWaiting;
	private final int TELLERS;
	private final int LINES;
	private long currentTicks;
	private List<ArrayList<Customer>> customersInLines = new ArrayList<ArrayList<Customer>>();

	public BankState(int tellers, int lines) {
		this.customersWaiting = 0;
		this.TELLERS = tellers;
		this.LINES = lines;
		currentTicks = 0;
		populateCustomersInLines();
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

	public int getShortestLine() {
		int shortestLine = 0;
		int shortestLineLength = Integer.MAX_VALUE;
		if (!customersInLines.isEmpty()) {
			shortestLineLength = customersInLines.get(0).size();
		}
		for (int x = 1; x < customersInLines.size(); x++) {
			if(customersInLines.get(x).size() < shortestLineLength){
				shortestLine = x;
			}
		}
		return shortestLine;
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
	
	@Override
	public String toString(){
		String newline = System.getProperty("line.separator");
		String lines = "";
		for (int x = 0; x < LINES; x++) {
			lines += "Line " + x + ": ";
			ArrayList<Customer> currentLine = customersInLines.get(x);
			for(int y = 0; y < currentLine.size(); y++){
				lines += currentLine.get(y).ID + " ";
			}
			lines += newline; 
		}
		return lines;
	}
}
