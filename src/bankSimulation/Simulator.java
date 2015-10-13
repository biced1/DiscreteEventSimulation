package bankSimulation;

import model.BankState;
import model.Customer;
import bankEventHandler.customerArrivedEventHandler.AddCustomerToBankState;
import bankEventHandler.customerArrivedEventHandler.AddNextCustomerArriveEvent;
import bankEventHandler.customerArrivedEventHandler.UpdateBankStateTime;
import bankEvents.CustomerArrivedEvent;
import eventSimulation.Events;

public class Simulator {
	private BankState state;
	private int tellers = 1;
	private int lines = 3;
	private Events events = new Events();
	
	public Simulator(){
		state = new BankState(tellers, lines);
	}
	
	public void run(){
		CustomerArrivedEvent e = new CustomerArrivedEvent(state.getCurrentTicks(), new Customer(state.getCurrentCustomerID()));
		e.subscribe(new AddCustomerToBankState(state));
		e.subscribe(new UpdateBankStateTime(state));
		e.subscribe(new AddNextCustomerArriveEvent(events, state));
		events.addEvent(e);
		state.incrementCustomerID();
		while(true){
			events.fireNextEvent();
		}
	}
}
