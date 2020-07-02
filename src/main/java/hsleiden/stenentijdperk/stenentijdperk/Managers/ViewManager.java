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

    /**
     * Load the login view.
     * This function loads the login view.
     *
     * @see LoginView
     * @author Joost de Jager
     */
    public static void loadLoginView() {
        closeView();
        currentView = new LoginView().setScene();
        showView(800, 800, "Login");
    }

    /**
     * Load the lobby view.
     * This function loads the lobby view.
     *
     * @param playerModel We use the playermodel to display the name of the player
     * @see LobbyView
     * @author Joost de Jager
     */
    public static void loadLobbyView(PlayerModel playerModel) {
        closeView();
        currentView = new LobbyView(playerModel).setScene();

        showView(800, 800, "Lobby");
    }

    /**
     * Load the board view.
     * This function loads the board view.
     *
     * @see BoardView
     * @author Joost de Jager, Bob van Velzen
     */
    public static void loadBoardView() {
        closeView();
        isRunning = true;
        BoardView boardView = new BoardView();
        currentView = boardView.setScene();
        showView(1200, 800, "Board", boardView.getPlayers());
    }

    /**
     * Load the GameOver view.
     * This function loads the GameOver view.
     *
     * @param playerModels We use the playermodels to generate a list of who won the game
     * @see GameOverView
     * @author Joost de Jager
     */
    public static void loadGameOverView(ArrayList<PlayerModel> playerModels) {
        // Close the main view
        closeView();

        // Close the picker view
        closePickerView();

        // Close the popup windows
        closePopupWindow();

        currentView = new GameOverView(playerModels).setScene();
        showView(650, 400, "Game over");
    }

    /**
     * Load the spelregels view.
     * This function loads the spelregels view.
     *
     * @see SpelregelView
     * @author Joost de Jager
     */
    public static void loadSpelregelView() {
        ViewManager.loadPopupWindow(new SpelregelView().setScene());
    }

    /**
     * Load the picker view.
     * This function loads the picker view.
     *
     * @see PickerView
     * @author Joost de Jager
     */
    public static void loadPickerView(ArrayList<PlayerModel> playerModels) {
        closePickerView();
        PickerView pickerView = new PickerView(playerModels);
        pickerStage = pickerView.setScene();
        if (isRunning) {
            showPickerView();
        }
    }

    /**
     * Load a popup window.
     * This function loads a popup window.
     *
     * @param tableauView the gridpane is rendered
     * @author Joost de Jager
     */
    public static void loadPopupWindow(GridPane tableauView) {
        closePopupWindow();
        currentPopupView = tableauView;
        openPopupWindow();
    }

    /**
     * Load a popup window.
     * This function loads a popup window.
     *
     * @param vBox The vbox is being rendered
     * @author Joost de Jager
     */
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

    /**
     * Creates a stage
     * This function creates a stage from a view.
     *
     * @param width the width of the window
     * @param height the height of the window
     * @param title the title of the window
     * @author Joost de Jager
     */
    public static void createStageFromView(int width, int height, String title) {
        currentStage = new Stage();
        if (currentView != null)
            currentStage.setScene(new Scene(currentView, width, height));
        currentStage.setTitle(title);
    }

    /**
     * Shows a stage.
     * This function shows a stage.
     *
     * @param width the width of the window
     * @param height the height of the window
     * @param title the title of the window
     * @author Joost de Jager
     */
    public static void showView(int width, int height, String title) {
        createStageFromView(width, height, title);
        currentStage.setOnCloseRequest(event -> {
            closePickerView();
            closePopupWindow();
        });
        if (currentStage != null)
            currentStage.show();
    }

    /**
     * Shows a stage.
     * This function shows a stage.
     *
     * @param width the width of the window
     * @param height the height of the window
     * @param title the title of the window
     * @param playerModels for generating the tableaus
     * @author Joost de Jager
     */
    public static void showView(int width, int height, String title, ArrayList<PlayerModel> playerModels) {
        createStageFromView(width, height, title);
        currentStage.setOnCloseRequest(event -> {
            isRunning = false;
            closePickerView();
            closeResourceWindow();
        });
        if (currentStage != null)
            currentStage.show();
            currentStage.setResizable(false);
        ViewManager.loadPickerView(playerModels);
    }

    /**
     * Closes a stage.
     * This function closes a stage.
     *
     * @author Joost de Jager
     */
    public static void closeView() {
        if (currentStage != null)
            currentStage.close();
    }

    /**
     * Closes a stage.
     * This function closes the pickerview.
     *
     * @author Joost de Jager
     */
    public static void closePickerView() {
        if (pickerStage != null)
            pickerStage.close();
    }

    /**
     * Shows a stage.
     * This function shows the pickerview.
     *
     * @author Joost de Jager
     */
    public static void showPickerView() {
        if (pickerStage != null)
            pickerStage.show();
    }

    /**
     * Creates a stage.
     * This function creates a stage from a gridpane.
     *
     * @param width the width of the window
     * @param height the height of the window
     * @param title the title of the window
     * @author Joost de Jager
     */
    public static void createPopupFromView(int width, int height, String title) {
        currentPopupStage = new Stage();
        if (currentPopupView != null)
            currentPopupStage.setScene(new Scene(currentPopupView, width, height));
        currentPopupStage.setTitle(title);
        currentPopupStage.setResizable(false);
    }

    /**
     * Shows a stage.
     * This function shows a popup window.
     *
     * @author Joost de Jager
     */
    public static void openPopupWindow() {
        createPopupFromView(680, 460, "tableau");
        if (currentPopupStage != null)
            currentPopupStage.show();
    }

    /**
     * Creates a stage.
     * This function creates a stage from a vbox.
     *
     * @param width the width of the window
     * @param height the height of the window
     * @param title the title of the window
     * @author Joost de Jager
     */
    public static void createPopupFromVbox(int width, int height, String title) {
        currentPopupStage = new Stage();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(currentPopupVbox);
        if (currentPopupVbox != null)
            currentPopupStage.setScene(new Scene(scrollPane, width, height));
        currentPopupStage.setTitle(title);
        currentPopupStage.setResizable(false);
    }

    /**
     * Shows a stage.
     * This function shows a popup window.
     *
     * @author Joost de Jager
     */
    public static void openPopupVbox(int width, int height, String title) {
        createPopupFromVbox(width, height, title);
        if (currentPopupStage != null)
            currentPopupStage.show();
    }

    /**
     * Closes a stage.
     * This function closes a popup window.
     *
     * @author Joost de Jager
     */
    public static void closePopupWindow() {
        if (currentPopupStage != null)
            currentPopupStage.close();
    }
}
