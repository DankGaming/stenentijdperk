package hsleiden.stenentijdperk.stenentijdperk.Controllers;

import hsleiden.stenentijdperk.stenentijdperk.observers.LobbyObserver;

public class LobbyController {
    int lobbyId;

    public LobbyController() {
    }

    public void setLobbyId(int id) {
        this.lobbyId = id;
    }

    public void joinLobby() {
        if(this.lobbyId > 0)
            System.out.println("Player selected lobby " + this.lobbyId);
    }

    public void registerObserver(LobbyObserver lo) {

    }
}
