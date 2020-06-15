package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.Controllers.LoginController;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;
import hsleiden.stenentijdperk.stenentijdperk.Models.TableauModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class LoginView {
	private LoginController controller;
	private PlayerModel playermodel;
	private GridPane view;

	public LoginView() {
		this.controller = new LoginController();
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

		for(int i = 0; i < amountOfColumns; i++) {
			ColumnConstraints columnConstraints = new ColumnConstraints();
			columnConstraints.setPercentWidth(100 / amountOfColumns);
			this.view.getColumnConstraints().add(columnConstraints);
		}
		for(int j = 0; j < amountOfRows; j++) {
			RowConstraints rowConstraints = new RowConstraints();
			rowConstraints.setPercentHeight(100 / amountOfRows);
			this.view.getRowConstraints().add(rowConstraints);
		}

		Label naamLabel = new Label("Naam:");
		naamLabel.setStyle("-fx-font-size: 20px;");
		GridPane.setConstraints(naamLabel, 1, 1, 10, 3);

		TextField naamField = new TextField();
		GridPane.setConstraints(naamField, 6, 1, 15, 3);

		Button verderButton = new Button("Verder");
		verderButton.setMinSize(150, 60);
		verderButton.setMaxSize(150, 60);
		verderButton.setStyle("-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 2em;");
		GridPane.setConstraints(verderButton, 7, 3, 10, 10);


		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				// make call to controller.
				String naam = naamField.getText();
				if(naam.length() > 0)
					controller.onVerderButtonClick(naam);
			}
		};

		verderButton.setOnAction(event);

		this.view.getChildren().addAll(naamLabel, naamField, verderButton);
	}
}
