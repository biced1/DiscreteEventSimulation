package model;

import java.util.ArrayList;
import java.util.List;

public class BankState {
	private int customersWaiting;
	private final int TELLERS;
	private final int LINES;
	private long currentTicks;
	private List<ArrayList<Customer>> customersInLines = new ArrayList<ArrayList<Customer>>();
	
	public BankState(int tellers, int lines){
		this.customersWaiting = 0;
		this.TELLERS = tellers;
		this.LINES = lines;
		currentTicks = 0;
		populateCustomersInLines();
	}
	
	private void populateCustomersInLines(){
		for(int x = 0; x < LINES; x ++){
			customersInLines.add(x, new ArrayList<Customer>());
		}
	}
	public void setCurrentTicks(long ticks){
		this.currentTicks = ticks;
	}
	
	public long getCurrentTicks(){
		return currentTicks;
	}
	
	public Customer getNextCustomer(int lineNumber){
		Customer c = null;
		if(lineNumber <= LINES){
			ArrayList<Customer> line = customersInLines.get(lineNumber);
			if(!line.isEmpty()){
				c = line.get(lineNumber);
			}
		}
		return c;
	}
	
	public void addCustomerToLine(int lineNumber, Customer c){
		if(lineNumber <= LINES){
			customersInLines.get(lineNumber).add(c);
		}
	}
	
	public void removeCustomerFromLine(int lineNumber, Customer c){
		if(lineNumber <= LINES){
			customersInLines.get(lineNumber).remove(c);
		}
	}
	
	public int getCustomersWaiting(){
		return customersWaiting;
	}
	
	public int getTellers(){
		return TELLERS;
	}
	
	public int getLines(){
		return LINES;
	}
}
