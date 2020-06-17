package hsleiden.stenentijdperk.stenentijdperk.Models;

import hsleiden.stenentijdperk.stenentijdperk.observers.LobbyObserver;
import hsleiden.stenentijdperk.stenentijdperk.observers.TableauObserver;

public class PlayerModel {
    private String naam;
    private TableauModel tableauModal;

    public void setNaam(String naam) {
       this.naam = naam;
    }

    public String getNaam() {
        return this.naam;
    }


    public void registerObserver(TableauObserver to) {
        this.tableauModal.register(to);
    }
}
