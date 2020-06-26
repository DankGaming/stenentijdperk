package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.Managers.ViewManager;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class PickerView {
    private VBox vBox;
    private ScrollPane scrollPane;
    private ArrayList<PlayerModel> playerModels;
    private Stage view;

    public PickerView(ArrayList<PlayerModel> playerModels) {
        this.playerModels = playerModels;
        setupPane();
        generateButtons(playerModels);
    }

    public void createPane() {
        this.scrollPane = new ScrollPane();
        this.scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        this.scrollPane.setStyle("-fx-background-color: black");
        this.scrollPane.setContent(this.vBox);
    }

    public void createStage() {
        this.view = new Stage();
        this.view.setTitle("Picker");
        this.view.setResizable(false);
        this.view.setScene(new Scene(this.scrollPane, 238, 600));
        this.view.setOnCloseRequest(event -> {
            ViewManager.loadPickerView(playerModels);
        });
    }

    public Stage setScene() {
        createPane();
        createStage();
        return this.view;
    }

    public void setupPane() {
        this.vBox = new VBox();
    }

    // Functions that create buttons based on playermodels
    public void generateButtons(ArrayList<PlayerModel> playerModels) {
        Button button = new Button("Spelregels");
        button.setMinSize(237, 43);
        button.setMaxSize(237, 43);
        button.setStyle("-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 2em;");
        button.setOnMouseClicked(event -> {
            ViewManager.loadSpelregelView();
        });
        this.vBox.getChildren().add(button);
        for(PlayerModel playerModel : playerModels) {
            Button playerButton = new Button(playerModel.getNaam());
            playerButton.setMinSize(237, 43);
            playerButton.setMaxSize(237, 43);
            playerButton.setStyle("-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 2em;");
            playerButton.setOnMouseClicked(event -> {
                ViewManager.loadPopupWindow(new TableauView(playerModel).setScene());
            });
            this.vBox.getChildren().add(playerButton);
        }
    }
}
