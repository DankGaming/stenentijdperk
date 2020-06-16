package hsleiden.stenentijdperk.stenentijdperk.Controllers;

import hsleiden.stenentijdperk.stenentijdperk.Models.LobbyModel;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;
import hsleiden.stenentijdperk.stenentijdperk.observers.LobbyObserver;

public class LobbyController {
    int lobbyId;
    LobbyModel lobbyModel;
    PlayerModel p;

    public LobbyController() {
        this.lobbyModel = new LobbyModel();
    }

    public void setLobbyId(int id, PlayerModel player) {
        this.lobbyId = id;
        this.p = player;
        lobbyModel.changeLobbyId(id);
        FirebaseController.listenForUpdates(String.valueOf(id));
    }

    public void joinLobby() {
        if(this.lobbyId > 0)
            FirebaseController.updateDocument(String.valueOf(lobbyId), "user1", p.getNaam());
            System.out.println("Player selected lobby " + this.lobbyId);
    }

    public void registerObserver(LobbyObserver lo) {
        this.lobbyModel.register(lo);
    }
}
