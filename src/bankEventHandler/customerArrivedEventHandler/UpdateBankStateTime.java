package bankEventHandler.customerArrivedEventHandler;

import model.BankState;
import model.Customer;
import eventHandler.CustomerArrivedEventHandler;

public class UpdateBankStateTime implements CustomerArrivedEventHandler {

	private BankState state;

	public UpdateBankStateTime(BankState state) {
		this.state = state;
	}

	@Override
	public void handleEvent(long ticks, Customer c) {
		state.setCurrentTicks(ticks);
	}

}
