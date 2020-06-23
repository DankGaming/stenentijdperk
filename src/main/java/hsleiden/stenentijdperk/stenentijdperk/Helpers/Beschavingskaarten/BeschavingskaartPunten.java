package hsleiden.stenentijdperk.stenentijdperk.Helpers.Beschavingskaarten;

import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;

public class BeschavingskaartPunten extends Kaart {
    private int waarde;

    public BeschavingskaartPunten(int kosten, String path, int waarde) {
        super(kosten, path);
        this.waarde = waarde;
    }


    public void uitvoerenActie(PlayerModel player) {
        //actie uitvoeren
        // verhoogpunten
    }
}
