package hsleiden.stenentijdperk.stenentijdperk.Helpers;

import java.util.ArrayList;
import java.util.List;

public class StaticHut {
    private int punten;
    private List<Integer> resourceKost = new ArrayList<Integer>();
    private String path;
    private boolean status;

    public StaticHut(int punten, /* List<Integer> kost, */ String path) {
        this.punten = punten;
        // this.resourceKost = kost;
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    public int getPunten() {
        return this.punten;
    }
}