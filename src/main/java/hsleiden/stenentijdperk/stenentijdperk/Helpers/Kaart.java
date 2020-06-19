package hsleiden.stenentijdperk.stenentijdperk.Helpers;

import hsleiden.stenentijdperk.stenentijdperk.Interfaces.Status;

public class Kaart implements Status {
	private int punten;
	private Resource tool;
	private Resource graan;
	private String treasure;
	private boolean status;
	private String path;

	public Kaart(int punten, String path) {
		this.path = path;
		this.punten = punten;
		this.status = false;
	}

	public String getPath() {
		return this.path;
	}

	public int getPunten() {
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
