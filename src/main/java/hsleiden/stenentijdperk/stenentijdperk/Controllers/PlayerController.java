package hsleiden.stenentijdperk.stenentijdperk.Controllers;

import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;;

public class PlayerController {
	private PlayerModel playermodel;

	public PlayerController() {
		playermodel = new PlayerModel();
	}

	public int getVillagers() {
		return playermodel.getVillagers();
	}

	public void setVillagers(int villagers) {
		playermodel.setVillagers(villagers);
	}
}
