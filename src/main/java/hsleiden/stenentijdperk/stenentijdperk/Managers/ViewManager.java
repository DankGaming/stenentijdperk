package hsleiden.stenentijdperk.stenentijdperk.Managers;

import hsleiden.stenentijdperk.stenentijdperk.Controllers.FirebaseController;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;
import hsleiden.stenentijdperk.stenentijdperk.Views.*;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ViewManager {
    // The current stage and view.
    private static Stage currentStage;
    private static GridPane currentView;
    private static FirebaseController firebaseController;

    // tableau popup
    private static Stage currentPopupStage;
    private static GridPane currentPopupView;
    private static VBox currentPopupVbox;

    //Resource popup
    private static Stage resourcePopupStage;
    private static GridPane resourcePopupView;

    // Picker view
    private static Stage pickerStage;
    private static boolean isRunning = false;

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
        isRunning = true;
        BoardView boardView = new BoardView();
        currentView = boardView.setScene();
        showView(1200, 800, "Board", boardView.getPlayers());
    }

    public static void loadSpelregelView() {
        ViewManager.loadPopupWindow(new SpelregelView().setScene());
    }

    public static void loadPickerView(ArrayList<PlayerModel> playerModels) {
        closePickerView();
        PickerView pickerView = new PickerView(playerModels);
        pickerStage = pickerView.setScene();
        if (isRunning) {
            showPickerView();
        }
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
    public static void loadPopupWindow(ResourceView resourceView){
        closeResourceWindow();
        resourcePopupView = resourceView.setScene();
        openResourceWindow();
    }
    public static void closeResourceWindow(){
        if(resourcePopupStage != null)
            resourcePopupStage.close();
    }

    public static void openResourceWindow(){
        createResourceFromView();
        resourcePopupStage.show();
    }

    public static void createResourceFromView() {
        resourcePopupStage = new Stage();
        if(resourcePopupView != null)
            resourcePopupStage.setScene(new Scene(resourcePopupView, 640, 640));
        resourcePopupStage.setTitle("Resources");
        resourcePopupStage.setResizable(false);
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
        currentStage.setOnCloseRequest(event -> {
            closePickerView();
        });
        if (currentStage != null)
            currentStage.show();
    }

    // And this function closes a view.
    public static void showView(int width, int height, String title, ArrayList<PlayerModel> playerModels) {
        createStageFromView(width, height, title);
        currentStage.setOnCloseRequest(event -> {
            isRunning = false;
            closePickerView();
        });
        if (currentStage != null)
            currentStage.show();
            currentStage.setResizable(false);
        ViewManager.loadPickerView(playerModels);
    }

    public static void closeView() {
        if (currentStage != null)
            currentStage.close();
    }

    // Picker view
    public static void closePickerView() {
        if (pickerStage != null)
            pickerStage.close();
    }

    public static void showPickerView() {
        if (pickerStage != null)
            pickerStage.show();
    }

    // This function creates a stage from a gridpane.
    public static void createPopupFromView(int width, int height, String title) {
        currentPopupStage = new Stage();
        if (currentPopupView != null)
            currentPopupStage.setScene(new Scene(currentPopupView, width, height));
        currentPopupStage.setTitle(title);
        currentPopupStage.setResizable(false);
    }

    // Function that opens popup window
    public static void openPopupWindow() {
        createPopupFromView(680, 460, "tableau");
        if (currentPopupStage != null)
            currentPopupStage.show();
    }

    public static void createPopupFromVbox(int width, int height, String title) {
        currentPopupStage = new Stage();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(currentPopupVbox);
        if (currentPopupVbox != null)
            currentPopupStage.setScene(new Scene(scrollPane, width, height));
        currentPopupStage.setTitle(title);
        currentPopupStage.setResizable(false);
    }

    public static void openPopupVbox(int width, int height, String title) {
        createPopupFromVbox(width, height, title);
        if (currentPopupStage != null)
            currentPopupStage.show();
    }

    // Function that closes popup window
    public static void closePopupWindow() {
        if (currentPopupStage != null)
            currentPopupStage.close();
    }
}
