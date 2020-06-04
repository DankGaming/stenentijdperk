package hsleiden.stenentijdperk.stenentijdperk.Controllers;

import hsleiden.stenentijdperk.stenentijdperk.Managers.ViewManager;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;
import hsleiden.stenentijdperk.stenentijdperk.SystemInfo;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LoginController {
	public void onVerderButtonClick(String naam) {
		PlayerModel playerModel = generatePlayer(naam);
		ViewManager.loadLobbyView(playerModel);
	}

	public PlayerModel generatePlayer(String naam) {
		PlayerModel playerModel = new PlayerModel();
		playerModel.setNaam(naam);
		return playerModel;
	}
}
