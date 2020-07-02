package hsleiden.stenentijdperk.stenentijdperk.Controllers;

import hsleiden.stenentijdperk.stenentijdperk.Helpers.Tool;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.StaticHut;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;

import java.util.ArrayList;
import java.util.List;

public class PlayerController {

	public PlayerController() {
	}

	public int getVillagers(PlayerModel playermodel) {
		return playermodel.getVillagers();
	}

	public void setVillagers(PlayerModel playermodel, int villagers) {
		playermodel.setVillagers(villagers);
	}

	public int getMaxVillagers(PlayerModel playerModel) {
		return playerModel.getMaxVillagers();
	}

	public void addMaxVillagers(PlayerModel playerModel) {
		playerModel.addMaxVillagers();
	}

	public ArrayList<Tool> getTools(PlayerModel playermodel) {
		return playermodel.getTools();
	}

	public int getPositie(PlayerModel playerModel, int index) {
		return playerModel.getPositie(index);
	}

	public void setPositie(PlayerModel playerModel, int index, int stamleden) {
		playerModel.setPositie(index, stamleden);
	}

	public void addGraan(PlayerModel playerModel) {
		playerModel.increaseGraan();
	}

	public int vraagGraan(PlayerModel playerModel) {
		return playerModel.getGraan();
	}

	public List<Integer> vraagPosities(PlayerModel playerModel) {
		return playerModel.getPosities();
	}

	public List<Integer> vraagResources(PlayerModel playerModel) {
		return playerModel.getResources();
	}

	public void reduceResource(PlayerModel playerModel, int index, int amount) {
		playerModel.reduceResources(index, amount);
	}

	public void increaseResource(PlayerModel playerModel, int index, int amount) {
		playerModel.addResources(index, amount);
	}

	public void addTool(PlayerModel playerModel) {
		playerModel.addTool();
	}

	public String getNaam(PlayerModel playerModel) {
		return playerModel.getNaam();
	}

	public void increasePunten(PlayerModel playermodel, int punten) {
		playermodel.increasePunten(punten);
	}

	public List<Integer> getMultiplier(PlayerModel playermodel) {
		return playermodel.getMultiplier();
	}

	public List<StaticHut> getHutjes(PlayerModel playermodel) {
		return playermodel.getHutjes();
	}

	public List<String> getTreasures(PlayerModel playermodel) {
		return playermodel.getTreasures();
	}

	public void addTreasure(PlayerModel playermodel, String treasure) {
		playermodel.addTreasure(treasure);
	}
}
