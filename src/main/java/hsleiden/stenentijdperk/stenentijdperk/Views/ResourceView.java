package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;

import java.util.ArrayList;
import java.util.List;

import hsleiden.stenentijdperk.stenentijdperk.Controllers.PlayerController;
import hsleiden.stenentijdperk.stenentijdperk.Controllers.BoardController;
import hsleiden.stenentijdperk.stenentijdperk.Managers.ViewManager;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Beschavingskaarten.Kaart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ResourceView {

    private GridPane view;
    private List<Label> labels;
    private List<Label> amounts;
    private List<TextField> fields;
    private List<String> labelTexts;
    private Label header;
    private Button resourceButton;
    private PlayerModel playerModel;
    private PlayerController playerController;
    private int price;
    private BoardController boardController;
    private Kaart kaart;

    public ResourceView(PlayerModel playerModel, PlayerController playerController, BoardController boardController,
            int price, Kaart kaart) {
        this.kaart = kaart;
        this.boardController = boardController;
        this.playerModel = playerModel;
        this.playerController = playerController;
        this.price = price;
        this.labels = new ArrayList<Label>();
        this.amounts = new ArrayList<Label>();
        this.fields = new ArrayList<TextField>();
        this.labelTexts = new ArrayList<String>();
        this.labelTexts.add("Hout");
        this.labelTexts.add("Leem");
        this.labelTexts.add("Steen");
        this.labelTexts.add("Goud");
        setupPane();
    }

    public GridPane setScene() {
        return view;
    }

    public void setupPane() {
        this.view = new GridPane();
        this.view.setStyle("-fx-background-color: #d0c9b5");
        int amountOfRows = 50;
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

        String textFont = "-fx-font-size: 15px;";

        header = new Label("U moet " + price + " resources betalen:");
        header.setStyle(textFont);
        GridPane.setConstraints(header, 5, 1, 20, 2);

        int n = 4;
        for (int i = 0; i < 4; i++) {

            // Labels
            this.labels.add(new Label(this.labelTexts.get(i)));
            this.labels.get(i).setStyle(textFont);
            GridPane.setConstraints(this.labels.get(i), 3, n, 5, 2);

            // Hoeveelheden
            this.amounts.add(
                    new Label(Integer.toString(this.playerController.vraagResources(this.playerModel).get(i + 1))));
            this.amounts.get(i).setStyle(textFont);
            GridPane.setConstraints(this.amounts.get(i), 10, n, 2, 2);

            // Text field
            this.fields.add(i, new TextField());
            GridPane.setConstraints(this.fields.get(i), 15, n, 5, 1);
            n += 2;
        }

        // button
        resourceButton = new Button("Confirm");
        resourceButton.setMinSize(220, 50);
        resourceButton.setStyle(
                "-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 20px;");
        GridPane.setConstraints(resourceButton, 3, 15, 5, 5);

        EventHandler<ActionEvent> buttonEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean genoegResources = false;
                if (event.getSource() == resourceButton) {
                    int amount = 0;
                    int i = 0;
                    for (TextField field : fields) {
                        System.out.println(!field.getText().isEmpty());
                        if (!field.getText().isEmpty()) {
                            try {
                                int betaald = Integer.parseInt(field.getText());
                                if (betaald < playerController.vraagResources(playerModel).get(i+1)) {
                                    amount += betaald;
                                }
                                i ++;
                            } catch (Exception e) {
                                System.out.println("Invoer mag geen letters bevatten. " + field.getText());
                            }

                        }
                        System.out.println("amount: " + amount);

                    }
                    System.out.println("price: " + price);
                    if (amount == price) {
                        boardController.kaartGekocht(kaart);
                        ViewManager.closeResourceWindow();
                    } else if (amount < price) {
                        header.setText("Te weinig grondstoffen! (" + amount + "/" + price + ")");
                    } else if (amount > price) {
                        header.setText("Te veel grondstoffen. (" + amount + "/" + price + ")");
                    }
                }
            }
        };

        resourceButton.setOnAction(buttonEvent);

        this.view.getChildren().addAll(header, this.labels.get(0), this.amounts.get(0), this.fields.get(0),
                this.labels.get(1), this.amounts.get(1), this.fields.get(1), this.labels.get(2), this.amounts.get(2),
                this.fields.get(2), this.labels.get(3), this.amounts.get(3), this.fields.get(3), resourceButton);
    }
}
