package bankEventHandler.customerArrivedEventHandler;

import java.util.Random;

import bankEvents.CustomerArrivedEvent;
import eventHandler.CustomerArrivedEventHandler;
import eventSimulation.Events;
import model.BankState;
import model.BankStatistics;
import model.Customer;

public class AddNextCustomerArriveEvent implements CustomerArrivedEventHandler{

	private Events events;
	private BankState state;
	private BankStatistics stats;
	
	public AddNextCustomerArriveEvent(Events events, BankState state, BankStatistics stats) {
		this.events = events;
		this.state = state;
		this.stats = stats;
	}
	
	@Override
	public void handleEvent(long ticks, Customer c) {
		long nextEventTicks = ticks + getRandomWaitTicks(500, 500);
		CustomerArrivedEvent e = new CustomerArrivedEvent(nextEventTicks, new Customer(state.getCurrentCustomerID()));
		state.incrementCustomerID();
		e.subscribe(new UpdateBankStateOnArrival(state, events, stats));
		e.subscribe(new AddNextCustomerArriveEvent(events, state, stats));
		events.addEvent(e);
		System.out.println("Added customer arrived event at " + nextEventTicks);
		System.out.println(state);
	}
	
	private int getRandomWaitTicks(int minimum, int maximum){
		Random rand = new Random();
		int randomTicks = rand.nextInt(maximum) + minimum;
		return randomTicks;
	}

}
