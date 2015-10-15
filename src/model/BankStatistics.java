package model;

public class BankStatistics {
	private long totalCustomers;
	private long averageWaitTicks = 0;
	private long totalWaitTicks;
	
	public void addCustomerWaitTime(long ticks){
		totalCustomers++;
		totalWaitTicks += ticks;
		averageWaitTicks = totalWaitTicks / totalCustomers;
	}
	
	public long getAverageWaitTicks(){
		return averageWaitTicks;
	}
}
