package bankSimulation;

import java.util.Scanner;

import bankEventHandler.customerArrivedEventHandler.AddNextCustomerArriveEvent;
import bankEventHandler.customerArrivedEventHandler.UpdateBankStateOnArrival;
import bankEvents.CustomerArrivedEvent;
import eventSimulation.Events;
import model.BankState;
import model.BankStatistics;
import model.Customer;

public class Simulator {
	private BankState state;
	private int tellers = 3;
	private int lines = 1;
	private Events events = new Events();
	private BankStatistics stats = new BankStatistics();

	public Simulator() {
		state = new BankState(tellers, lines);
	}

	public void run() {
		CustomerArrivedEvent e = new CustomerArrivedEvent(state.getCurrentTicks(),
				new Customer(state.getCurrentCustomerID()));
		e.subscribe(new UpdateBankStateOnArrival(state, events, stats));
		e.subscribe(new AddNextCustomerArriveEvent(events, state, stats));
		events.addEvent(e);
		state.incrementCustomerID();
		int eventsTodo = 1;
		while (true) {
			for (int x = 0; x < eventsTodo; x++) {
				events.fireNextEvent();
			}
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();
			if(input.equals("stats")){
				System.out.println("Average wait time " + stats.getAverageWaitTicks());
				eventsTodo = 0;
			}
			try {
				eventsTodo = Integer.parseInt(input);
			} catch (Exception ex) {
				eventsTodo = 1;
			}
		}
	}
}
