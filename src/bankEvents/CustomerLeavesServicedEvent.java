package bankEvents;

import java.util.ArrayList;
import java.util.List;

import eventHandler.CustomerLeavesServicedEventHandler;
import eventSimulation.Event;
import model.Customer;

public class CustomerLeavesServicedEvent extends Event {
	private List<CustomerLeavesServicedEventHandler> handlers = new ArrayList<CustomerLeavesServicedEventHandler>();

	public CustomerLeavesServicedEvent(long ticks, Customer c) {
		super(ticks, c);
	}

	@Override
	public void fire() {
		for(CustomerLeavesServicedEventHandler h : handlers){
			h.handleEvent(ticks, c);
		}
		
	}
	
	public void subscribe(CustomerLeavesServicedEventHandler h){
		handlers.add(h);
	}

}
