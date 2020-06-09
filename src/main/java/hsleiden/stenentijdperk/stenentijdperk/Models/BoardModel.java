package hsleiden.stenentijdperk.stenentijdperk.Models;

import hsleiden.stenentijdperk.stenentijdperk.Helpers.Kaart;
import hsleiden.stenentijdperk.stenentijdperk.Views.BoardView;

public class BoardModel {
    private Kaart[] kaarten;
    private boolean isPlaceable;
    private String[] players = { "Matt", "Jake" };
    private int food;
    private int wood;
    private int clay;
    private int rock;
    private int gold;
    private int tools;
    private int huts;
    private int villagersOnBoard; // placeholder voor locatie
    private boolean wincondition;

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
    }

    public void setPlaceable(boolean isPlaceable) {
        this.isPlaceable = isPlaceable;
    }

    public boolean getPlaceable() {
        return this.isPlaceable;
    }

    public String[] getPlayers() {
        return this.players;
    }

    // dit moet naar resources
    public void setVillagersOnBoard(int villagers) {
        this.villagersOnBoard = villagers;
    }

    public int getVillagersOnBoard() {
        return this.villagersOnBoard;
    }
}