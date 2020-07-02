package hsleiden.stenentijdperk.stenentijdperk;

import hsleiden.stenentijdperk.stenentijdperk.Controllers.FirebaseController;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Test;
import hsleiden.stenentijdperk.stenentijdperk.Managers.ViewManager;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        Test test = new Test();
        FirebaseController.initializeFirebaseApp(test);
        ViewManager.loadLoginView();
    }

    public static void main(String[] args) {
        launch();
    }
}