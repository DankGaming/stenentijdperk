package hsleiden.stenentijdperk.stenentijdperk;

import hsleiden.stenentijdperk.stenentijdperk.Controllers.FirebaseController;
import hsleiden.stenentijdperk.stenentijdperk.Managers.ViewManager;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;
import hsleiden.stenentijdperk.stenentijdperk.Views.LobbyView;
import hsleiden.stenentijdperk.stenentijdperk.Views.LoginView;
import hsleiden.stenentijdperk.stenentijdperk.Views.TableauView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        FirebaseController.initializeFirebaseApp();
        ViewManager.loadLoginView();
    }

    public static void main(String[] args) {
        launch();
    }

}