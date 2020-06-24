package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.Managers.ViewManager;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

// Constraints for pickerview
// GridPane.setConstraints(pickerView, 2, 2, 15, 11);

public class PickerView {
    private VBox vBox;
    private ScrollPane view;

    public PickerView() {
        setupPane();
    }

    public void createPane() {
        this.view = new ScrollPane();
        this.view.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        this.view.setStyle("-fx-background-color: black");
        this.view.setContent(this.vBox);
    }

    public ScrollPane setScene() {
        createPane();
        return this.view;
    }

    public void setupPane() {
        this.vBox = new VBox();
    }

    // Functions that create buttons based on playermodels

    public void generateButtons(ArrayList<PlayerModel> playerModels) {
        for(PlayerModel playerModel : playerModels) {
            Button button = new Button(playerModel.getNaam());
            button.setMinSize(237, 43);
            button.setMaxSize(237, 43);
            button.setStyle("-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 2em;");
            button.setOnMouseClicked(event -> {
                ViewManager.loadPopupWindow(new TableauView(playerModel).setScene());
            });
            this.vBox.getChildren().add(button);
        }
        Button button = new Button("Spelregels");
        button.setMinSize(237, 43);
        button.setMaxSize(237, 43);
        button.setStyle("-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 2em;");
        button.setOnMouseClicked(event -> {
            ViewManager.loadSpelregelView();
        });
        this.vBox.getChildren().add(button);
    }
}
