package hsleiden.stenentijdperk.stenentijdperk.Models;

import hsleiden.stenentijdperk.stenentijdperk.Helpers.Kaart;

public class SpelbordModel {
	//private Kaart[] kaarten;

	private Spoor voedselSpoor;
	private Spoor puntenSpoor;
	private List<HutFiches>[] hutfiches;
	private Beschavingskaart[] beschavingskaarten;
	private List<Beschavingskaart> kaarten;

	public SpelbordModel() {
		voedselSpoor = new Voedselspoor();
		puntenSpoor = new Puntenspoor();
		maakHutFiches();
		maakBeschavingsKaarten();
	}

	public void maakHutFiches(){

	}
	public void maakBeschavingsKaarten(){

	}
}
