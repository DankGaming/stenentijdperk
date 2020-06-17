package hsleiden.stenentijdperk.stenentijdperk.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hsleiden.stenentijdperk.stenentijdperk.Helpers.Kaart;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.StaticHut;

public class PlayerModel {
    private String naam;
    private int villagers;
    private int[] tools = new int[4];
    private int[] resources = {10,0,0,0,0};
    private int[] posities = new int[16];
    private ArrayList<Kaart> kaarten = new ArrayList<Kaart>();
    private ArrayList<StaticHut> hutjes = new ArrayList<StaticHut>();

    public PlayerModel(String naam) {
        this.villagers = 5;
        this.naam = naam;
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

    public int getTools(int index){
        return tools[index];
    }

    public void setTools(int index, int change){
        this.tools[index] = change;
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
        return posities[index];
    }

    public void setPosities(int index,int posities) {
        this.posities[index] = posities;
    }

    
}
