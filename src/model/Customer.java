package model;

public class Customer {
	public final long ID;
	private long startWaitTicks;
	private long startServiceTicks;
	private long endWaitTicks;

	public Customer(long nextCustomerId) {
		this.ID = nextCustomerId;
	}

	public long getStartWaitTicks() {
		return startWaitTicks;
	}

	public void setStartWaitTicks(long startWaitTicks) {
		this.startWaitTicks = startWaitTicks;
	}

	public long getStartServiceTicks() {
		return startServiceTicks;
	}

	public void setStartServiceTicks(long startServiceTicks) {
		this.startServiceTicks = startServiceTicks;
	}

	public long getEndWaitTicks() {
		return endWaitTicks;
	}

	public void setEndWaitTicks(long endWaitTicks) {
		this.endWaitTicks = endWaitTicks;
	}
}
