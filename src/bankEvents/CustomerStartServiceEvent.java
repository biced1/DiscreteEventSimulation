package bankEvents;

import java.util.ArrayList;
import java.util.List;

import eventHandler.CustomerStartServiceEventHandler;
import eventSimulation.Event;

public class CustomerStartServiceEvent extends Event {
	private List<CustomerStartServiceEventHandler> handlers = new ArrayList<CustomerStartServiceEventHandler>();

	public CustomerStartServiceEvent(long ticks) {
		super(ticks);
	}

	@Override
	public void fire() {
		for(CustomerStartServiceEventHandler h : handlers){
			h.handleEvent();
		}
		
	}
	
	public void subscribe(CustomerStartServiceEventHandler h){
		handlers.add(h);
	}

}
