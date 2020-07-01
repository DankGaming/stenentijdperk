package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.Managers.ViewManager;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Collections;

public class GameOverView {
    private GridPane view;
    private VBox vBox;
    private ArrayList<PlayerModel> playerModels;
    private boolean hasWon = true;

    public GameOverView(ArrayList<PlayerModel> playerModels) {
        this.playerModels = playerModels;
        setupPane();
    }

    public GridPane setScene() {
        return this.view;
    }

    private void setupPane() {
        this.view = new GridPane();
        this.vBox = new VBox();

        int amountOfRows = 30;
        int amountOfColumns = 50;

        for (int i = 0; i < amountOfColumns; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(100 / amountOfColumns);
            this.view.getColumnConstraints().add(columnConstraints);
        }
        for (int j = 0; j < amountOfRows; j++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(100 / amountOfRows);
            this.view.getRowConstraints().add(rowConstraints);
        }

        generateLabels();

        Button endGameButton = new Button("Stop met spelen");
        endGameButton.setMinSize(250, 60);
        endGameButton.setMaxSize(250, 60);
        endGameButton.setStyle("-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 2em;");

        endGameButton.setOnMouseClicked(event -> {
            ViewManager.closeView();
        });

        GridPane.setConstraints(endGameButton, 5, 20, 30, 30);

        Button restartButton = new Button("Speel opnieuw");
        restartButton.setMinSize(250, 60);
        restartButton.setMaxSize(250, 60);
        restartButton.setStyle("-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 2em;");
        GridPane.setConstraints(restartButton, 25, 20, 30, 30);

        GridPane.setConstraints(this.vBox, 0, 0, 50, 20);
        this.view.getChildren().addAll(this.vBox, endGameButton, restartButton);
    }

    private void generateLabels() {
        Collections.sort(this.playerModels);
        for(PlayerModel playerModel : playerModels) {
            Label label;
            if(hasWon) {
                hasWon = false;
                label = new Label(playerModel.getNaam() + " heeft gewonnen met " + playerModel.getPunten() + " punten");
                label.setStyle("-fx-font-size: 30");
            } else {
                label = new Label(playerModel.getNaam() + " had " + playerModel.getPunten() + " punten.");
                label.setStyle("-fx-font-size: 15");
            }
            this.vBox.getChildren().add(label);
        }
    }
}
