package hsleiden.stenentijdperk.stenentijdperk.Helpers.Beschavingskaarten;

import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;

public class BeschavingskaartMiddelen extends Kaart {
    private int waarde;
    private int middel;
    private String treasure;
    private int multiplier;

    public BeschavingskaartMiddelen(int kosten, String path, String treasure, int waarde, int middel) {
        super(kosten, path);
        this.treasure = treasure;
        this.waarde = waarde;
        this.middel = middel;
    }
    public BeschavingskaartMiddelen(int kosten, String path,int multiplier, int waarde, int middel) {
        super(kosten, path);
        this.multiplier = multiplier;
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
        // ontvang resource wat op de kaart staat
        player.addResources(middel, waarde);

        if(treasure != null){
            player.addTreasure(treasure);
        }else{
            player.addMultiplier(multiplier, 1);
        }
        // Ontvang beschavingskaart
        player.addKaarten(this);
    }
}
