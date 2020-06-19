package hsleiden.stenentijdperk.stenentijdperk.Controllers;

import hsleiden.stenentijdperk.stenentijdperk.Helpers.Tool;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;

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

	public Tool[] getTools(PlayerModel playermodel){
		return playermodel.getTools();
	}

	public Tool getTool(PlayerModel playerModel, int index) {
		return playerModel.getTool(index);
	}

	public void setTool(PlayerModel playermodel, int index, Tool tool){
		playermodel.setTools(index, tool);
	}

	public int getPosities(PlayerModel playerModel, int index){
		return playerModel.getPosities(index);
	}

	public void setPosities(PlayerModel playerModel, int index, int stamleden){
		playerModel.setPosities(index, stamleden);
	}
}
