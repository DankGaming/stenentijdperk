package hsleiden.stenentijdperk.stenentijdperk.Models;

import hsleiden.stenentijdperk.stenentijdperk.Helpers.Kaart;
import hsleiden.stenentijdperk.stenentijdperk.Views.BoardView;

public class BoardModel {
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
    private int villagersOnBoard; // placeholder voor locatie
    private boolean wincondition;
    private boolean placed;

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
        this.placed = false;
    }

    // dit bepaald H
    public void setPlaceable(boolean isPlaceable) {
        this.isPlaceable = isPlaceable;
    }

    public boolean getPlaceable() {
        return this.isPlaceable;
    }

    // Dit verandered wie er aan de beurt is.
    public void setPlayer(PlayerModel player){
        this.player = player;
    }

    public PlayerModel getPlayer(){
        return this.player;
    }

    // Dit houdt bij of de speler als iets heeft geplaast tijdens de beurt.
    public void setPlaced(boolean placed){
        this.placed = placed;
    }

    public boolean getPlaced(){
        return this.placed;
    }

    // dit moet naar resources
    public void setVillagersOnBoard(int villagers) {
        this.villagersOnBoard = villagers;
    }

    public int getVillagersOnBoard() {
        return this.villagersOnBoard;
    }
}