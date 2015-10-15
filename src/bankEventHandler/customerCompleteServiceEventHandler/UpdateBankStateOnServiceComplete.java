package bankEventHandler.customerCompleteServiceEventHandler;

import eventHandler.CustomerCompleteServiceEventHandler;
import model.BankState;
import model.BankStatistics;
import model.Customer;

public class UpdateBankStateOnServiceComplete implements CustomerCompleteServiceEventHandler {
	private BankState state;
	private BankStatistics stats;
	private int line;
	
	public UpdateBankStateOnServiceComplete(BankState state, int line, BankStatistics stats) {
		this.state = state;
		this.line = line;
		this.stats = stats;
	}

	@Override
	public void handleEvent(long ticks, Customer c) {
		c.setEndWaitTicks(ticks);
		stats.addCustomerWaitTime(c.getEndWaitTicks() - c.getStartWaitTicks());
		state.setTellerServicing(false, line);
		state.setCurrentTicks(ticks);
		System.out.println("Service was completed in line " + line + " at " + ticks);
		System.out.println(state);
	}

}
