package hsleiden.stenentijdperk.stenentijdperk.Models;

import hsleiden.stenentijdperk.stenentijdperk.Controllers.FirebaseController;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Beschavingskaarten.Kaart;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.StaticHut;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Tool;
import hsleiden.stenentijdperk.stenentijdperk.observers.PlayerObservable;
import hsleiden.stenentijdperk.stenentijdperk.observers.PlayerObserver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerModel implements PlayerObservable, Comparable {
    ArrayList<PlayerObserver> observers = new ArrayList<PlayerObserver>();
    private int lobby;
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
    private List<String> treasures = new ArrayList<>();
    private String playerNumber;

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
        if (this.getPlayerNumber() != null)
            FirebaseController.updateDocument(String.valueOf(this.getLobby()), this.getPlayerNumber(), "punten", this.punten);
    }
    
    public int getToolLevel(int index) {
        return this.tools.get(index).getLevel();
    }

    public void increaseToolLevel(int index) {
        this.tools.get(index).increaseLevel();
        if (this.getPlayerNumber() != null)
            FirebaseController.updateDocument(String.valueOf(this.getLobby()), this.getPlayerNumber(), "tools", this.tools);
        notifyAllObservers();
    }

    public int getResource(int index) {
        return resources.get(index);
    }

    public void setResource(int index, int amount) {
        this.resources.set(index, amount);
        if (this.getPlayerNumber() != null)
            FirebaseController.updateDocumentList(String.valueOf(this.getLobby()), this.getPlayerNumber(), "resources", this.resources);
        notifyAllObservers();
    }

    public void addResources(int index, int amount) {
        this.resources.set(index, this.resources.get(index) + amount);
        if (this.getPlayerNumber() != null)
            FirebaseController.updateDocumentList(String.valueOf(this.getLobby()), this.getPlayerNumber(), "resources", this.resources);
        notifyAllObservers();
    }

    public void reduceResources(int index, int amount) {
        this.resources.set(index, this.resources.get(index) - amount);
        if (this.getPlayerNumber() != null)
            FirebaseController.updateDocumentList(String.valueOf(this.getLobby()), this.getPlayerNumber(), "resources", this.resources);
        notifyAllObservers();
    }

    public void addMaxVillagers() {
        this.maxVillagers += 1;
        if (this.getPlayerNumber() != null)
            FirebaseController.updateDocument(String.valueOf(this.getLobby()), this.getPlayerNumber(), "villagers", this.villagers);
    }

    public void increaseGraan() {
        this.graan += 1;
        if (this.getPlayerNumber() != null)
            FirebaseController.updateDocument(String.valueOf(this.getLobby()), this.getPlayerNumber(), "graan", this.graan);
    }

    public void addTool() {
        Tool tool = new Tool();
        tools.add(tool);
        if (this.getPlayerNumber() != null)
            FirebaseController.updateDocument(String.valueOf(this.getLobby()), this.getPlayerNumber(), "tools", this.tools);
    }

    public void increasePunten(int punten) {
        this.punten += punten;
        if (this.getPlayerNumber() != null)
            FirebaseController.updateDocument(String.valueOf(this.getLobby()), this.getPlayerNumber(), "punten", this.punten);
    }

    public void decreasePunten(int punten) {
        this.punten -= punten;
        if (this.getPlayerNumber() != null)
            FirebaseController.updateDocument(String.valueOf(this.getLobby()), this.getPlayerNumber(), "punten", this.punten);
    }

    public List<Integer> getPosities() {
        return this.posities;
    }

    public void setPosities(List<Integer> pos) {
        this.posities = pos;
        if (this.getPlayerNumber() != null)
            FirebaseController.updateDocumentList(String.valueOf(this.getLobby()), this.getPlayerNumber(), "posities", this.posities);
    }

    public ArrayList<Kaart> getKaarten() {
        return kaarten;
    }

    public void setKaarten(ArrayList<Kaart> kaarten) {
        this.kaarten = kaarten;
        if (this.getPlayerNumber() != null)
            FirebaseController.setPlayerKaarten(String.valueOf(this.getLobby()), this.getPlayerNumber(), "kaarten", this.kaarten);
    }

    public void addKaarten(Kaart kaart) {
        kaarten.add(kaart);
        if (this.getPlayerNumber() != null)
            FirebaseController.setPlayerKaarten(String.valueOf(this.getLobby()), this.getPlayerNumber(), "kaarten", this.kaarten);
    }

    public ArrayList<StaticHut> getHutjes() {
        return hutjes;
    }

    public void setHutjes(ArrayList<StaticHut> hutjes) {
        this.hutjes = hutjes;
        if (this.getPlayerNumber() != null)
            FirebaseController.updateDocumentList(String.valueOf(this.getLobby()), this.getPlayerNumber(), "hutjes", this.hutjes);
    }

    public void addHutjes(StaticHut hut) {
        this.hutjes.add(hut);
        if (this.getPlayerNumber() != null)
            FirebaseController.updateDocumentList(String.valueOf(this.getLobby()), this.getPlayerNumber(), "hutjes", this.hutjes);
    }

    public List<Integer> getMultiplier() {
        return multiplier;
    }

    public void setMulitplier(List<Integer> multiplier) {
        this.multiplier = multiplier;
        if (this.getPlayerNumber() != null)
            FirebaseController.updateDocumentList(String.valueOf(this.getLobby()), this.getPlayerNumber(), "multiplier", this.multiplier);
        notifyAllObservers();
    }

    public void addMultiplier(int index, int multiplier) {
        this.resources.set(index, this.resources.get(index) + multiplier);
    }

    public int getLobby() {
        return lobby;
    }

    public void setLobby(int lobby) {
        this.lobby = lobby;
        if (this.getPlayerNumber() != null)
            FirebaseController.updateDocument(String.valueOf(this.getLobby()), this.getPlayerNumber(), "lobby", this.lobby);
    }

    public String getNaam() {
        return this.naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
        if (this.getPlayerNumber() != null)
            FirebaseController.updateDocument(String.valueOf(this.getLobby()), this.getPlayerNumber(), "treasures", this.treasures);
    }

    public int getVillagers() {
        return this.villagers;
    }

    public void setVillagers(int villagers) {
        this.villagers = villagers;
        if (this.getPlayerNumber() != null)
            FirebaseController.updateDocument(String.valueOf(this.getLobby()), this.getPlayerNumber(), "villagers", this.villagers);
        notifyAllObservers();
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
        if (this.getPlayerNumber() != null)
            FirebaseController.updateDocument(String.valueOf(this.getLobby()), this.getPlayerNumber(), "tools", this.tools);
        notifyAllObservers();
    }

    public List<Integer> getResources() {
        return this.resources;
    }

    public void setResource(List<Integer> resources) {
        this.resources = resources;
        if (this.getPlayerNumber() != null)
            FirebaseController.updateDocumentList(String.valueOf(this.getLobby()), this.getPlayerNumber(), "resources", this.resources);
        notifyAllObservers();
    }

    public int getPositie(int index) {
        return posities.get(index);
    }

    public void setPositie(int index, int posities) {
        this.posities.set(index, posities);
        if (this.getPlayerNumber() != null)
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
        if (this.getPlayerNumber() != null)
            FirebaseController.updateDocument(String.valueOf(this.getLobby()), this.getPlayerNumber(), "graan", this.graan);
    }

    public List<String> getTreasures() {
        return treasures;
    }

    public void setTreasures(List<String> treasures) {
        this.treasures = treasures;
        if (this.getPlayerNumber() != null)
            FirebaseController.updateDocument(String.valueOf(this.getLobby()), this.getPlayerNumber(), "treasures", this.treasures);
        notifyAllObservers();
    }

    public void addTreasure(String treasure) {
        this.treasures.add(treasure);
        if (this.getPlayerNumber() != null)
            FirebaseController.updateDocument(String.valueOf(this.getLobby()), this.getPlayerNumber(), "treasures", this.treasures);
        notifyAllObservers();
    }

    public void setPlayerNumber(String playerNumber) {
        this.playerNumber = playerNumber;
        if (this.getPlayerNumber() != null)
            FirebaseController.updateDocument(String.valueOf(this.getLobby()), this.getPlayerNumber(), "playerNumber", this.playerNumber);
        notifyAllObservers();
    }

    public String getPlayerNumber(){
        return this.playerNumber;
    }
    
    @Override
    public int compareTo(Object o) {
        int comparePunten = ((PlayerModel) o).getPunten();
        return comparePunten - this.getPunten();
    }
}
