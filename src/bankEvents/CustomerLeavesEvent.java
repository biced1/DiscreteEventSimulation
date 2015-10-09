package bankEvents;

import java.util.ArrayList;
import java.util.List;

import eventHandler.CustomerLeavesEventHandler;
import eventSimulation.Event;

public class CustomerLeavesEvent extends Event {
	private List<CustomerLeavesEventHandler> handlers = new ArrayList<CustomerLeavesEventHandler>();

	public CustomerLeavesEvent(long ticks) {
		super(ticks);
	}

	@Override
	public void fire() {
		for(CustomerLeavesEventHandler h : handlers){
			h.handleEvent();
		}
		
	}

	public void subscribe(CustomerLeavesEventHandler h){
		handlers.add(h);
	}
}
