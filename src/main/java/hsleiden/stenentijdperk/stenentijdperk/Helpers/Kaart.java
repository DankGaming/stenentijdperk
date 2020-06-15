package hsleiden.stenentijdperk.stenentijdperk.Helpers;

import java.util.ArrayList;
import java.util.HashMap;

public class Kaart {
	private int punten;
	private ArrayList<Resource> resources;
	private Resource tool;
	private Resource graan;
	private String treasure;
	private HashMap<Integer, Integer> multiplier;

	public Kaart(int punten/*
							 * , ArrayList<Resource> resources, Resource tool, Resource graan, String
							 * treasure, HashMap<Integer, Integer> multiplier
							 */) {
		this.punten = punten;
		this.resources = resources;
		this.graan = graan;
		this.treasure = treasure;
		this.multiplier = multiplier;
	}

	public int getPunten() {
		return this.punten;
	}

	public ArrayList<Resource> getResources() {
		return this.resources;
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

	public HashMap<Integer, Integer> getMultiplier() {
		return this.multiplier;
	}
}
