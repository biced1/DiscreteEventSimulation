package bankEvents;

import java.util.ArrayList;
import java.util.List;

import eventHandler.CustomerLeavesServicedEventHandler;
import eventSimulation.Event;

public class CustomerLeavesServicedEvent extends Event {
	private List<CustomerLeavesServicedEventHandler> handlers = new ArrayList<CustomerLeavesServicedEventHandler>();

	public CustomerLeavesServicedEvent(long ticks) {
		super(ticks);
	}

	@Override
	public void fire() {
		for(CustomerLeavesServicedEventHandler h : handlers){
			h.handleEvent();
		}
		
	}
	
	public void subscribe(CustomerLeavesServicedEventHandler h){
		handlers.add(h);
	}

}
