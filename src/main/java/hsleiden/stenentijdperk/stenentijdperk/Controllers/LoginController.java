package hsleiden.stenentijdperk.stenentijdperk.Controllers;

import hsleiden.stenentijdperk.stenentijdperk.Managers.ViewManager;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;

public class LoginController {
    public void onVerderButtonClick(String naam) {
        PlayerModel playerModel = generatePlayer(naam);
        ViewManager.loadLobbyView(playerModel);
    }

    public PlayerModel generatePlayer(String naam) {
        PlayerModel playerModel = new PlayerModel(naam);
        return playerModel;
    }
}
