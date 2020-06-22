package hsleiden.stenentijdperk.stenentijdperk.Controllers;

import hsleiden.stenentijdperk.stenentijdperk.Helpers.Tool;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;

import java.util.*;

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

	public int getMaxVillagers(PlayerModel playerModel){
		return playerModel.getMaxVillagers();
	}

	public void addMaxVillagers(PlayerModel playerModel) {
		playerModel.addMaxVillagers();
	}

	public ArrayList<Tool> getTools(PlayerModel playermodel){
		return playermodel.getTools();
	}

	public int getPositie(PlayerModel playerModel, int index){
		return playerModel.getPositie(index);
	}

	public void setPositie(PlayerModel playerModel, int index, int stamleden){
		playerModel.setPositie(index, stamleden);
	}

	public void addGraan(PlayerModel playerModel){
		playerModel.increaseGraan();
	}

	public int vraagGraan(PlayerModel playerModel){
		return playerModel.getGraan();
	}

	public List<Integer> vraagResources(PlayerModel playerModel){
		return playerModel.getPosities();
	}
	
	public void addTool(PlayerModel playerModel){
		playerModel.addTool();
	}

	public void increaseToolLevel(PlayerModel playerModel, int index){
		playerModel.increaseToolLevel(index);
	}

	public int getToolLevel(PlayerModel playerModel, int index){
		return playerModel.getToolLevel(index);
	}
}
