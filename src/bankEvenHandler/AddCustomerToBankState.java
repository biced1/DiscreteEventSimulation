package bankEvenHandler;

import eventHandler.CustomerArrivedEventHandler;
import model.BankState;
import model.Customer;

public class AddCustomerToBankState implements CustomerArrivedEventHandler {
	private BankState state;
	public AddCustomerToBankState(BankState state) {
		this.state = state;
	}
	
	@Override
	public void handleEvent(long ticks, Customer c) {
		int shortestLine = state.getShortestLine();
		state.addCustomerToLine(shortestLine, c);
		System.out.println("Customer " + c.ID + " was added to line " + shortestLine);
		System.out.println(state);
	}

}
