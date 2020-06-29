package hsleiden.stenentijdperk.stenentijdperk.Models;

import hsleiden.stenentijdperk.stenentijdperk.Helpers.Beschavingskaarten.Kaart;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.StaticHut;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Tool;
import hsleiden.stenentijdperk.stenentijdperk.observers.PlayerObservable;
import hsleiden.stenentijdperk.stenentijdperk.observers.PlayerObserver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerModel implements PlayerObservable {
    ArrayList<PlayerObserver> observers = new ArrayList<PlayerObserver>();
    private int lobby;
    private String playerNumber;
    private String naam;
    private int maxVillagers;
    private int villagers;
    private ArrayList<Kaart> kaarten = new ArrayList<>();
    private ArrayList<StaticHut> hutjes = new ArrayList<>();
    private ArrayList<Tool> tools = new ArrayList<>();
    private List<Integer> resources;
    private List<Integer> posities = new ArrayList<>();
    private int graan;
    private List<Integer> multiplier = new ArrayList<>();
    private int punten;

    public PlayerModel() {
    }

    public PlayerModel(String naam) {
        this.punten = 0;
        this.naam = naam;
        this.villagers = 5;
        this.maxVillagers = 5;
        this.graan = 0;
        this.resources = Arrays.asList(12, 0, 0, 0, 0);
        this.multiplier = Arrays.asList(0, 0, 0, 0);

        for (int i = 0; i < 16; i++) {
            posities.add(0);
        }

        notifyAllObservers();
    }

    public int getPunten() {
        return this.punten;
    }

    public void setPunten(int punten) {
        this.punten = punten;
    }

    public int getToolLevel(int index) {
        return this.tools.get(index).getLevel();
    }

    public void increaseToolLevel(int index) {
        this.tools.get(index).increaseLevel();
    }

    public int getResource(int index) {
        return resources.get(index);
    }

    public void setResource(int index, int resources) {
        this.resources.set(index, resources);
        FirebaseController.updateDocumentList(String.valueOf(this.getLobby()), this.getPlayerNumber(), "resources", this.resources);
    }

    public void addResources(int index, int resources) {
        this.resources.set(index, this.resources.get(index) + resources);
        FirebaseController.updateDocumentList(String.valueOf(this.getLobby()), this.getPlayerNumber(), "resources", this.resources);
    }

    public void reduceResources(int index, int resources) {
        this.resources.set(index, this.resources.get(index) - resources);
        FirebaseController.updateDocumentList(String.valueOf(this.getLobby()), this.getPlayerNumber(), "resources", this.resources);
    }

    public void addMaxVillagers() {
        this.maxVillagers += 1;
        FirebaseController.updateDocument(String.valueOf(this.getLobby()), this.getPlayerNumber(), "maxVillagers", this.maxVillagers);
    }

    public void increaseGraan() {
        this.graan += 1;
        FirebaseController.updateDocument(String.valueOf(this.getLobby()), this.getPlayerNumber(), "graan", this.graan);
    }

    public void addTool() {
        Tool tool = new Tool();
        tools.add(tool);
        FirebaseController.updateDocument(String.valueOf(this.getLobby()), this.getPlayerNumber(), "tools", this.tools);
    }

    public List<Integer> getPosities() {
        return this.posities;
    }

    public void setPosities(List<Integer> pos) {
        this.posities = pos;
        FirebaseController.updateDocumentList(String.valueOf(this.getLobby()), this.getPlayerNumber(), "posities", this.posities);
    }

    public ArrayList<Kaart> getKaarten() {
        return kaarten;
    }

    public void setKaarten(ArrayList<Kaart> kaarten) {
        this.kaarten = kaarten;
        FirebaseController.setPlayerKaarten(String.valueOf(this.getLobby()), this.getPlayerNumber(), "kaarten", this.kaarten);
    }
    public void addKaarten(Kaart kaart){
        kaarten.add(kaart);
    }
    public ArrayList<StaticHut> getHutjes() {
        return hutjes;
    }

    public void setHutjes(ArrayList<StaticHut> hutjes) {
        this.hutjes = hutjes;
        FirebaseController.updateDocumentList(String.valueOf(this.getLobby()), this.getPlayerNumber(), "hutjes", this.hutjes);
    }

    public List<Integer> getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(List<Integer> multiplier) {
        this.multiplier = multiplier;
        FirebaseController.updateDocumentList(String.valueOf(this.getLobby()), this.getPlayerNumber(), "multiplier", this.multiplier);
    }

    public int getLobby() {
        return lobby;
    }

    public void setLobby(int lobby) {
        this.lobby = lobby;
    }

    public String getNaam() {
        return this.naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getVillagers() {
        return this.villagers;
    }

    public void setVillagers(int villagers) {
        this.villagers = villagers;
        FirebaseController.updateDocument(String.valueOf(this.getLobby()), this.getPlayerNumber(), "villagers", this.villagers);
    }

    @Override
    public void registerObserver(PlayerObserver po) {
        observers.add(po);
        notifyAllObservers();
    }

    @Override
    public void notifyAllObservers() {
        for (PlayerObserver po : observers) {
            po.update(this);
        }
    }

    public ArrayList<Tool> getTools() {
        return tools;
    }

    public void setTools(ArrayList<Tool> tools) {
        this.tools = tools;
        FirebaseController.updateDocument(String.valueOf(this.getLobby()), this.getPlayerNumber(), "tools", this.tools);
    }

    public List<Integer> getResources() {
        return this.resources;
    }

    public void setResource(List<Integer> resources) {
        this.resources = resources;
        FirebaseController.updateDocumentList(String.valueOf(this.getLobby()), this.getPlayerNumber(), "resources", this.resources);
    }

    public int getPositie(int index) {
        return posities.get(index);
    }

    public void setPositie(int index, int posities) {
        this.posities.set(index, posities);
        FirebaseController.updateDocumentList(String.valueOf(this.getLobby()), this.getPlayerNumber(), "posities", this.posities);
    }

    public int getMaxVillagers() {
        return maxVillagers;
    }

    public int getGraan() {
        return graan;
    }

    public void setGraan(int graan) {
        this.graan = graan;
        System.out.println(String.valueOf(this.getLobby()) + " " + this.getPlayerNumber() + " " + this.graan);
        FirebaseController.updateDocument(String.valueOf(this.getLobby()), this.getPlayerNumber(), "graan", this.graan);
    }

    public String getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(String playerNumber) {
        this.playerNumber = playerNumber;
    }
}
