package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.Controllers.BoardController;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane;
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

		Button villagerButton = new Button("Plaats stamlid");
		villagerButton.setMinSize(300, 60);
		villagerButton.setMaxSize(300, 60);
		villagerButton.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 2em;");
		GridPane.setConstraints(villagerButton, 7, 3, 10, 10);

		Button endTurn = new Button("Beurt eindigen");
		endTurn.setMinSize(300, 60);
		endTurn.setMaxSize(300, 60);
		endTurn.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 2em;");
		GridPane.setConstraints(endTurn, 7, 3, 10, 10);

		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				// make call to controller.
				controller.onVillagerButtonClick();
			}
		};

		/*
		 * EventHandler<ActionEvent> endTurn = new EventHandler<ActionEvent>() {
		 * 
		 * @Override public void handle(ActionEvent actionEvent) { // make call to
		 * controller. controller.onEndTurnClick(); } };
		 */

		villagerButton.setOnAction(event);
		// endTurn.setOnAction(endTurn);

		this.view.getChildren().addAll(villagerButton);
	}
}
