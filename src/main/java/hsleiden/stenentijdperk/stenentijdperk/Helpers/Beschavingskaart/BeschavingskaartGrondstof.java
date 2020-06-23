package hsleiden.stenentijdperk.stenentijdperk.Helpers.Beschavingskaart;

import hsleiden.stenentijdperk.stenentijdperk.Helpers.Resource;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;

public class BeschavingskaartGrondstof extends Kaart{
    private int waarde;
    private Resource resource;

    public BeschavingskaartGrondstof(int punten, String path, int waarde, Resource resource) {
        super(punten, path);
        this.waarde = waarde;
        this.resource = resource;
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
        // Ontvang resource
        // Ontvang beschavingskaart
    }
}
