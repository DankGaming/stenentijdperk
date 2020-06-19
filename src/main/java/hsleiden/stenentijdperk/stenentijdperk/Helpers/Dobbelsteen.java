package hsleiden.stenentijdperk.stenentijdperk.Helpers;

import java.util.ArrayList;

public class Dobbelsteen {
    private int ogen;
    private int totaalworp;
    private ArrayList<Integer> worpen = new ArrayList<Integer>();
    private int dobbelstenen;
    

    public Dobbelsteen(int dobbelstenen) {
        this.ogen = 0;
        this.dobbelstenen = dobbelstenen;
    }

    public void worp() {
        for (int i = 0; i < dobbelstenen; i++){
            ogen = (int) (Math.random() * 6) + 1;
            worpen.add(ogen);
        }
    }

    public int getTotaal(){
        return totaalworp;
    }

    public void berekenTotaal() {
        totaalworp = 0;
        for (int worp : worpen) {
            totaalworp += worp;
        }
    }
}
