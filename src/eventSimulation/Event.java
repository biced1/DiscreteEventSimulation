package eventSimulation;

import model.Customer;

public abstract class Event {
	protected long ticks;
	protected Customer c;
	
	public Event(long ticks, Customer c){
		this.ticks = ticks;
		this.c = c;
	}
	
	public long getTicks(){
		return ticks;
	}
	
	public abstract void fire();
}
