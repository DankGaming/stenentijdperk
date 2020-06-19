package hsleiden.stenentijdperk.stenentijdperk.Helpers;

public class Resource {
	private final String naam;
	private final int waarde;
	private final int maxCap;
	private int hoeveelheid;
	private int villagers = 0;
	private boolean status;

	public Resource(String naam, int hoeveelheid, int waarde, int maxCap) {
		this.naam = naam;
		this.hoeveelheid = hoeveelheid;
		this.waarde = waarde;
		this.maxCap = maxCap;
	}

	public String getNaam() {
		return this.naam;
	}

	public int getHoeveelheid() {
		return this.hoeveelheid;
	}

	public void setHoeveelheid(int amount) {
		this.hoeveelheid = amount;
	}

	public void reduceHoeveelheid(int amount) {
		this.hoeveelheid -= amount;
	}

	public void addHoeveelheid(int amount) {
		this.hoeveelheid += amount;
	}

	public int getWaarde() {
		return this.waarde;
	}

	public int getMaxCap() {
		return this.maxCap;
	}

	public int getVillagers() {
		return this.villagers;
	}

	public void setVillager(int villagers) {
		this.villagers = villagers;
	}
}
