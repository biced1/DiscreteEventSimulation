package bankEvents;

import java.util.ArrayList;
import java.util.List;

import eventHandler.CustomerArrivedEventHandler;
import eventSimulation.Event;
import model.Customer;

public class CustomerArrivedEvent extends Event {
	private List<CustomerArrivedEventHandler> handlers = new ArrayList<CustomerArrivedEventHandler>();

	public CustomerArrivedEvent(long ticks, Customer c) {
		super(ticks, c);
	}

	@Override
	public void fire() {
		for (CustomerArrivedEventHandler h : handlers) {
			h.handleEvent(ticks, c);
		}

	}

	public void subscribe(CustomerArrivedEventHandler h) {
		handlers.add(h);
	}
}
