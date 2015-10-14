package bankEventHandler.startServiceEventHandler;

import eventHandler.CustomerStartServiceEventHandler;
import model.BankState;
import model.Customer;

public class UpdateBankStateOnServiceStart implements CustomerStartServiceEventHandler{
	
	private BankState state;
	private int line;
	public UpdateBankStateOnServiceStart(BankState state, int line){
		this.state = state;
		this.line = line;
	}
	
	@Override
	public void handleEvent(long ticks, Customer c) {
		state.removeFirstCustomerFromLine(line);
		state.setTellerServicing(true, line);
		state.setCurrentTicks(ticks);
		System.out.println("Removed first customer from line " + line);
		System.out.println(state);
	}

}
