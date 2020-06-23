package hsleiden.stenentijdperk.stenentijdperk.Helpers.Beschavingskaarten;

import hsleiden.stenentijdperk.stenentijdperk.Helpers.Enums.Middel;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Resource;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;

public class BeschavingskaartMiddelen extends Kaart{
    private int waarde;
    private Middel middel;

    public BeschavingskaartMiddelen(int kosten, String path, int waarde, Middel middel) {
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
    @Override
    public int getPunten() {
        return super.getPunten();
    }

    public void uitvoerenActie(PlayerModel player) {
        // Ontvang resource (middel, waarde)
        // Ontvang beschavingskaart (this)
    }
}
