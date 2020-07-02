package hsleiden.stenentijdperk.stenentijdperk.Controllers;

import hsleiden.stenentijdperk.stenentijdperk.Helpers.Tool;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Beschavingskaarten.Kaart;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;

import java.util.ArrayList;
import java.util.List;

public class PlayerController {

    public PlayerController() {
    }

    public void addKaarten(PlayerModel playerModel, Kaart kaart) {
        playerModel.addKaarten(kaart);
    }

    public int getVillagers(PlayerModel playerModel) {
        return playerModel.getVillagers();
    }

    public void setVillagers(PlayerModel playerModel, int villagers) {
        playerModel.setVillagers(villagers);
    }

    public int getMaxVillagers(PlayerModel playerModel) {
        return playerModel.getMaxVillagers();
    }

    public void addMaxVillagers(PlayerModel playerModel) {
        playerModel.addMaxVillagers();
    }

    public ArrayList<Tool> getTools(PlayerModel playerModel) {
        return playerModel.getTools();
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
}
