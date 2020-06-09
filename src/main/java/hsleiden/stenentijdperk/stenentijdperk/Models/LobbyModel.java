package hsleiden.stenentijdperk.stenentijdperk.Models;

import hsleiden.stenentijdperk.stenentijdperk.observers.LobbyObservable;
import hsleiden.stenentijdperk.stenentijdperk.observers.LobbyObserver;

import java.util.ArrayList;

public class LobbyModel implements LobbyObservable {
    ArrayList<LobbyObserver> observers = new ArrayList<>();

    @Override
    public void register(LobbyObserver lo) {
        this.observers.add(lo);
    }

    @Override
    public void notifyAllObservers() {
        for(LobbyObserver lo : observers) {
            lo.update(this);
        }
    }
}
