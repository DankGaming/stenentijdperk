package hsleiden.stenentijdperk.stenentijdperk.Models;

import hsleiden.stenentijdperk.stenentijdperk.Controllers.BoardController;
import hsleiden.stenentijdperk.stenentijdperk.Controllers.PlayerController;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Beschavingskaarten.BeschavingskaartMiddelen;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Beschavingskaarten.BeschavingskaartPunten;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Beschavingskaarten.BeschavingskaartWorpMiddelen;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Beschavingskaarten.Kaart;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Resource;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.StaticHut;
import hsleiden.stenentijdperk.stenentijdperk.observers.BoardObservable;
import hsleiden.stenentijdperk.stenentijdperk.observers.BoardObserver;

import java.util.ArrayList;
import java.util.Collections;

import java.io.File;

public class BoardModel implements BoardObservable {
    private boolean isPlaceable;
    private PlayerModel player;
    private int turn;
    private boolean placed;
    private ArrayList<Kaart> kaarten = new ArrayList<Kaart>();
    private BoardController controller;
    private String LabelText;
    private PlayerController playerController;
    private int phase;
    private ArrayList<StaticHut> hutjes = new ArrayList<>();
    private ArrayList<Resource> locaties = new ArrayList<>();
    private String path = "src/main/Resources/Kaarten/";
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
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        maakKaarten();

        Collections.shuffle(this.kaarten);
    }

    public ArrayList<Kaart> removeKaart(int index) {
        this.kaarten.remove(index);
        return this.kaarten;
    }

    public Kaart getKaart(int index) {
        return this.kaarten.get(index);
    }

    public void setKaarten(ArrayList<Kaart> kaarten){
        this.kaarten = kaarten;
    }

    public ArrayList<Kaart> getKaarten(){
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

    public ArrayList<Resource> getLocaties(){
        return this.locaties;
    }

    public void setLocaties(ArrayList<Resource> res){
        this.locaties = res;
    }

    public void maakKaarten(){
        this.kaarten.add(0 ,new BeschavingskaartMiddelen(1, path + "Food_Gpoint.png", 3, 0));
        this.kaarten.add(1 ,new BeschavingskaartMiddelen(1, path + "Food_Hpoint.png", 2, 0));
        this.kaarten.add(2 ,new BeschavingskaartMiddelen(1, path + "Food_Kruid.jpg", 5, 0));
        this.kaarten.add(3 ,new BeschavingskaartMiddelen(1, path + "Food_pot.png", 7, 0));
        this.kaarten.add(4 ,new BeschavingskaartMiddelen(1, path + "Food_Raam.jpg", 1, 0));
        this.kaarten.add(5 ,new BeschavingskaartMiddelen(1, path + "3Food_Raam.jpg", 3, 0));
        this.kaarten.add(6 ,new BeschavingskaartMiddelen(1, path + "4Food_Hpoint.jpg", 4, 0));

        this.kaarten.add(7 ,new BeschavingskaartMiddelen(1, path + "Leem_Bpoint.jpg", 1, 2));
        this.kaarten.add(8 ,new BeschavingskaartMiddelen(2, path + "Steen_Bpoint.png", 1, 3));
        this.kaarten.add(9 ,new BeschavingskaartMiddelen(1, path + "Steen_Gpoint.png", 1, 3));
        this.kaarten.add(10 ,new BeschavingskaartMiddelen(1, path + "Steen_Wagen.png", 2, 3));
        this.kaarten.add(11 ,new BeschavingskaartMiddelen(3, path + "Goud_Bpoint.png", 1, 4));


        this.kaarten.add(12 ,new BeschavingskaartWorpMiddelen(4, path + "xGoud_Idol.png", 6, 4));
        this.kaarten.add(13 ,new BeschavingskaartWorpMiddelen(4, path + "xSteen_Bpoint.png", 5, 3));
        this.kaarten.add(14 ,new BeschavingskaartWorpMiddelen(4, path + "xHout_Hpoint.png", 3, 3));

        this.kaarten.add(15 ,new BeschavingskaartPunten(2, path + "Point_Fluit.jpg", 3));
        this.kaarten.add(16 ,new BeschavingskaartPunten(2, path + "Point_Hpoint.png", 3));
    }
}