package hsleiden.stenentijdperk.stenentijdperk.Controllers;

import hsleiden.stenentijdperk.stenentijdperk.Models.LobbyModel;
import hsleiden.stenentijdperk.stenentijdperk.observers.LobbyObserver;

public class LobbyController {
    int lobbyId;
    LobbyModel lobbyModel;

    public LobbyController() {
        this.lobbyModel = new LobbyModel();
    }

    public void setLobbyId(int id) {
        this.lobbyId = id;
        lobbyModel.changeLobbyId(id);
    }

    public void joinLobby() {
        if(this.lobbyId > 0)
            System.out.println("Player selected lobby " + this.lobbyId);
    }

    public void registerObserver(LobbyObserver lo) {
        this.lobbyModel.register(lo);
    }
}
