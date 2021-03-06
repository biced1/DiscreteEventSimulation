package bankEventHandler.customerCompleteServiceEventHandler;

import bankEventHandler.startServiceEventHandler.AddCustomerCompletedServiceEvent;
import bankEventHandler.startServiceEventHandler.UpdateBankStateOnServiceStart;
import bankEvents.CustomerStartServiceEvent;
import eventHandler.CustomerCompleteServiceEventHandler;
import eventSimulation.Events;
import model.BankState;
import model.BankStatistics;
import model.Customer;

public class AddStartServiceEvent implements CustomerCompleteServiceEventHandler{
	private Events events;
	private int line;
	private BankState state;
	private BankStatistics stats;
	
	public AddStartServiceEvent(Events events, int line, BankState state, BankStatistics stats) {
		this.events = events;
		this.line = line;
		this.state = state;
		this.stats = stats;
	}
	@Override
	public void handleEvent(long ticks, Customer c) {
		long lineLength = state.getLineLength(line);
		if(lineLength > 0){
			CustomerStartServiceEvent e = new CustomerStartServiceEvent(ticks, c);
			e.subscribe(new AddCustomerCompletedServiceEvent(state, events, line, stats));
			e.subscribe(new UpdateBankStateOnServiceStart(state, line));
			events.addEvent(e);
			System.out.println("Added a start service event for " + line + " at " + ticks);
			System.out.println(state);
		}
	}

}
