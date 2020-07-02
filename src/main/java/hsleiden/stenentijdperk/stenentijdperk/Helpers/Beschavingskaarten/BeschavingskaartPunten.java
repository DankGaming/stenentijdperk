package hsleiden.stenentijdperk.stenentijdperk.Helpers.Beschavingskaarten;

import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;

public class BeschavingskaartPunten extends Kaart {
    private int waarde;

<<<<<<< HEAD
    public BeschavingskaartPunten(){}
=======
>>>>>>> master
    public BeschavingskaartPunten(int kosten, String path, int waarde) {
        super(kosten, path);
        this.waarde = waarde;
    }

    public int getWaarde() {
        return waarde;
    }

    public String getPath() {
        return super.getPath();
    }

    public int getPunten() {
<<<<<<< HEAD
        return super.getKosten();
=======
        return super.getPunten();
>>>>>>> master
    }

    public void uitvoerenActie(PlayerModel player) {
        // ontvang punten op de kaart
        player.setPunten(waarde);

        // Ontvang beschavingskaart (this)
        player.addKaarten(this);
    }
}
