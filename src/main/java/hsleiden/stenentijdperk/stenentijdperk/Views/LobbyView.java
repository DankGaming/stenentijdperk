package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.Controllers.LobbyController;
import hsleiden.stenentijdperk.stenentijdperk.Managers.ViewManager;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;
import hsleiden.stenentijdperk.stenentijdperk.observers.LobbyObservable;
import hsleiden.stenentijdperk.stenentijdperk.observers.LobbyObserver;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class LobbyView implements LobbyObserver {
    private LobbyController controller;
    private PlayerModel playerModel;
    private GridPane view;

    private Label lobbyInformation = new Label("");
    private Label spelers = new Label("");
    private Label speler1 = new Label("");
    private Label speler2 = new Label("");
    private Label speler3 = new Label("");
    private Label speler4 = new Label("");

    boolean visible = false;
    private ArrayList<Label> playerLabels;

    public LobbyView(PlayerModel model) {
        this.controller = new LobbyController();
        this.playerModel = model;
        this.controller.registerObserver(this);

        setupPane();
    }

    public GridPane setScene() {
        return view;
    }

    public void selectLobby(int id, ArrayList<PlayerModel> players) {
        this.lobbyInformation.setText("Lobby " + id);
        this.lobbyInformation.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; ");
        updateLabels(players);
    }

    @Override
    public void update(LobbyObservable lo) {
        selectLobby(lo.getId(), lo.getPlayers(lo.getId()));
    }
    
    private void setupPane() {
        this.view = new GridPane();
        this.view.setPadding(new Insets(10, 10, 10, 10));
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

        Label welkomLabel = new Label("Hallo " + playerModel.getNaam() + ", je hebt keuze uit deze lobby's");
        welkomLabel.setStyle("-fx-font-size: 20px; ");
        GridPane.setConstraints(welkomLabel, 1, 1, 48, 3);

        Button lobbyButton1 = createLobbyButton("Lobby 1");
        Button lobbyButton2 = createLobbyButton("Lobby 2");
        Button lobbyButton3 = createLobbyButton("Lobby 3");
        Button lobbyButton4 = createLobbyButton("Lobby 4");
        Button lobbyButton5 = createLobbyButton("Lobby 5");

        Button joinLobbyButton = new Button("Join");
        joinLobbyButton.setMinSize(280, 120);
        joinLobbyButton.setMaxSize(280, 120);
        joinLobbyButton.setStyle(
                "-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 2em;");
        GridPane.setConstraints(joinLobbyButton, 30, 26, 10, 10);

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (actionEvent.getSource() == lobbyButton1) {
                    controller.setLobbyId(1, playerModel);
                } else if (actionEvent.getSource() == lobbyButton2) {
                    controller.setLobbyId(2, playerModel);
                } else if (actionEvent.getSource() == lobbyButton3) {
                    controller.setLobbyId(3, playerModel);
                } else if (actionEvent.getSource() == lobbyButton4) {
                    controller.setLobbyId(4, playerModel);
                } else if (actionEvent.getSource() == lobbyButton5) {
                    controller.setLobbyId(5, playerModel);
                } else if (actionEvent.getSource() == joinLobbyButton) {
                    controller.joinLobby();
                }
            }
        };

        lobbyButton1.setOnAction(event);
        lobbyButton2.setOnAction(event);
        lobbyButton3.setOnAction(event);
        lobbyButton4.setOnAction(event);
        lobbyButton5.setOnAction(event);
        joinLobbyButton.setOnAction(event);

        Button startGame = new Button("Start game");
        startGame.setVisible(visible);
        startGame.setOnMouseClicked(event1 -> {
            ViewManager.loadBoardView();
        });
        startGame.setMinSize(280, 120);
        startGame.setMaxSize(280, 120);
        startGame.setStyle(
                "-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 2em;");
        GridPane.setConstraints(startGame, 30, 36, 10, 10);

        VBox lobbyVbox = new VBox(lobbyButton1, lobbyButton2, lobbyButton3, lobbyButton4, lobbyButton5);

        ScrollPane lobbyScrollPane = new ScrollPane();
        lobbyScrollPane.setContent(lobbyVbox);
        lobbyScrollPane.setStyle("-fx-background-color: black;");
        lobbyScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        GridPane.setConstraints(lobbyScrollPane, 1, 5, 25, 30);

        VBox informationVbox = new VBox(this.lobbyInformation, this.spelers, this.speler1, this.speler2, this.speler3,
                this.speler4);

        informationVbox.setPadding(new Insets(0, 0, 0, 5));

        ScrollPane informationScrollPane = new ScrollPane();
        informationScrollPane.setContent(informationVbox);
        informationScrollPane.setStyle("-fx-background-color: black;");
        informationScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        GridPane.setConstraints(informationScrollPane, 30, 5, 18, 15);

        this.view.getChildren().addAll(welkomLabel, lobbyScrollPane, informationScrollPane, joinLobbyButton, startGame);
    }

    private void updateLabels(ArrayList<PlayerModel> players) {
        if (players.size() > 0)
            this.spelers.setText("Spelers: ");
        this.spelers.setStyle("-fx-font-size: 22px;");

        String fontSize = "-fx-font-size: 15px;";

        try {
            this.speler1.setText(players.get(0).getNaam());
            this.speler1.setStyle(fontSize);
        } catch (Exception ignored) {}

        try {
            this.speler2.setText(players.get(1).getNaam());
            this.speler2.setStyle(fontSize);
        } catch (Exception ignored) {}

        try {
            this.speler3.setText(players.get(2).getNaam());
            this.speler3.setStyle(fontSize);
        } catch (Exception ignored) {}

        try {
            this.speler4.setText(players.get(3).getNaam());
            this.speler4.setStyle(fontSize);
        } catch (Exception ignored) {}

    }

    private Button createLobbyButton(String text) {
        Button lobbyButton = new Button(text);
        lobbyButton.setMinWidth(373);
        lobbyButton.setMinHeight(100);
        lobbyButton.setStyle("-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 2em;");
        return lobbyButton;
    }
}
