package bankEvents;

import java.util.ArrayList;
import java.util.List;

import eventHandler.CustomerCompleteServiceEventHandler;
import eventSimulation.Event;

public class CustomerCompleteServiceEvent extends Event {
	private List<CustomerCompleteServiceEventHandler> handlers = new ArrayList<CustomerCompleteServiceEventHandler>();

	public CustomerCompleteServiceEvent(long ticks) {
		super(ticks);
	}

	@Override
	public void fire() {
		for(CustomerCompleteServiceEventHandler h : handlers){
			h.handleEvent();
		}
		
	}
	
	public void subscribe(CustomerCompleteServiceEventHandler h){
		handlers.add(h);
	}

}
