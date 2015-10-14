package bankEventHandler.customerCompleteServiceEventHandler;

import eventHandler.CustomerCompleteServiceEventHandler;
import model.BankState;
import model.Customer;

public class UpdateBankStateOnServiceComplete implements CustomerCompleteServiceEventHandler {
	private BankState state;
	private int line;
	
	public UpdateBankStateOnServiceComplete(BankState state, int line) {
		this.state = state;
		this.line = line;
	}

	@Override
	public void handleEvent(long ticks, Customer c) {
		state.setTellerServicing(false, line);
		state.setCurrentTicks(ticks);
		System.out.println("Service was completed in line " + line + " at " + ticks);
	}

}
