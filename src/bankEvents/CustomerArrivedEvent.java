package bankEvents;

import java.util.List;
import java.util.ArrayList;

import eventHandler.CustomerArrivedEventHandler;
import eventSimulation.Event;

public class CustomerArrivedEvent extends Event {
	private List<CustomerArrivedEventHandler> handlers = new ArrayList<CustomerArrivedEventHandler>();

	public CustomerArrivedEvent(long ticks) {
		super(ticks);
	}

	@Override
	public void fire() {
		for (CustomerArrivedEventHandler h : handlers) {
			h.handleEvent();
		}

	}

	public void subscribe(CustomerArrivedEventHandler h) {
		handlers.add(h);
	}
}
