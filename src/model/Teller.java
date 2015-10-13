package model;

public class Teller {
	private boolean isServicing;
	private int tellerID;
	
	public Teller(int ID){
		this.tellerID = ID;
		this.isServicing = false;
	}

	public boolean isServicing() {
		return isServicing;
	}

	public void setServicing(boolean isServicing) {
		this.isServicing = isServicing;
	}

	public int getTellerID() {
		return tellerID;
	}

	public void setTellerID(int tellerID) {
		this.tellerID = tellerID;
	}
}
