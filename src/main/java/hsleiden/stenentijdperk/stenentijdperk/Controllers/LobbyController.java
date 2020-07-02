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
        FirebaseController.listenForLobbyUpdates(String.valueOf(id));
        FirebaseController.listenForPlayerUpdates(String.valueOf(id));
    }

    public void joinLobby() {
        if (this.lobbyId > 0)
            if (FirebaseController.getAmountofPlayersInLobby(this.lobbyId) < 4) {
                if (FirebaseController.getAmountofPlayersInLobby(this.lobbyId) == 0) {
                    FirebaseController.setLobbyLeader(this.lobbyId, this.p);
                }
                FirebaseController.addPlayers(this.lobbyId, this.p, setPlayerNumber());
                FirebaseController.getPlayersInLobby(this.lobbyId);
                System.out.println("Player selected lobby " + this.lobbyId);
            }
    }

    public void registerObserver(LobbyObserver lo) {
        this.lobbyModel.register(lo);
    }

    private String setPlayerNumber() {
        int players = FirebaseController.getAmountofPlayersInLobby(this.lobbyId);
        String playerNumber = "";
        switch (players) {
            case 0:
                playerNumber = "speler1";
                break;
            case 1:
                playerNumber = "speler2";
                break;
            case 2:
                playerNumber = "speler3";
                break;
            case 3:
                playerNumber = "speler4";
                break;
        }
        return playerNumber;
    }
}
