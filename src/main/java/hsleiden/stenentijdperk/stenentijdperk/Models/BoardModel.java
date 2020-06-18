package hsleiden.stenentijdperk.stenentijdperk.Models;

import hsleiden.stenentijdperk.stenentijdperk.Controllers.BoardController;
import hsleiden.stenentijdperk.stenentijdperk.Controllers.PlayerController;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Kaart;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Resource;
import hsleiden.stenentijdperk.stenentijdperk.observers.BoardObservable;
import hsleiden.stenentijdperk.stenentijdperk.observers.BoardObserver;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import java.io.File;

public class BoardModel implements BoardObservable {
    private Kaart[] kaarten; // temp made public en dit moet datatype Kaart worden
    private boolean isPlaceable;
    private PlayerModel player;
    private int turn;
    private boolean wincondition;
    private boolean placed;
    private BoardController controller;
    private String LabelText;
    private PlayerController playerController;
    private int phase;
    private ArrayList<String> kaartPaths = new ArrayList<String>();
    private ArrayList<Resource> locaties = new ArrayList<>();
    private String path = "src/main/Resources/Kaarten/";
    public ArrayList<BoardObserver> observers = new ArrayList<>();

    public BoardModel() {
        this.wincondition = false;
        this.isPlaceable = true;
        this.turn = 1;
        this.placed = false;
        this.phase = 1;
        Resource food = new Resource("Food", 500, 2, 40);
        Resource wood = new Resource("Wood", 28, 3, 7);
        Resource leem = new Resource("Leem", 18, 4, 7);
        Resource stone = new Resource("Stone", 12, 5, 7);
        Resource gold = new Resource("Gold", 10, 6, 7);
        locaties.add(food);
        locaties.add(wood);
        locaties.add(leem);
        locaties.add(stone);
        locaties.add(gold);
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            this.kaartPaths.add(path + listOfFiles[i].getName());
        }

        this.kaarten = new Kaart[10];
        for (int i = 0; i < 10; i++) {
            this.kaarten[i] = new Kaart(i);
        }
        Collections.shuffle(Arrays.asList(this.kaarten));
        Collections.shuffle(this.kaartPaths);
    }

    public String getKaartPath(int index) {
        return this.kaartPaths.get(index);
    }

    public Kaart getKaart(int index) {
        return this.kaarten[index];
    }

    public void setPlaceable(boolean isPlaceable) {
        this.isPlaceable = isPlaceable;
    }

    public boolean getPlaceable() {
        return this.isPlaceable;
    }

    // Dit verandered wie er aan de beurt is.
    public void setPlayer(PlayerModel player) {
        this.player = player;
    }

    public PlayerModel getPlayer() {
        return this.player;
    }

    // Dit houdt bij of de speler als iets heeft geplaast tijdens de beurt.
    public void setPlaced(boolean placed) {
        this.placed = placed;
    }

    public boolean getPlaced() {
        return this.placed;
    }

    // dit handelt all het veranderen van de hoeveelheid villagers
    public void changeVillagers(int index, int amount) {
        this.locaties.get(index).setVillager(amount + this.locaties.get(index).getVillagers());
    }

    public int requestVillagers(int index) {
        return this.locaties.get(index).getVillagers();
    }

    
    public int requestCap(int index) {
        return this.locaties.get(index).getMaxCap();
    }

    // dit is voor het toevoegen of weghalen van resources per locatie
    public void changeHoeveelheid(int index, int amount) {
        this.locaties.get(index).setHoeveelheid(amount + this.locaties.get(index).getHoeveelheid());
    }

    @Override
    public void register(BoardObserver boardobserver) {
        this.observers.add(boardobserver);
    }

    @Override
    public void notifyAllObservers() {
        for (BoardObserver boardobserver : observers) {
            boardobserver.update(this);
        }
    }

    @Override
    public int getTurn() {
        return this.turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public Resource getResource(int index){
        return this.locaties.get(index);
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

}
