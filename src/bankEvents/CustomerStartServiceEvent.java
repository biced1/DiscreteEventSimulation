package bankEvents;

import java.util.ArrayList;
import java.util.List;

import eventHandler.CustomerStartServiceEventHandler;
import eventSimulation.Event;
import model.Customer;

public class CustomerStartServiceEvent extends Event {
	private List<CustomerStartServiceEventHandler> handlers = new ArrayList<CustomerStartServiceEventHandler>();

	public CustomerStartServiceEvent(long ticks, Customer c) {
		super(ticks, c);
	}

	@Override
	public void fire() {
		for(CustomerStartServiceEventHandler h : handlers){
			h.handleEvent(ticks, c);
		}
		
	}
	
	public void subscribe(CustomerStartServiceEventHandler h){
		handlers.add(h);
	}

}
