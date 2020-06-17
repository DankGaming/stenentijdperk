package hsleiden.stenentijdperk.stenentijdperk.Helpers;


public class Dobbelsteen {
    private int ogen;
    private int totaalworp;
    private int[] dobbelstenen;

    public Dobbelsteen() {
        reset();
    }

    public void reset() {
        ogen = 0;
    }

    public void worp() {
        ogen = (int) (Math.random() * 6) + 1;
    }

    public int getOgen() {
        return ogen;
    }

    public int getTotaal(){
        return totaalworp;
    }

    public void berekenTotaal() {
        totaalworp = 0;
        for (int dobbelsteen : dobbelstenen) {
            totaalworp += getOgen();
        }
    }
}
