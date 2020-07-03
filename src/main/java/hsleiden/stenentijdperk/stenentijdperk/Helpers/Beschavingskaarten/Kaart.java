package hsleiden.stenentijdperk.stenentijdperk.Helpers.Beschavingskaarten;

import hsleiden.stenentijdperk.stenentijdperk.Helpers.Dobbelsteen;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Resource;
import hsleiden.stenentijdperk.stenentijdperk.Interfaces.Status;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;

public class Kaart implements Status {
    private String type;
    private String treasure;
    private int multiplier;
    private int waarde;
    private int middel;
    private boolean status;
    private String path;

    public Kaart(){
        
    }
    public Kaart(String type,String path, String treasure, int multiplier, int waarde, int middel) {
        this.type = type;
        this.treasure = treasure;
        this.multiplier = multiplier;
        this.waarde = waarde;
        this.middel = middel;
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    public void uitvoerenActie(PlayerModel player) {
        if(type.equals("middelen")){
            player.addResources(middel, waarde);
        } else if(type.equals("worp")){
            Dobbelsteen dobbel = new Dobbelsteen(2);
            dobbel.worp();
            dobbel.berekenTotaal();
            player.addResources(middel, dobbel.getTotaal() / waarde);
        } else if(type.equals("punten")){
            player.increasePunten(waarde);
        }

        if (!treasure.isEmpty()) {
            player.addTreasure(treasure);
        } else{
            player.addMultiplier(multiplier, 1);
        }
        // ontvang beschavingskaart
        player.addKaarten(this);
    }

    @Override
    public boolean getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(boolean status) {
        this.status = status;
    }

}
