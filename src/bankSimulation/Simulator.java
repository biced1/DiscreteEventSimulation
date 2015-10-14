package bankSimulation;

import java.util.Scanner;

import bankEventHandler.customerArrivedEventHandler.AddNextCustomerArriveEvent;
import bankEventHandler.customerArrivedEventHandler.UpdateBankStateOnArrival;
import bankEvents.CustomerArrivedEvent;
import eventSimulation.Events;
import model.BankState;
import model.Customer;

public class Simulator {
	private BankState state;
	private int tellers = 3;
	private int lines = 1;
	private Events events = new Events();

	public Simulator() {
		state = new BankState(tellers, lines);
	}

	public void run() {
		CustomerArrivedEvent e = new CustomerArrivedEvent(state.getCurrentTicks(),
				new Customer(state.getCurrentCustomerID()));
		e.subscribe(new UpdateBankStateOnArrival(state, events));
		e.subscribe(new AddNextCustomerArriveEvent(events, state));
		events.addEvent(e);
		state.incrementCustomerID();
		int eventsTodo = 1;
		while (true) {
			for (int x = 0; x < eventsTodo; x++) {
				events.fireNextEvent();
			}
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();
			try {
				eventsTodo = Integer.parseInt(input);
			} catch (Exception ex) {
				eventsTodo = 1;
			}
		}
	}
}
