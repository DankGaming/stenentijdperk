package hsleiden.stenentijdperk.stenentijdperk.Helpers.Beschavingskaarten;

import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;

public class BeschavingskaartMiddelen extends Kaart {
    private int waarde;
    private int middel;

<<<<<<< HEAD
    public BeschavingskaartMiddelen(){}
=======
>>>>>>> master
    public BeschavingskaartMiddelen(int kosten, String path, int waarde, int middel) {
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
<<<<<<< HEAD
        return super.getKosten();
=======
        return super.getPunten();
>>>>>>> master
    }

    public void uitvoerenActie(PlayerModel player) {
        // ontvang resource wat op de kaart staat
        player.addResources(middel, waarde);

        // Ontvang beschavingskaart
        player.addKaarten(this);
    }
}
