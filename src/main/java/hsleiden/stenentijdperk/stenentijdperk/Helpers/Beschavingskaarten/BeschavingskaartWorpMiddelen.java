package hsleiden.stenentijdperk.stenentijdperk.Helpers.Beschavingskaarten;

import hsleiden.stenentijdperk.stenentijdperk.Helpers.Dobbelsteen;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;

public class BeschavingskaartWorpMiddelen extends Kaart {
    private int waarde;
    private int middel;
    private String treasure;
    private int multiplier;

    public BeschavingskaartWorpMiddelen(int kosten, String path, String treasure, int waarde, int middel) {
        super(kosten, path);
        this.treasure = treasure;
        this.waarde = waarde;
        this.middel = middel;
    }
    public BeschavingskaartWorpMiddelen(int kosten, String path, int multiplier, int waarde, int middel) {
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
        // uitvoeren actie
        Dobbelsteen dobbel = new Dobbelsteen(2);
        dobbel.worp();
        dobbel.berekenTotaal();

        player.addResources(middel, dobbel.getTotaal() / waarde);

        if(treasure != null){
            player.addTreasure(treasure);
        }else{
            player.addMultiplier(multiplier, 1);
        }
        // ontvang beschavingskaart
        player.addKaarten(this);
    }
}
