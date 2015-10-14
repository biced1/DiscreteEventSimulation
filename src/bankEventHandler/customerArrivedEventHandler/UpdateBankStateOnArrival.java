package bankEventHandler.customerArrivedEventHandler;

import java.util.List;

import bankEventHandler.startServiceEventHandler.AddCustomerCompletedServiceEvent;
import bankEventHandler.startServiceEventHandler.UpdateBankStateOnServiceStart;
import bankEvents.CustomerStartServiceEvent;
import eventHandler.CustomerArrivedEventHandler;
import eventSimulation.Events;
import model.BankState;
import model.Customer;

public class UpdateBankStateOnArrival implements CustomerArrivedEventHandler {
	private BankState state;
	private Events events;

	public UpdateBankStateOnArrival(BankState state, Events events) {
		this.state = state;
		this.events = events;
	}

	@Override
	public void handleEvent(long ticks, Customer c) {
		List<Integer> shortestLines = state.getShortestLines();
		int shortestLine = shortestLines.get(0);
		for(int x = 0; x < shortestLines.size(); x++){
			if(!state.getTellerServicing(shortestLines.get(x))){
				shortestLine = shortestLines.get(x);
			}
		}
		long lineLength = state.getLineLength(shortestLine);
		if (lineLength == 0 && !state.getTellerServicing(shortestLine)) {
			shortestLine = state.getOpenTeller();
			CustomerStartServiceEvent e = new CustomerStartServiceEvent(ticks, c);
			e.subscribe(new UpdateBankStateOnServiceStart(state, shortestLine));
			e.subscribe(new AddCustomerCompletedServiceEvent(state, events, shortestLine));
			events.addEvent(e);
			System.out.println("Customer " + c.ID + "  started servicing at " + shortestLine);
		} else {
			state.addCustomerToLine(shortestLine, c);
			System.out.println("Customer " + c.ID + " was added to line " + shortestLine);
		}
		state.setCurrentTicks(ticks);

		System.out.println(state.getCurrentTicks());
		System.out.println(state);
	}

}
