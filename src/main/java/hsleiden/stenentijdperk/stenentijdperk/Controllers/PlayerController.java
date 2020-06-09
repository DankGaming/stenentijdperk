package hsleiden.stenentijdperk.stenentijdperk.Controllers;

import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;;

public class PlayerController {
	private PlayerModel playermodel;

	public PlayerController() {
	}

	public int getVillagers(PlayerModel playermodel) {
		return playermodel.getVillagers();
	}

	public void setVillagers(PlayerModel playermodel, int villagers) {
		playermodel.setVillagers(villagers);
	}
}
