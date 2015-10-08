package model;

public class BankState {
	private int customersWaiting;
	private final int TELLERS;
	private final int LINES;
	private int[] customersPerLine;
	private long currentTicks;
	
	public BankState(int tellers, int lines){
		this.customersWaiting = 0;
		this.TELLERS = tellers;
		this.LINES = lines;
		customersPerLine = new int[lines];
		currentTicks = 0;
	}
	
	public void setCurrentTicks(long ticks){
		this.currentTicks = ticks;
	}
	
	public long getCurrentTicks(){
		return currentTicks;
	}
	
	public void addCustomerToLine(int lineNumber){
		if(lineNumber <= customersPerLine.length){
			customersPerLine[lineNumber] += 1;
		}
	}
	
	public void removeCustomerFromLine(int lineNumber){
		if(lineNumber <= customersPerLine.length){
			customersPerLine[lineNumber] -= 1;
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
