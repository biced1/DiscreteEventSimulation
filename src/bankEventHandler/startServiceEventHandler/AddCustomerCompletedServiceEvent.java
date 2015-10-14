package bankEventHandler.startServiceEventHandler;

import java.util.Random;

import bankEventHandler.customerCompleteServiceEventHandler.AddStartServiceEvent;
import bankEventHandler.customerCompleteServiceEventHandler.UpdateBankStateOnServiceComplete;
import bankEvents.CustomerCompleteServiceEvent;
import eventHandler.CustomerStartServiceEventHandler;
import eventSimulation.Events;
import model.BankState;
import model.Customer;

public class AddCustomerCompletedServiceEvent implements CustomerStartServiceEventHandler{
	
	private int line;
	private Events events;
	private BankState state;
	
	public AddCustomerCompletedServiceEvent(BankState state, Events events, int line) {
		this.line = line;
		this.events = events;
		this.state = state;
	}
	
	@Override
	public void handleEvent(long ticks, Customer c) {
		long nextTicks = ticks + getRandomWaitTicks(5000, 10000);
		CustomerCompleteServiceEvent e = new CustomerCompleteServiceEvent(nextTicks, c);
		e.subscribe(new UpdateBankStateOnServiceComplete(state, line));
		e.subscribe(new AddStartServiceEvent(events, line, state));
		events.addEvent(e);
		System.out.println("added complete service for " + c.ID + " at " + nextTicks);
		System.out.println(state);
		
	}
	
	private int getRandomWaitTicks(int minimum, int maximum){
		Random rand = new Random();
		int randomTicks = rand.nextInt(maximum) + minimum;
		return randomTicks;
	}

}
