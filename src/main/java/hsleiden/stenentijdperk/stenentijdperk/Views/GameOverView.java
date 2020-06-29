package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
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
        generateLabels();

        GridPane.setConstraints(this.vBox, 0, 0, 20, 20);
        this.view.getChildren().add(this.vBox);
    }

    private void generateLabels() {
        Collections.sort(this.playerModels);
        for(PlayerModel playerModel : playerModels) {
            Label label = null;
            if(hasWon) {
                hasWon = false;
                label = new Label(playerModel.getNaam() + " heeft gewonnen met " + playerModel.getPunten() + " punten");
            } else {
                label = new Label(playerModel.getNaam() + " had " + playerModel.getPunten() + " punten.");
            }
            this.vBox.getChildren().add(label);
        }
    }
}
