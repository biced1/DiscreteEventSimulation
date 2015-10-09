package eventHandler;

import model.Customer;

public interface EventHandler {
	public void handleEvent(long ticks, Customer c);
}
