package hsleiden.stenentijdperk.stenentijdperk.Helpers.Beschavingskaarten;

import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;

public class BeschavingskaartPunten extends Kaart {
    private int waarde;
    private String treasure;
    private int multiplier;

    public BeschavingskaartPunten(int kosten, String path, String treasure, int multiplier, int waarde) {
        super(kosten, path);
        this.treasure = treasure;
        this.multiplier = multiplier;
        this.waarde = waarde;
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
        // ontvang punten op de kaart
        player.increasePunten(waarde);

        if(!treasure.isEmpty()){
            player.addTreasure(treasure);
        }else{
            player.addMultiplier(multiplier, 1);
        }
        // Ontvang beschavingskaart (this)
        player.addKaarten(this);
    }
}
