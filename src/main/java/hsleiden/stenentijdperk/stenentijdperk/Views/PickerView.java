package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.Managers.ViewManager;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class PickerView {
    private HBox hBox;
    private ScrollPane view;

    public PickerView() {
        setupPane();
        testButtonGeneration();
    }

    public void testButtonGeneration() {
        ArrayList<PlayerModel> playerModels = new ArrayList<>();
        PlayerModel matt = new PlayerModel("Matt");
        PlayerModel jake = new PlayerModel("Jake");
        PlayerModel lucas = new PlayerModel("Lucas");
        PlayerModel carlos = new PlayerModel("Carlos");
        playerModels.add(matt);
        playerModels.add(jake);
        playerModels.add(lucas);

        generateButtons(playerModels);
    }

    public void createPane() {
        this.view = new ScrollPane();
        this.view.setContent(this.hBox);
    }

    public ScrollPane setScene() {
        createPane();
        return this.view;
    }

    public void setupPane() {
        this.hBox = new HBox();
    }

    // Functions that create buttons based on playermodels

    public void generateButtons(ArrayList<PlayerModel> playerModels) {
        for(PlayerModel playerModel : playerModels) {
            Button button = new Button(playerModel.getNaam());
            button.setOnMouseClicked(event -> {
                ViewManager.loadPopupWindow(new TableauView(playerModel).setScene());
            });
            this.hBox.getChildren().add(button);
        }
    }
}
