package hsleiden.stenentijdperk.stenentijdperk.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hsleiden.stenentijdperk.stenentijdperk.Helpers.Kaart;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.StaticHut;

public class PlayerModel {
    private String naam;
    private int villagers;
    HashMap<Integer, Integer> toolHashMap = new HashMap<Integer, Integer>();
    HashMap<String, Integer> resourcesHashMap = new HashMap<String, Integer>();
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
}
