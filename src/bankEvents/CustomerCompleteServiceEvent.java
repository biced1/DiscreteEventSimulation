package bankEvents;

import java.util.ArrayList;
import java.util.List;

import eventHandler.CustomerCompleteServiceEventHandler;
import eventSimulation.Event;
import model.Customer;

public class CustomerCompleteServiceEvent extends Event {
	private List<CustomerCompleteServiceEventHandler> handlers = new ArrayList<CustomerCompleteServiceEventHandler>();

	public CustomerCompleteServiceEvent(long ticks, Customer c) {
		super(ticks, c);
	}

	@Override
	public void fire() {
		for(CustomerCompleteServiceEventHandler h : handlers){
			h.handleEvent(ticks, c);
		}
		
	}
	
	public void subscribe(CustomerCompleteServiceEventHandler h){
		handlers.add(h);
	}

}
