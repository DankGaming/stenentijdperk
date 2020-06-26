package hsleiden.stenentijdperk.stenentijdperk.observers;

import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;

import java.util.ArrayList;

public interface LobbyObservable {
    void register(LobbyObserver lo);

    void notifyAllObservers();

    int getId();

    ArrayList<PlayerModel> getPlayers(int id);
}
