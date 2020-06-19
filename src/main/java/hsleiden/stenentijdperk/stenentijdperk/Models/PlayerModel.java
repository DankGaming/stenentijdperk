package hsleiden.stenentijdperk.stenentijdperk.Models;

import hsleiden.stenentijdperk.stenentijdperk.observers.LobbyObserver;
import hsleiden.stenentijdperk.stenentijdperk.observers.TableauObserver;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Kaart;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.StaticHut;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Tool;

import java.util.*;

public class PlayerModel {
    private String naam;
    private int maxVillagers;
    private int villagers; 
    private Tool[] tools = new Tool[3];
    private TableauModel tableauModal;
    private int[] resources = {10,0,0,0,0};
    private List<Integer> posities = new ArrayList<Integer>();
    private ArrayList<Kaart> kaarten = new ArrayList<Kaart>();
    private ArrayList<StaticHut> hutjes = new ArrayList<StaticHut>();
    private int graan;

    public PlayerModel(){}

    public PlayerModel(String naam) {
        this.villagers = 5;
        this.maxVillagers = 5;
        this.naam = naam;
        this.graan = 0;
        for (int i = 0; i < 16; i++) {
            posities.add(0);
          }
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getNaam() {
        return this.naam;
    }

    public void setVillagers(int villagers) {
        this.villagers = villagers;
    }

    public int getVillagers() {
        return this.villagers;
    }

    public Tool[] getTools() {
        return tools;
    }

    public Tool getTool(int index) {
        return tools[index];
    }

    public void setTools(int index, Tool tool) {
        this.tools[index] = tool;
    }

    public int getResources(int index) {
        return resources[index];
    }

    public void setResources(int index, int resources) {
        this.resources[index] = resources;
    }

    public void addResources(int index, int resources) {
        this.resources[index] += resources;
    }

    public int getPosities(int index) {
        return posities.get(index);
    }

    public void setPosities(int index,int posities) {
        this.posities.set(index, posities);
    }

    public int getMaxVillagers() {
        return maxVillagers;
    }

    public void addMaxVillagers() {
        this.maxVillagers += maxVillagers;
    }

    public int getGraan() {
        return graan;
    }

    public void increaseGraan() {
        this.graan += 1;
    }

    public void registerObserver(TableauObserver to) {
        this.tableauModal.register(to);
    }

    public List<Integer> getAllResources(){
        return this.posities;
    }
}
