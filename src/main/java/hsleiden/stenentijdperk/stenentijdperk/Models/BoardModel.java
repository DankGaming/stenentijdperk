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

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;

public class BoardModel implements BoardObservable {
    public ArrayList<BoardObserver> observers = new ArrayList<>();
    private boolean isPlaceable;
    private int turn;
    private boolean placed;
    private PlayerModel player;
    private List<Kaart> kaarten = new ArrayList<Kaart>();
    private List<StaticHut> hutKaarten = new ArrayList<StaticHut>();
    private List<StaticHut> hutStapel1 = new ArrayList<StaticHut>();
    private List<StaticHut> hutStapel2 = new ArrayList<StaticHut>();
    private List<StaticHut> hutStapel3 = new ArrayList<StaticHut>();
    private List<StaticHut> hutStapel4 = new ArrayList<StaticHut>();
    private List<StaticHut> hutStapel5 = new ArrayList<StaticHut>();
    private BoardController controller;
    private String LabelText;
    private PlayerController playerController;
    private int phase;
    private ArrayList<StaticHut> hutjes = new ArrayList<>();
    private ArrayList<Resource> locaties = new ArrayList<>();

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

        maakKaarten();

        Collections.shuffle(this.kaarten);

        folder = new File("src/main/Resources/Hutjes/");
        listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            List<Integer> resourceKost = new ArrayList<Integer>();
            String[] s = listOfFiles[i].getName().split("\\.");
            int punten;
            if (s[0].contains("-")) {
                String kosten[] = s[0].split("-");
                for (String kost : kosten) {
                    resourceKost.add(Integer.parseInt(kost));
                }
                if (resourceKost.size() > 4) {
                    resourceKost.remove(4);
                }
                punten = (resourceKost.get(0) * 3) + (resourceKost.get(1) * 4) + (resourceKost.get(2) * 5)
                        + (resourceKost.get(3) * 6);
            } else {
                punten = 0;
                resourceKost.add(0);

            }

