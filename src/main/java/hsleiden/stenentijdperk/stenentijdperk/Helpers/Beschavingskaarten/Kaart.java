package hsleiden.stenentijdperk.stenentijdperk.Helpers.Beschavingskaarten;

import hsleiden.stenentijdperk.stenentijdperk.Helpers.Resource;
import hsleiden.stenentijdperk.stenentijdperk.Interfaces.Status;

public class Kaart implements Status {
    //wat kost de kaart
    private int kosten;
    private Resource tool;
    private Resource graan;
    private String treasure;
    private boolean status;
    private final String path;

    public Kaart(int kosten, String path) {
        this.path = path;
        this.kosten = kosten;
        this.status = false;
    }

    public String getPath() {
        return this.path;
    }

    public void uitvoerenActie(PlayerModel player) {
        if(type.equals("middelen")){
            player.addResources(middel, waarde);
        }
        else if(type.equals("worp")){
            Dobbelsteen dobbel = new Dobbelsteen(2);
            dobbel.worp();
            dobbel.berekenTotaal();
            player.addResources(middel, dobbel.getTotaal() / waarde);
        }
        else if(type.equals("punten")){
            player.increasePunten(waarde);
        }

        if(!treasure.isEmpty()){
            player.addTreasure(treasure);
        }else{
            player.addMultiplier(multiplier, 1);
        }
        // ontvang beschavingskaart
        player.addKaarten(this);
    }
  
    public int getPunten() {
        return this.kosten;
    }

    public Resource getTool() {
        return this.tool;
    }

    public Resource getGraan() {
        return this.graan;
    }

    public String getTreasure() {
        return this.treasure;
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
