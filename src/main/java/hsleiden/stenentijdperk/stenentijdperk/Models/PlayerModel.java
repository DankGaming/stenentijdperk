package hsleiden.stenentijdperk.stenentijdperk.Models;

import java.util.*;

import hsleiden.stenentijdperk.stenentijdperk.observers.TableauObserver;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Beschavingskaarten.Kaart;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.StaticHut;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Tool;

public class PlayerModel {
    private String naam;
    private int maxVillagers;
    private int villagers;
    private int punten;
    private ArrayList<Kaart> kaarten = new ArrayList<>();;
    private ArrayList<StaticHut> hutjes = new ArrayList<>();;
    private ArrayList<Tool> tools = new ArrayList<>();
    private TableauModel tableauModal;
    private List<Integer> resources;
    private List<Integer> posities = new ArrayList<>();
    private int graan;
    private List<Integer> multiplier = new ArrayList<>();

    public PlayerModel(){}

    public PlayerModel(String naam) {
        this.naam = naam;
        this.villagers = 5;
        this.maxVillagers = 5;
        this.punten = 0;
        this.graan = 0;
        this.resources = Arrays.asList(10, 0, 0, 0, 0);
        this.multiplier = Arrays.asList(0,0,0,0);
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

    public int getPunten() {
        return punten;
    }

    public void setPunten(int punten) {
        this.punten = punten;
    }

    public void setVillagers(int villagers) {
        this.villagers = villagers;
    }

    public int getVillagers() {
        return this.villagers;
    }

    public ArrayList<Tool> getTools() {
        return tools;
    }

    public void setTools(ArrayList<Tool> tools) {
        this.tools = tools;
    }

    public int getToolLevel(int index) {
        return this.tools.get(index).getLevel();
    }

    public void  increaseToolLevel(int index){
        this.tools.get(index).increaseLevel();
    }

    public int getResource(int index) {
        return resources.get(index);
    }

    public void setResource(int index, int resources) {
        this.resources.set(index, resources);
    }

    public List<Integer> getResources() {
        return this.resources;
    }

    public void setResource(List<Integer> resources) {
        this.resources = resources;
    }

    public void addResources(int index, int resources) {
        this.resources.set(index, resources);
    }

    public int getPositie(int index) {
        return posities.get(index);
    }

    public void setPositie(int index,int posities) {
        this.posities.set(index, posities);
    }

    public int getMaxVillagers() {
        return maxVillagers;
    }

    public void addMaxVillagers() {
        this.maxVillagers += 1;
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

    public List<Integer> getPosities(){
        return this.posities;
    }

    public void setPosities(List<Integer> pos){
        this.posities = pos;
    }

    public ArrayList<Kaart> getKaarten() {
        return kaarten;
    }

    public void setKaarten(ArrayList<Kaart> kaarten) {
        this.kaarten = kaarten;
    }

    public ArrayList<StaticHut> getHutjes() {
        return hutjes;
    }

    public void setHutjes(ArrayList<StaticHut> hutjes) {
        this.hutjes = hutjes;
    }

    public List<Integer> getMulitplier() {
        return multiplier;
    }

    public void setMulitplier(List<Integer> mulitplier) {
        this.multiplier = mulitplier;
    }

    public void addTool(){
        Tool tool = new Tool();
        tools.add(tool);
    }
}
