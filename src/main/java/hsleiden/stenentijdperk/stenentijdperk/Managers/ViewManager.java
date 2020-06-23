package hsleiden.stenentijdperk.stenentijdperk.Managers;

import hsleiden.stenentijdperk.stenentijdperk.Controllers.FirebaseController;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;
import hsleiden.stenentijdperk.stenentijdperk.Views.*;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewManager {
    // The current stage and view.
    private static Stage currentStage;
    private static GridPane currentView;
    private static FirebaseController firebaseController;

    // tableau popup
    private static Stage currentPopupStage;
    private static GridPane currentPopupView;
    private static VBox currentPopupVbox;

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

    public static void loadSpelregelView() {
        ViewManager.loadPopupWindow(new SpelregelView().setScene());
    }

    // Popup window functions

    public static void loadPopupWindow(GridPane tableauView) {
        closePopupWindow();
        currentPopupView = tableauView;
        openPopupWindow();
    }

    public static void loadPopupWindow(VBox vBox) {
        closePopupWindow();
        currentPopupVbox = vBox;
        openPopupVbox(800, 600, "Spelregels");
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
        createStageFromView(width, height, title);
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
        currentPopupStage.setResizable(false);
    }

    // Function that opens popup window
    public static void openPopupWindow() {
        createPopupFromView(680, 460, "tableau");
        if(currentPopupStage != null)
            currentPopupStage.show();
    }

    public static void createPopupFromVbox(int width, int height, String title) {
        currentPopupStage = new Stage();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(currentPopupVbox);
        if(currentPopupVbox != null)
            currentPopupStage.setScene(new Scene(scrollPane, width, height));
        currentPopupStage.setTitle(title);
        currentPopupStage.setResizable(false);
    }

    public static void openPopupVbox(int width, int height, String title) {
        createPopupFromVbox(width, height, title);
        if(currentPopupStage != null)
            currentPopupStage.show();
    }

    // Function that closes popup window
    public static void closePopupWindow() {
        if(currentPopupStage != null)
            currentPopupStage.close();
    }
}
