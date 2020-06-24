package hsleiden.stenentijdperk.stenentijdperk.Models;

import hsleiden.stenentijdperk.stenentijdperk.Controllers.BoardController;
import hsleiden.stenentijdperk.stenentijdperk.Controllers.PlayerController;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Kaart;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.StaticHut;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Resource;
import hsleiden.stenentijdperk.stenentijdperk.observers.BoardObservable;
import hsleiden.stenentijdperk.stenentijdperk.observers.BoardObserver;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.io.File;

public class BoardModel implements BoardObservable {
    private boolean isPlaceable;
    private PlayerModel player;
    private int turn;
    private boolean placed;
    private List<Kaart> kaarten = new ArrayList<Kaart>();
    private List<StaticHut> hutKaarten = new ArrayList<StaticHut>();
    private List<StaticHut> hutStapel1 = new ArrayList<StaticHut>();
    private List<StaticHut> hutStapel2 = new ArrayList<StaticHut>();
    private List<StaticHut> hutStapel3 = new ArrayList<StaticHut>();
    private List<StaticHut> hutStapel4 = new ArrayList<StaticHut>();
    private BoardController controller;
    private String LabelText;
    private PlayerController playerController;
    private int phase;
    private ArrayList<StaticHut> hutjes = new ArrayList<>();
    private ArrayList<Resource> locaties = new ArrayList<>();
    public ArrayList<BoardObserver> observers = new ArrayList<>();

    public BoardModel() {
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

        File folder = new File("src/main/Resources/Kaarten/");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            this.kaarten.add(i, new Kaart(i, "src/main/Resources/Kaarten/" + listOfFiles[i].getName()));
        }
        Collections.shuffle(this.kaarten);

        folder = new File("src/main/Resources/Hutjes/");
        listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            this.hutKaarten.add(i, new StaticHut(i, "src/main/Resources/Hutjes/" + listOfFiles[i].getName()));
        }
        Collections.shuffle(this.hutKaarten);
        int n = this.hutKaarten.size();

        this.hutStapel1 = new ArrayList<StaticHut>(this.hutKaarten.subList(0, (n + 1) / 4));
        this.hutStapel2 = new ArrayList<StaticHut>(this.hutKaarten.subList((n + 1) / 4, (n + 1) / 2));
        this.hutStapel3 = new ArrayList<StaticHut>(this.hutKaarten.subList((n + 1) / 2, ((n + 1) / 4) * 3));
        this.hutStapel4 = new ArrayList<StaticHut>(this.hutKaarten.subList(((n + 1) / 4) * 3, n));

        this.hutKaarten.clear();
    }

    public List<Kaart> removeKaart(int index) {
        this.kaarten.remove(index);
        return this.kaarten;
    }

    public List<StaticHut> removeHut(int stapel) {
        switch (stapel) {
            case 0:
                this.hutStapel1.remove(0);
                return this.hutStapel1;
            case 1:
                this.hutStapel2.remove(0);
                return this.hutStapel2;
            case 2:
                this.hutStapel3.remove(0);
                return this.hutStapel3;
            case 3:
                this.hutStapel4.remove(0);
                return this.hutStapel4;
            default:
                return null;
        }
    }

    public Kaart getKaart(int index) {
        return this.kaarten.get(index);
    }

    public StaticHut getHut(int stapel, int index) {
        switch (stapel) {
            case 0:
                return this.hutStapel1.get(index);
            case 1:
                return this.hutStapel2.get(index);
            case 2:
                return this.hutStapel3.get(index);
            case 3:
                return this.hutStapel4.get(index);
            default:
                return null;
        }
    }

    public void setKaarten(ArrayList<Kaart> kaarten) {
        this.kaarten = kaarten;
    }

    public List<Kaart> getKaarten() {
        return this.kaarten;
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

    public void addTurn() {
        this.turn += 1;
    }

    public Resource getResource(int index) {
        return this.locaties.get(index);
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public ArrayList<StaticHut> getHutjes() {
        return hutjes;
    }

    public void setHutjes(ArrayList<StaticHut> hutjes) {
        this.hutjes = hutjes;
    }

    public ArrayList<Resource> getLocaties() {
        return this.locaties;
    }

    public void setLocaties(ArrayList<Resource> res) {
        this.locaties = res;
    }
}