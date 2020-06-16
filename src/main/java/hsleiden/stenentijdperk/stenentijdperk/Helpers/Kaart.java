package hsleiden.stenentijdperk.stenentijdperk.Helpers;

import hsleiden.stenentijdperk.stenentijdperk.Interfaces.Status;

public class Kaart implements Status {
	private int punten;
	private Resource tool;
	private Resource graan; 
	private String treasure;
	private boolean status;
 

	public Kaart(int punten) {
		this.punten = punten;	
	}
	
	public int getPunten(){
		return this.punten;
	}
	
	public Resource getTool() {
		return this.tool;
	}
	
	public Resource getGraan() {
		return this.graan;
	}
	
	public String getTreasure() {
		return this.treasure;
	}

	@Override
	public boolean getStatus() {
		return this.status;

	}

	@Override
	public void setStatus(boolean status) {
		this.status = status;

	}

	
}

