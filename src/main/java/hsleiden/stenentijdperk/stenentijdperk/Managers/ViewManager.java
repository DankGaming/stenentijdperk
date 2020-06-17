package hsleiden.stenentijdperk.stenentijdperk.Managers;

import hsleiden.stenentijdperk.stenentijdperk.Controllers.FirebaseController;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;
import hsleiden.stenentijdperk.stenentijdperk.Models.TableauModel;
import hsleiden.stenentijdperk.stenentijdperk.Views.BoardView;
import hsleiden.stenentijdperk.stenentijdperk.Views.LobbyView;
import hsleiden.stenentijdperk.stenentijdperk.Views.LoginView;
import hsleiden.stenentijdperk.stenentijdperk.Views.TableauView;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ViewManager {
    // The current stage and view.
    private static Stage currentStage;
    private static GridPane currentView;
    private static FirebaseController firebaseController;

    // tableau popup
    private static Stage currentPopupStage;
    private static GridPane currentPopupView;

    public static void setFirebase(FirebaseController f) {
        firebaseController = f;
    }
    // The load view functions. Call these in your controllers and views.

    public static void loadLoginView() {
        closeView();
        currentView = new LoginView().setScene();
        showView(800, 800, "Login");
    }

    public static void loadLobbyView(PlayerModel playerModel) {
        closeView();
        currentView = new LobbyView(playerModel).setScene();

        showView(800, 800, "Lobby");
    }

    public static void loadBoardView() {
        closeView();
        currentView = new BoardView().setScene();
        showView(1200, 800, "Board");
    }

    // This function creates a stage from a gridpane.
    public static void createStageFromView(int width, int height, String title) {
        currentStage = new Stage();
        if (currentView != null)
            currentStage.setScene(new Scene(currentView, width, height));
        currentStage.setTitle(title);
    }

    // This function shows a view.
    public static void showView(int width, int height, String title) {
        createStageFromView(1200, 800, "Login");
        if (currentStage != null)
            currentStage.show();
    }

    // And this function closes a view.
    public static void closeView() {
        if (currentStage != null)
            currentStage.close();
    }

    // This function creates a stage from a gridpane.
    public static void createPopupFromView(int width, int height, String title) {
        currentPopupStage = new Stage();
        if(currentPopupView != null)
            currentPopupStage.setScene(new Scene(currentPopupView, width, height));
        currentPopupStage.setTitle(title);
    }

    // Function that opens popup window
    public static void openPopupWindow() {
        createPopupFromView(680, 460, "tableau");
        if(currentPopupStage != null)
            currentPopupStage.show();
    }

    // Function that closes popup window
    public static void closePopupWindow() {
        if(currentPopupStage != null)
            currentPopupStage.close();
    }
}
