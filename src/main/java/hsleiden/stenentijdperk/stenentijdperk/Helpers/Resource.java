package hsleiden.stenentijdperk.stenentijdperk.Helpers;

public class Resource {
	private String naam;
	private int hoeveelheid;
	private int ogen;
	private int waarde; 
	
	public Resource(String naam, int hoeveelheid, int ogen, int waarde) {
		this.naam = naam;
		this.hoeveelheid = hoeveelheid;
		this.ogen = ogen;
		this.waarde = waarde;
	}
	
	public String getNaam() {
		return this.naam;
	}
	
	public int getHoeveelheid() {
		return this.hoeveelheid;
	}
	
	public int getOgen() {
		return this.ogen;
	}
	
	public int getWaarde() {
		return this.waarde;
	}
}
