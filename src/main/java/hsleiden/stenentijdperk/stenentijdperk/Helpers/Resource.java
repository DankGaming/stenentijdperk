package hsleiden.stenentijdperk.stenentijdperk.Helpers;

public class Resource {
	private String naam;
	private int hoeveelheid;
	private int waarde; 
	
	public Resource(String naam, int hoeveelheid, int waarde) {
		this.naam = naam;
		this.hoeveelheid = hoeveelheid;
		this.waarde = waarde;
	}
	
	public String getNaam() {
		return this.naam;
	}
	
	public int getHoeveelheid() {
		return this.hoeveelheid;
	}
	
	public int getWaarde() {
		return this.waarde;
	}
}
