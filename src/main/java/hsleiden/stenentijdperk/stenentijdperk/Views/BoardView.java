package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.Controllers.BoardController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class BoardView {
	private BoardController controller;
	private GridPane view;

	public BoardView() {
		this.controller = new BoardController();
		setupPane();
	}

	public GridPane setScene() {
		return view;
	}

	private void setupPane() {
		this.view = new GridPane();
		this.view.setPadding(new Insets(10, 10, 10, 10));
		this.view.setStyle("-fx-background-color: d0c9b5");
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

		Label amountLabel = new Label("Hoeveel:");
		amountLabel.setStyle("-fx-font-size: 20px;");
		GridPane.setConstraints(amountLabel, 1, 1, 10, 3);

		TextField amountField = new TextField();
		GridPane.setConstraints(amountField, 6, 1, 15, 3);

		/*
		 * locaties jacht: onbeperkt hut: 2 hutkaart: 1 beschavingskaart: 1 gereedschap:
		 * 1 akkerbouw: 1 steen, leem, goud, hout: 7
		 */

		Button hutKaartButton1 = new Button("Plaats stamlid op een hut kaart");
		// hutKaartButton1.setMinSize(300, 60);
		// hutKaartButton1.setMaxSize(300, 60);
		//TODO maybe make this a single variable
		hutKaartButton1.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 2em;");
		GridPane.setConstraints(hutKaartButton1, 0, 1, 1, 1);

		Button hutKaartButton2 = new Button("Plaats stamlid op een hut kaart");
		// hutKaartButton2.setMinSize(300, 60);
		// hutKaartButton2.setMaxSize(300, 60);
		hutKaartButton2.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 2em;");
		GridPane.setConstraints(hutKaartButton2, 2, 1, 1, 1);

		Button hutKaartButton3 = new Button("Plaats stamlid op een hut kaart");
		// hutKaartButton3.setMinSize(300, 60);
		// hutKaartButton3.setMaxSize(300, 60);
		hutKaartButton3.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 2em;");
		GridPane.setConstraints(hutKaartButton3, 4, 1, 1, 1);

		Button hutKaartButton4 = new Button("Plaats stamlid op een hut kaart");
		// hutKaartButton4.setMinSize(300, 60);
		// hutKaartButton4.setMaxSize(300, 60);
		hutKaartButton4.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 2em;");
		GridPane.setConstraints(hutKaartButton4, 6, 1, 1, 1);

		Button beschavingsKaartButton1 = new Button("Plaats stamlid op een beschavingskaart");
		// beschavingsKaartButton1.setMinSize(300, 60);
		// beschavingsKaartButton1.setMaxSize(300, 60);
		beschavingsKaartButton1.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 2em;");
		GridPane.setConstraints(beschavingsKaartButton1, 8, 1, 1, 1);

		Button beschavingsKaartButton2 = new Button("Plaats stamlid op een beschavingskaart");
		// beschavingsKaartButton2.setMinSize(300, 60);
		// beschavingsKaartButton2.setMaxSize(300, 60);
		beschavingsKaartButton2.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 2em;");
		GridPane.setConstraints(beschavingsKaartButton2, 10, 1, 1, 1);

		Button beschavingsKaartButton3 = new Button("Plaats stamlid op een beschavingskaart");
		// beschavingsKaartButton3.setMinSize(300, 60);
		// beschavingsKaartButton3.setMaxSize(300, 60);
		beschavingsKaartButton3.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 2em;");
		GridPane.setConstraints(beschavingsKaartButton3, 12, 1, 1, 1);

		Button beschavingsKaartButton4 = new Button("Plaats stamlid op een beschavingskaart");
		// beschavingsKaartButton4.setMinSize(300, 60);
		// beschavingsKaartButton4.setMaxSize(300, 60);
		beschavingsKaartButton4.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 2em;");
		GridPane.setConstraints(beschavingsKaartButton4, 14, 1, 1, 1);

		Button hutButton = new Button("Plaats stamleden bij de hut");
		// hutButton.setMinSize(300, 60);
		// hutButton.setMaxSize(300, 60);
		hutButton.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 2em;");
		GridPane.setConstraints(hutButton, 0, 5, 1, 1);

		Button gereedschapButton = new Button("Plaats stamlid bij de gereedschapsmaker");
		// gereedschapButton.setMinSize(300, 60);
		// gereedschapButton.setMaxSize(300, 60);
		gereedschapButton.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 2em;");
		GridPane.setConstraints(gereedschapButton, 2, 5, 1, 1);

		Button akkerbouwButton = new Button("Plaats stamlid bij de akkerbouw");
		// akkerbouwButton.setMinSize(300, 60);
		// akkerbouwButton.setMaxSize(300, 60);
		akkerbouwButton.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 2em;");
		GridPane.setConstraints(akkerbouwButton, 4, 5, 1, 1);

		Button jachtButton = new Button("Plaats stamlid/leden op jachtvlakte");
		// jachtButton.setMinSize(300, 60);
		// jachtButton.setMaxSize(300, 60);
		jachtButton.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 2em;");
		GridPane.setConstraints(jachtButton, 0, 9, 1, 1);

		Button bosButton = new Button("Plaats stamlid/leden bij het bos");
		// bosButton.setMinSize(300, 60);
		// bosButton.setMaxSize(300, 60);
		bosButton.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 2em;");
		GridPane.setConstraints(bosButton, 2, 9, 1, 1);

		Button leemGroeveButton = new Button("Plaats stamlid/leden bij de leem groeve");
		// leemGroeveButton.setMinSize(300, 60);
		// leemGroeveButton.setMaxSize(300, 60);
		leemGroeveButton.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 2em;");
		GridPane.setConstraints(leemGroeveButton, 4, 9, 1, 1);

		Button steenGroeveButton = new Button("Plaats stamlid/leden bij de steen groeve");
		// steenGroeveButton.setMinSize(300, 60);
		// steenGroeveButton.setMaxSize(300, 60);
		steenGroeveButton.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 2em;");
		GridPane.setConstraints(steenGroeveButton, 6, 9, 1, 1);

		Button rivierButton = new Button("Plaats stamlid/leden bij de rivier");
		// rivierButton.setMinSize(300, 60);
		// rivierButton.setMaxSize(300, 60);
		rivierButton.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 2em;");
		GridPane.setConstraints(rivierButton, 8, 9, 1, 1);

		Button endTurn = new Button("Beurt eindigen");
		endTurn.setMinSize(300, 60);
		endTurn.setMaxSize(300, 60);
		endTurn.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 2em;");
		GridPane.setConstraints(endTurn, 7, 6, 15, 15);

		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				/*if (actionEvent.getSource() == hutKaartButton1) {
					controller.onKaartButtonClick(0);
				} else if (actionEvent.getSource() == hutKaartButton2) {
					controller.onKaartButtonClick(1);
				} else if (actionEvent.getSource() == hutKaartButton3) {
					controller.onKaartButtonClick(2);
				} else if (actionEvent.getSource() == hutKaartButton4) {
					controller.onKaartButtonClick(3);
				} else*/ if (actionEvent.getSource() == beschavingsKaartButton1) {
					controller.onKaartButtonClick(4);
				} else if (actionEvent.getSource() == beschavingsKaartButton2) {
					controller.onKaartButtonClick(5);
				} else if (actionEvent.getSource() == beschavingsKaartButton3) {
					controller.onKaartButtonClick(6);
				} else if (actionEvent.getSource() == beschavingsKaartButton4) {
					controller.onKaartButtonClick(7);
				/*} else if (actionEvent.getSource() == hutButton) {
					controller.onVillageButtonClick(0);
				} else if (actionEvent.getSource() == gereedschapButton) {
					controller.onVillageButtonClick(1);
				} else if (actionEvent.getSource() == akkerbouwButton) {
					controller.onVillageButtonClick(2); */
				} else if(actionEvent.getSource() == jachtButton) {
					controller.onResourceButtonClick(0);
				} else if (actionEvent.getSource() == bosButton) {
					controller.onResourceButtonClick(1);
				} else if (actionEvent.getSource() == leemGroeveButton) {
					controller.onResourceButtonClick(2);
				} else if (actionEvent.getSource() == steenGroeveButton) {
					controller.onResourceButtonClick(3);
				} else if (actionEvent.getSource() == rivierButton) {
					controller.onResourceButtonClick(4);
				} else if (actionEvent.getSource() == endTurn) {
					controller.endTurn();
				}
			}
		};

		hutKaartButton1.setOnAction(event);
		hutKaartButton2.setOnAction(event);
		hutKaartButton3.setOnAction(event);
		hutKaartButton4.setOnAction(event);
		beschavingsKaartButton1.setOnAction(event);
		beschavingsKaartButton2.setOnAction(event);
		beschavingsKaartButton3.setOnAction(event);
		beschavingsKaartButton4.setOnAction(event);
		hutButton.setOnAction(event);
		gereedschapButton.setOnAction(event);
		akkerbouwButton.setOnAction(event);
		jachtButton.setOnAction(event);
		bosButton.setOnAction(event);
		leemGroeveButton.setOnAction(event);
		steenGroeveButton.setOnAction(event);
		rivierButton.setOnAction(event);
		endTurn.setOnAction(event);

		this.view.getChildren().addAll(hutKaartButton1, hutKaartButton2, hutKaartButton3, hutKaartButton4,
				beschavingsKaartButton1, beschavingsKaartButton2, beschavingsKaartButton3, beschavingsKaartButton4,
				hutButton, gereedschapButton, akkerbouwButton, jachtButton, bosButton, leemGroeveButton,
				steenGroeveButton, rivierButton, endTurn);
	}
}
