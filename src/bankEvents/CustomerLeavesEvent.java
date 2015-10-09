package bankEvents;

import java.util.ArrayList;
import java.util.List;

import eventHandler.CustomerLeavesEventHandler;
import eventSimulation.Event;
import model.Customer;

public class CustomerLeavesEvent extends Event {
	private List<CustomerLeavesEventHandler> handlers = new ArrayList<CustomerLeavesEventHandler>();

	public CustomerLeavesEvent(long ticks, Customer c) {
		super(ticks, c);
	}

	@Override
	public void fire() {
		for(CustomerLeavesEventHandler h : handlers){
			h.handleEvent(ticks, c);
		}
		
	}

	public void subscribe(CustomerLeavesEventHandler h){
		handlers.add(h);
	}
}
