package hsleiden.stenentijdperk.stenentijdperk.Helpers;

public class Resource {
    private String naam;
    private int waarde;
    private int maxCap;
    private int hoeveelheid;
    private int villagers;


    public Resource(String naam, int hoeveelheid, int waarde, int maxCap) {
        this.naam = naam;
        this.hoeveelheid = hoeveelheid;
        this.waarde = waarde;
        this.maxCap = maxCap;
        this.villagers = 0;
    }

    public void reduceHoeveelheid(int amount) {
        this.hoeveelheid -= amount;
    }

    public void addHoeveelheid(int amount) {
        this.hoeveelheid += amount;
    }

    public void increaseVillager(int villagers) {
        this.villagers += villagers;
    }

    public void reduceVillager(int villagers) {
        this.villagers -= villagers;
    }

    public int getWaarde() {
        return this.waarde;
    }

    public int getMaxCap() {
        return this.maxCap;
    }

    public int getVillagers() {
        return this.villagers;
    }

    public void setVillagers(int villagers) {
        this.villagers = villagers;
    }

    public String getNaam() {
        return this.naam;
    }

    public int getHoeveelheid() {
        return this.hoeveelheid;
    }

    public void setHoeveelheid(int amount) {
        this.hoeveelheid = amount;
    }
}
