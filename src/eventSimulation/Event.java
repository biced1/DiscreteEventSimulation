package eventSimulation;

public abstract class Event {
	private long ticks;
	
	public Event(long ticks){
		this.ticks = ticks;
	}
	
	public long getTicks(){
		return ticks;
	}
	
	public abstract void fire();
}
