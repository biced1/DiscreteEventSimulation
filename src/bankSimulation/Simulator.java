package bankSimulation;

import java.util.Random;

import bankEvenHandler.AddCustomerToBankState;
import bankEvents.CustomerArrivedEvent;
import model.BankState;
import model.Customer;

public class Simulator {
	private long ticks = 0;
	private long nextCustomerId = 1;
	private BankState state;
	private int tellers = 1;
	private int lines = 3;
	
	public Simulator(){
		state = new BankState(tellers, lines);
	}
	
	public void run(){
		while(true){
			CustomerArrivedEvent e = new CustomerArrivedEvent(ticks, new Customer(nextCustomerId));
			e.subscribe(new AddCustomerToBankState(state));
			e.fire();
			nextCustomerId++;
			ticks += getRandomWaitTicks(100, 5000);
		}
	}
	
	public int getRandomWaitTicks(int minimum, int maximum){
		Random rand = new Random();
		int randomTicks = rand.nextInt(maximum) + minimum;
		try {
			Thread.sleep(randomTicks);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return randomTicks;
	}


}
