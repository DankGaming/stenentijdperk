package hsleiden.stenentijdperk.stenentijdperk;

import hsleiden.stenentijdperk.stenentijdperk.Controllers.FirebaseController;
import hsleiden.stenentijdperk.stenentijdperk.Controllers.LobbyController;
import hsleiden.stenentijdperk.stenentijdperk.Controllers.LoginController;
import hsleiden.stenentijdperk.stenentijdperk.Managers.ViewManager;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;
import hsleiden.stenentijdperk.stenentijdperk.Views.LobbyView;
import hsleiden.stenentijdperk.stenentijdperk.Views.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
	private Stage primaryStage;
    @Override
    public void start(Stage primaryStage) {
        ViewManager.loadLoginView();
        FirebaseController firebaseController = new FirebaseController();
        firebaseController.initializeFirebaseApp();
        firebaseController.listenForUpdates("lobby");
    }

    public static void main(String[] args) {
        launch();
    }

}