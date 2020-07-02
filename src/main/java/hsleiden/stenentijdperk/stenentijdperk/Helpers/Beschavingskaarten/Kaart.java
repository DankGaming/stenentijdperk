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
    private String path;

    public Kaart(){}
    public Kaart(int kosten, String path) {
        this.path = path;
        this.kosten = kosten;
        this.status = false;
    }

    public String getPath() {
        return this.path;
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
