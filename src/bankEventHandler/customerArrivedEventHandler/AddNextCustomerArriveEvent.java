package bankEventHandler.customerArrivedEventHandler;

import java.util.Random;

import model.BankState;
import model.Customer;
import bankEvents.CustomerArrivedEvent;
import eventHandler.CustomerArrivedEventHandler;
import eventSimulation.Events;

public class AddNextCustomerArriveEvent implements CustomerArrivedEventHandler{

	private Events events;
	private BankState state;
	public AddNextCustomerArriveEvent(Events events, BankState state) {
		this.events = events;
		this.state = state;
	}
	
	@Override
	public void handleEvent(long ticks, Customer c) {
		long nextEventTicks = ticks + getRandomWaitTicks(500, 5000);
		CustomerArrivedEvent e = new CustomerArrivedEvent(nextEventTicks, new Customer(state.getCurrentCustomerID()));
		state.incrementCustomerID();
		e.subscribe(new UpdateBankStateOnArrival(state, events));
		e.subscribe(new AddNextCustomerArriveEvent(events, state));
		events.addEvent(e);
		System.out.println("Added customer arrived event at " + nextEventTicks);
	}
	
	private int getRandomWaitTicks(int minimum, int maximum){
		Random rand = new Random();
		int randomTicks = rand.nextInt(maximum) + minimum;
		return randomTicks;
	}

}