            this.hutKaarten.add(i,
                    new StaticHut(punten, resourceKost, "src/main/Resources/Hutjes/" + listOfFiles[i].getName()));
        }
        Collections.shuffle(this.hutKaarten);
        int n = this.hutKaarten.size();

        this.hutStapel1 = new ArrayList<StaticHut>(this.hutKaarten.subList(0, (n + 1) / 4));
        this.hutStapel2 = new ArrayList<StaticHut>(this.hutKaarten.subList((n + 1) / 4, (n + 1) / 2));
        this.hutStapel3 = new ArrayList<StaticHut>(this.hutKaarten.subList((n + 1) / 2, ((n + 1) / 4) * 3));
        this.hutStapel4 = new ArrayList<StaticHut>(this.hutKaarten.subList(((n + 1) / 4) * 3, n));
        this.hutStapel5.add(new StaticHut(0, new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0)), ""));

        this.hutKaarten.clear();
    }

    public List<Kaart> removeKaart(int index) {
        this.kaarten.remove(index);
        return this.kaarten;
    }

    public void removeHut(int stapel) {
        switch (stapel) {
            case 0:
                this.hutStapel1.remove(0);
                notifyAllObservers();
                break;
            case 1:
                this.hutStapel2.remove(0);
                notifyAllObservers();
                break;
            case 2:
                this.hutStapel3.remove(0);
                notifyAllObservers();
                break;
            case 3:
                this.hutStapel4.remove(0);
                notifyAllObservers();
                break;
        }
    }

    public Kaart getKaart(int index) {
        return this.kaarten.get(index);
    }

    public StaticHut getHut(int stapel) {
        switch (stapel) {
            case 0:
                return this.hutStapel1.get(0);
            case 1:
                return this.hutStapel2.get(0);
            case 2:
                return this.hutStapel3.get(0);
            case 3:
                return this.hutStapel4.get(0);
            default:
                return this.hutStapel5.get(0);
        }
    }

    public List<StaticHut> getHutStapel(int stapel) {
        switch (stapel) {
            case 0:
                return this.hutStapel1;
            case 1:
                return this.hutStapel2;
            case 2:
                return this.hutStapel3;
            case 3:
                return this.hutStapel4;
            default:
                return this.hutStapel5;
        }
    }

    // dit handelt all het veranderen van de hoeveelheid villagers
    public void increaseVillagers(int index, int amount) {
        this.locaties.get(index).increaseVillager(amount);
    }

    public void decreaseVillagers(int index, int amount) {
        this.locaties.get(index).reduceVillager(amount);
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

    public void addTurn() {
        this.turn += 1;
    }

    public Resource getResource(int index) {
        return this.locaties.get(index);
    }

    public void addResources(int index, int amount) {
        this.locaties.get(index).addHoeveelheid(amount);
    }

    public void reduceResources(int index, int amount) {
        this.locaties.get(index).reduceHoeveelheid(amount);
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

    public void maakKaarten() {
        this.kaarten.add(0, new BeschavingskaartMiddelen(1, "src/main/Resources/Kaarten/Food_Gpoint.png","", 4, 3, 0));
        this.kaarten.add(1, new BeschavingskaartMiddelen(1, "src/main/Resources/Kaarten/Food_Hpoint.png","",2, 2, 0));
        this.kaarten.add(2, new BeschavingskaartMiddelen(1, "src/main/Resources/Kaarten/Food_Kruid.jpg", "kruid",0, 5, 0));
        this.kaarten.add(3, new BeschavingskaartMiddelen(1, "src/main/Resources/Kaarten/Food_pot.png","pot" ,0, 7, 0));
        this.kaarten.add(4, new BeschavingskaartMiddelen(1, "src/main/Resources/Kaarten/Food_Raam.jpg", "raam",0, 1, 0));
        this.kaarten.add(5, new BeschavingskaartMiddelen(1, "src/main/Resources/Kaarten/3Food_Raam.jpg","raam",0, 3, 0));
        this.kaarten.add(6, new BeschavingskaartMiddelen(1, "src/main/Resources/Kaarten/Food_Hpoint.png", "", 2, 4, 0));

        this.kaarten.add(7, new BeschavingskaartMiddelen(1, "src/main/Resources/Kaarten/Leem_Bpoint.jpg","", 3, 1, 2));
        this.kaarten.add(8, new BeschavingskaartMiddelen(2, "src/main/Resources/Kaarten/Steen_Bpoint.png","",3, 1, 3));
        this.kaarten.add(9, new BeschavingskaartMiddelen(1, "src/main/Resources/Kaarten/Steen_Gpoint.png", "",4, 1, 3));
        this.kaarten.add(10, new BeschavingskaartMiddelen(1, "src/main/Resources/Kaarten/Steen_Wagen.png", "wagen",0, 2, 3));
        this.kaarten.add(11, new BeschavingskaartMiddelen(3, "src/main/Resources/Kaarten/Goud_Bpoint.png", "",3, 1, 4));

        this.kaarten.add(12, new BeschavingskaartWorpMiddelen(4, "src/main/Resources/Kaarten/xGoud_Idol.png","idol", 0, 6, 4));
        this.kaarten.add(13, new BeschavingskaartWorpMiddelen(4, "src/main/Resources/Kaarten/xSteen_Bpoint.png","" ,3, 5, 3));
        this.kaarten.add(14, new BeschavingskaartWorpMiddelen(4, "src/main/Resources/Kaarten/xHout_Hpoint.png", "",2, 3, 3));

        this.kaarten.add(15, new BeschavingskaartPunten(2, "src/main/Resources/Kaarten/Point_Fluit.jpg", "fluit", 0, 3));
        this.kaarten.add(16, new BeschavingskaartPunten(2, "src/main/Resources/Kaarten/Point_Hpoint.png", "",2, 3));
    }

    public List<Kaart> getKaarten() {
        return this.kaarten;
    }

    public void setKaarten(ArrayList<Kaart> kaarten) {
        this.kaarten = kaarten;
    }

    public boolean getPlaceable() {
        return this.isPlaceable;
    }

    public void setPlaceable(boolean isPlaceable) {
        this.isPlaceable = isPlaceable;
    }

    @Override
    public int getTurn() {
        return this.turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public boolean getPlaced() {
        return this.placed;
    }

    // Dit houdt bij of de speler als iets heeft geplaast tijdens de beurt.
    public void setPlaced(boolean placed) {
        this.placed = placed;
    }

    public PlayerModel getPlayer() {
        return this.player;
    }

    // Dit verandered wie er aan de beurt is.
    public void setPlayer(PlayerModel player) {
        this.player = player;
    }
}