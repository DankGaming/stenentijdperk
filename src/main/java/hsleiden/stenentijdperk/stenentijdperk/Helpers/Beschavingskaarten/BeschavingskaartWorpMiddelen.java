package hsleiden.stenentijdperk.stenentijdperk.Helpers.Beschavingskaarten;

import hsleiden.stenentijdperk.stenentijdperk.Helpers.Dobbelsteen;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;

import java.util.ArrayList;

public class BeschavingskaartWorpMiddelen extends Kaart{
    private int waarde;
    private int middel;

    public BeschavingskaartWorpMiddelen(int kosten, String path, int waarde, int middel) {
        super(kosten, path);
        this.waarde = waarde;
        this.middel = middel;
    }

    public int getWaarde() {
        return waarde;
    }

    public String getPath() {
        return super.getPath();
    }

    public int getPunten() {
        return super.getPunten();
    }

    public void uitvoerenActie(PlayerModel player) {
        Dobbelsteen dobbel = new Dobbelsteen(2);
        dobbel.worp();
        dobbel.berekenTotaal();

        player.addResources(middel, dobbel.getTotaal() / waarde);

    }
}
