package hsleiden.stenentijdperk.stenentijdperk.Models;

import hsleiden.stenentijdperk.stenentijdperk.Helpers.Kaart;
import hsleiden.stenentijdperk.stenentijdperk.Views.BoardView;
import hsleiden.stenentijdperk.stenentijdperk.observers.BoardObservable;
import hsleiden.stenentijdperk.stenentijdperk.observers.BoardObserver;

import java.util.ArrayList;
import java.util.*;

public class BoardModel implements BoardObservable {
    private Kaart[] kaarten;
    private boolean isPlaceable;
    private PlayerModel player;
    private int food;
    private int wood;
    private int clay;
    private int rock;
    private int gold;
    private int tools;
    private int huts;
    private int turn;
    private int villagersOnBoard; // placeholder voor locatie
    private boolean wincondition;
    private boolean placed;

    ArrayList<BoardObserver> observers = new ArrayList<>();

    public BoardModel() {
        this.food = 53;
        this.wood = 17;
        this.clay = 17;
        this.rock = 17;
        this.gold = 17;
        this.tools = 18;
        this.huts = 28;
        this.villagersOnBoard = 0;
        this.wincondition = false;
        this.isPlaceable = true;
        this.turn = 1;
        this.placed = false;
        this.kaarten = new Kaart[10];
        for (int i = 0; i < 10; i++) {
            this.kaarten[i] = new Kaart(i);
        }
        Collections.shuffle(Arrays.asList(this.kaarten));
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

    // dit moet naar resources
    public void setVillagersOnBoard(int villagers) {
        this.villagersOnBoard = villagers;
    }

    public int getVillagersOnBoard() {
        return this.villagersOnBoard;
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
}