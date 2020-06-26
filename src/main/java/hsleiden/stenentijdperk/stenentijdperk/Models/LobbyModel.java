package hsleiden.stenentijdperk.stenentijdperk.Models;

import hsleiden.stenentijdperk.stenentijdperk.Controllers.FirebaseController;
import hsleiden.stenentijdperk.stenentijdperk.observers.LobbyObservable;
import hsleiden.stenentijdperk.stenentijdperk.observers.LobbyObserver;
import java.util.ArrayList;

public class LobbyModel implements LobbyObservable {
    private int lobbyId;
    private ArrayList<PlayerModel> players;

    ArrayList<LobbyObserver> observers = new ArrayList<>();

    public LobbyModel() { }

    public void changeLobbyId(int id) {
        this.lobbyId = id;
        notifyAllObservers();
    }

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

    @Override
    public int getId() {
        return this.lobbyId;
    }

    @Override
    public ArrayList<PlayerModel> getPlayers(int id) {
        return FirebaseController.getPlayersInLobby(id);
    }
}
