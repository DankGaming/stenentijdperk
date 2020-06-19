package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.App;
import hsleiden.stenentijdperk.stenentijdperk.Controllers.BoardController;
import hsleiden.stenentijdperk.stenentijdperk.Models.BoardModel;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BoardView {
	private BoardController controller;
	private BoardModel boardModel;
	private ArrayList<Button> beschavingsKaartButtons;
	private GridPane view = new GridPane();
	private String spelbordImage = "src/main/java/hsleiden/stenentijdperk/stenentijdperk/Resources/Achtergronden/spelbord2.jpg";
	private ImageView imageView;
	private String Speler1 = "src/main/java/hsleiden/stenentijdperk/stenentijdperk/Resources/Spelers/Speler1.png";
	private String Speler2 = "src/main/java/hsleiden/stenentijdperk/stenentijdperk/Resources/Spelers/Speler2.png";
	private String Speler3 = "src/main/java/hsleiden/stenentijdperk/stenentijdperk/Resources/Spelers/Speler3.png";
	private String Speler4 = "src/main/java/hsleiden/stenentijdperk/stenentijdperk/Resources/Spelers/Speler4.png";
	private Label speler1Label;
	private Label speler2Label;
	private Label speler3Label;
	private Label speler4Label;
	private ImageView speler1Image;
	private ImageView speler2Image;
	private ImageView speler3Image;
	private ImageView speler4Image;

	public BoardView() {
		this.beschavingsKaartButtons = new ArrayList<Button>();
		this.controller = new BoardController();
		setupPane();
	}

	public GridPane setScene() {
		return view;
	}

	private void setupPane() {
		this.view = new GridPane();
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

		// Spelbord Image

		FileInputStream input = null;
		try {
			input = new FileInputStream(this.spelbordImage);
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println(fileNotFoundException);
		}

		assert input != null;
		Image image = new Image(input);
		this.imageView = new ImageView(image);
		this.imageView.setFitHeight(800);
		this.imageView.setFitWidth(1200);
		GridPane.setConstraints(imageView, 0, 0, 50, 50);

		// Speler stamleden

		FileInputStream speler1 = null;
		try {
			speler1 = new FileInputStream(this.Speler1);
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println(fileNotFoundException);
		}

		assert speler1 != null;
		Image Speler1 = new Image(speler1);
		speler1Image = new ImageView(Speler1);
		speler1Image.setFitHeight(30);
		speler1Image.setFitWidth(30);
		speler1Image.setVisible(false);

		FileInputStream speler2 = null;
		try {
			speler2 = new FileInputStream(this.Speler2);
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println(fileNotFoundException);
		}

		assert speler2 != null;
		Image Speler2 = new Image(speler2);
		speler2Image = new ImageView(Speler2);
		speler2Image.setFitHeight(30);
		speler2Image.setFitWidth(30);
		speler2Image.setVisible(false);

		FileInputStream speler3 = null;
		try {
			speler3 = new FileInputStream(this.Speler3);
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println(fileNotFoundException);
		}

		assert speler3 != null;
		Image Speler3 = new Image(speler3);
		speler3Image = new ImageView(Speler3);
		speler3Image.setFitHeight(30);
		speler3Image.setFitWidth(30);
		speler3Image.setVisible(false);

		FileInputStream speler4 = null;
		try {
			speler4 = new FileInputStream(this.Speler4);
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println(fileNotFoundException);
		}

		assert speler4 != null;
		Image Speler4 = new Image(speler4);
		speler4Image = new ImageView(Speler4);
		speler4Image.setFitHeight(30);
		speler4Image.setFitWidth(30);
		speler4Image.setVisible(false);

		// Stamleden hoeveelheden
		// + boardModel.getVillagersOnBoard
		speler1Label = new Label("  ");
		speler1Label.setStyle("-fx-font-size: 20px; -fx-font-weight: bold");
		speler1Label.setVisible(false);

		speler2Label = new Label("  1");
		speler2Label.setStyle("-fx-font-size: 20px; -fx-font-weight: bold");
		speler2Label.setVisible(false);
		// GridPane.setConstraints(speler2Label, 26, 11, 2 ,2);

		speler3Label = new Label("  1");
		speler3Label.setStyle("-fx-font-size: 20px; -fx-font-weight: bold");
		speler3Label.setVisible(false);
		// GridPane.setConstraints(speler3Label, 28, 11, 2 ,2);

		speler4Label = new Label("  1");
		speler4Label.setStyle("-fx-font-size: 20px; -fx-font-weight: bold");
		speler4Label.setVisible(false);
		// GridPane.setConstraints(speler4Label, 30, 11, 2 ,2);

		Label amountLabel = new Label("Hoeveel:");
		amountLabel.setStyle("-fx-font-size: 20px;");
		amountLabel.setVisible(true);
		GridPane.setConstraints(amountLabel, 20, 25, 5, 3);

		TextField amountField = new TextField();
		amountField.setVisible(true);
		amountField.setEditable(false);
		GridPane.setConstraints(amountField, 26, 25, 10, 3);


		/*
		 * locaties jacht: onbeperkt hut: 2 hutkaart: 1 beschavingskaart: 1 gereedschap:
		 * 1 akkerbouw: 1 steen, leem, goud, hout: 7
		 */

		// Buttons
		String style = "-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 10px;";
		for (int i = 0; i < 4; i++) { // maakt 4 beschavingskaart buttons
			FileInputStream input2 = null;
			try {
				input2 = new FileInputStream(this.controller.getKaartPath(i));
			} catch (FileNotFoundException fileNotFoundException) {
				System.out.println(fileNotFoundException);
			}

			assert input2 != null;
			Image image2 = new Image(input2);
			ImageView imageView = new ImageView(image2);
			imageView.setFitHeight(160);
			imageView.setPreserveRatio(true);
			Button beschavingsKaartButton = new Button("", imageView);
			beschavingsKaartButtons.add(beschavingsKaartButton);
		}

		GridPane.setConstraints(beschavingsKaartButtons.get(0), 26, 40, 1, 1);
		GridPane.setConstraints(beschavingsKaartButtons.get(1), 31, 40, 1, 1);
		GridPane.setConstraints(beschavingsKaartButtons.get(2), 37, 40, 1, 1);
		GridPane.setConstraints(beschavingsKaartButtons.get(3), 42, 40, 1, 1);

		Button hutKaartButton1 = new Button("stamlid op een hut");
		hutKaartButton1.setStyle(style);
		GridPane.setConstraints(hutKaartButton1, 5, 36, 5, 3);

		Button hutKaartButton2 = new Button("stamlid op een hut");
		hutKaartButton2.setStyle(style);
		GridPane.setConstraints(hutKaartButton2, 10, 36, 5, 3);

		Button hutKaartButton3 = new Button("stamlid op een hut");
		hutKaartButton3.setStyle(style);
		GridPane.setConstraints(hutKaartButton3, 15, 36, 5, 3);

		Button hutKaartButton4 = new Button("stamlid op een hut");
		hutKaartButton4.setStyle(style);
		GridPane.setConstraints(hutKaartButton4, 20, 36, 5, 3);

		Button beschavingsKaartButton1 = new Button("beschavingskaart1");
		beschavingsKaartButton1.setStyle(style);
		GridPane.setConstraints(beschavingsKaartButton1, 26, 32, 5, 1);

		Button beschavingsKaartButton2 = new Button("beschavingskaart2");
		beschavingsKaartButton2.setStyle(style);
		GridPane.setConstraints(beschavingsKaartButton2, 32, 32, 5, 1);

		Button beschavingsKaartButton3 = new Button("beschavingskaart3");
		beschavingsKaartButton3.setStyle(style);
		GridPane.setConstraints(beschavingsKaartButton3, 37, 32, 5, 1);

		Button beschavingsKaartButton4 = new Button("beschavingskaart4");
		beschavingsKaartButton4.setStyle(style);
		GridPane.setConstraints(beschavingsKaartButton4, 43, 32, 5, 1);

		Button hutButton = new Button("Stamlid hut");
		hutButton.setStyle(style);
		GridPane.setConstraints(hutButton, 18, 32, 5, 1);

		Button gereedschapButton = new Button("gereedschapsmaker");
		gereedschapButton.setStyle(style);
		GridPane.setConstraints(gereedschapButton, 25, 26, 5, 1);

		Button akkerbouwButton = new Button("akkerbouw");
		akkerbouwButton.setStyle(style);
		GridPane.setConstraints(akkerbouwButton, 15, 25, 5, 1);

		Button jachtButton = new Button("stamlid jachtvlakte");
		jachtButton.setStyle(style);
		GridPane.setConstraints(jachtButton, 5, 7, 5, 1);

		Button bosButton = new Button("stamlid bos");
		bosButton.setStyle(style);
		GridPane.setConstraints(bosButton, 21, 15, 5, 1);

		Button leemGroeveButton = new Button("stamlid leem");
		leemGroeveButton.setStyle(style);
		GridPane.setConstraints(leemGroeveButton, 28, 15, 5, 1);

		Button steenGroeveButton = new Button("stamlid steen");
		steenGroeveButton.setStyle(style);
		GridPane.setConstraints(steenGroeveButton, 40, 15, 5, 1);

		Button rivierButton = new Button("stamlid rivier");
		rivierButton.setStyle(style);
		GridPane.setConstraints(rivierButton, 41, 25, 5, 1);

		Button endTurn = new Button("Beurt eindigen");
		endTurn.setStyle(style);
		GridPane.setConstraints(endTurn, 22, 5, 15, 1);

		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if (actionEvent.getSource() == hutKaartButton1) {
					GridPane.setConstraints(speler1Image, 5, 40, 2, 2);
					GridPane.setConstraints(speler1Label, 5, 40, 1, 1);

					GridPane.setConstraints(speler2Image, 7, 40, 2, 2);
					GridPane.setConstraints(speler2Label, 7, 40, 1, 1);

					GridPane.setConstraints(speler3Image, 5, 42, 2, 2);
					GridPane.setConstraints(speler3Label, 5, 42, 1, 1);

					GridPane.setConstraints(speler4Image, 7, 42, 2, 2);
					GridPane.setConstraints(speler4Label, 7, 42, 1, 1);

					setSpelersVisable(true);
					controller.onButtonClick(8);
				} else if (actionEvent.getSource() == hutKaartButton2) {
					GridPane.setConstraints(speler1Image, 10, 40, 2, 2);
					GridPane.setConstraints(speler1Label, 10, 40, 1, 1);

					GridPane.setConstraints(speler2Image, 12, 40, 2, 2);
					GridPane.setConstraints(speler2Label, 12, 40, 1, 1);

					GridPane.setConstraints(speler3Image, 10, 42, 2, 2);
					GridPane.setConstraints(speler3Label, 10, 42, 1, 1);

					GridPane.setConstraints(speler4Image, 12, 42, 2, 2);
					GridPane.setConstraints(speler4Label, 12, 42, 1, 1);

					setSpelersVisable(true);
					controller.onButtonClick(9);
				} else if (actionEvent.getSource() == hutKaartButton3) {
					GridPane.setConstraints(speler1Image, 15, 40, 2, 2);
					GridPane.setConstraints(speler1Label, 15, 40, 1, 1);

					GridPane.setConstraints(speler2Image, 17, 40, 2, 2);
					GridPane.setConstraints(speler2Label, 17, 40, 1, 1);

					GridPane.setConstraints(speler3Image, 15, 42, 2, 2);
					GridPane.setConstraints(speler3Label, 15, 42, 1, 1);

					GridPane.setConstraints(speler4Image, 17, 42, 2, 2);
					GridPane.setConstraints(speler4Label, 17, 42, 1, 1);

					setSpelersVisable(true);
					controller.onButtonClick(10);
				} else if (actionEvent.getSource() == hutKaartButton4) {
					GridPane.setConstraints(speler1Image, 20, 40, 2, 2);
					GridPane.setConstraints(speler1Label, 20, 40, 1, 1);

					GridPane.setConstraints(speler2Image, 22, 40, 2, 2);
					GridPane.setConstraints(speler2Label, 22, 40, 1, 1);

					GridPane.setConstraints(speler3Image, 20, 42, 2, 2);
					GridPane.setConstraints(speler3Label, 20, 42, 1, 1);

					GridPane.setConstraints(speler4Image, 22, 42, 2, 2);
					GridPane.setConstraints(speler4Label, 22, 42, 1, 1);

					setSpelersVisable(true);
					controller.onButtonClick(11);
				} else if (actionEvent.getSource() == beschavingsKaartButton1) {
					GridPane.setConstraints(speler1Image, 27, 36, 2, 2);
					GridPane.setConstraints(speler1Label, 27, 36, 1, 1);

					GridPane.setConstraints(speler2Image, 29, 36, 2, 2);
					GridPane.setConstraints(speler2Label, 29, 36, 1, 1);

					GridPane.setConstraints(speler3Image, 27, 38, 2, 2);
					GridPane.setConstraints(speler3Label, 27, 38, 1, 1);

					GridPane.setConstraints(speler4Image, 29, 38, 2, 2);
					GridPane.setConstraints(speler4Label, 29, 38, 1, 1);

					setSpelersVisable(true);
					controller.onButtonClick(12);
				} else if (actionEvent.getSource() == beschavingsKaartButton2) {
					GridPane.setConstraints(speler1Image, 32, 36, 2, 2);
					GridPane.setConstraints(speler1Label, 32, 36, 1, 1);

					GridPane.setConstraints(speler2Image, 34, 36, 2, 2);
					GridPane.setConstraints(speler2Label, 34, 36, 1, 1);

					GridPane.setConstraints(speler3Image, 32, 38, 2, 2);
					GridPane.setConstraints(speler3Label, 32, 38, 1, 1);

					GridPane.setConstraints(speler4Image, 34, 38, 2, 2);
					GridPane.setConstraints(speler4Label, 34, 38, 1, 1);

					setSpelersVisable(true);
					controller.onButtonClick(13);
				} else if (actionEvent.getSource() == beschavingsKaartButton3) {
					GridPane.setConstraints(speler1Image, 37, 36, 2, 2);
					GridPane.setConstraints(speler1Label, 37, 36, 1, 1);

					GridPane.setConstraints(speler2Image, 39, 36, 2, 2);
					GridPane.setConstraints(speler2Label, 39, 36, 1, 1);

					GridPane.setConstraints(speler3Image, 37, 38, 2, 2);
					GridPane.setConstraints(speler3Label, 37, 38, 1, 1);

					GridPane.setConstraints(speler4Image, 39, 38, 2, 2);
					GridPane.setConstraints(speler4Label, 39, 38, 1, 1);

					setSpelersVisable(true);
					controller.onButtonClick(14);
				} else if (actionEvent.getSource() == beschavingsKaartButton4) {
					GridPane.setConstraints(speler1Image, 43, 36, 2, 2);
					GridPane.setConstraints(speler1Label, 43, 36, 1, 1);

					GridPane.setConstraints(speler2Image, 45, 36, 2, 2);
					GridPane.setConstraints(speler2Label, 45, 36, 1, 1);

					GridPane.setConstraints(speler3Image, 43, 38, 2, 2);
					GridPane.setConstraints(speler3Label, 43, 38, 1, 1);

					GridPane.setConstraints(speler4Image, 45, 38, 2, 2);
					GridPane.setConstraints(speler4Label, 45, 38, 1, 1);

					setSpelersVisable(true);
					controller.onButtonClick(15);
				} else if (actionEvent.getSource() == hutButton) {
					GridPane.setConstraints(speler1Image, 16, 34, 2, 2);
					GridPane.setConstraints(speler1Label, 16, 34, 1, 1);

					GridPane.setConstraints(speler2Image, 18, 34, 2, 2);
					GridPane.setConstraints(speler2Label, 18, 34, 1, 1);

					GridPane.setConstraints(speler3Image, 20, 34, 2, 2);
					GridPane.setConstraints(speler3Label, 20, 34, 1, 1);

					GridPane.setConstraints(speler4Image, 22, 34, 2, 2);
					GridPane.setConstraints(speler4Label, 22, 34, 1, 1);

					setSpelersVisable(true);
					controller.onButtonClick(6);
				} else if (actionEvent.getSource() == gereedschapButton) {
					GridPane.setConstraints(speler1Image, 26, 21, 2, 2);
					GridPane.setConstraints(speler1Label, 26, 21, 1, 1);

					GridPane.setConstraints(speler2Image, 28, 21, 2, 2);
					GridPane.setConstraints(speler2Label, 28, 21, 1, 1);

					GridPane.setConstraints(speler3Image, 26, 23, 2, 2);
					GridPane.setConstraints(speler3Label, 26, 23, 1, 1);

					GridPane.setConstraints(speler4Image, 28, 23, 2, 2);
					GridPane.setConstraints(speler4Label, 28, 23, 1, 1);

					setSpelersVisable(true);
					controller.onButtonClick(7);
				} else if (actionEvent.getSource() == akkerbouwButton) {
					GridPane.setConstraints(speler1Image, 13, 27, 2, 2);
					GridPane.setConstraints(speler1Label, 13, 27, 1, 1);

					GridPane.setConstraints(speler2Image, 15, 27, 2, 2);
					GridPane.setConstraints(speler2Label, 15, 27, 1, 1);

					GridPane.setConstraints(speler3Image, 17, 27, 2, 2);
					GridPane.setConstraints(speler3Label, 17, 27, 1, 1);

					GridPane.setConstraints(speler4Image, 19, 27, 2, 2);
					GridPane.setConstraints(speler4Label, 19, 27, 1, 1);

					setSpelersVisable(true);
					controller.onButtonClick(5);
				} else if (actionEvent.getSource() == jachtButton) {
					GridPane.setConstraints(speler1Image, 5, 11, 2, 2);
					GridPane.setConstraints(speler1Label, 5, 11, 1, 1);

					GridPane.setConstraints(speler2Image, 7, 11, 2, 2);
					GridPane.setConstraints(speler2Label, 7, 11, 1, 1);

					GridPane.setConstraints(speler3Image, 9, 11, 2, 2);
					GridPane.setConstraints(speler3Label, 9, 11, 1, 1);

					GridPane.setConstraints(speler4Image, 11, 11, 2, 2);
					GridPane.setConstraints(speler4Label, 11, 11, 1, 1);

					setSpelersVisable(true);
					controller.onResourceButtonClick(0);
				} else if (actionEvent.getSource() == bosButton) {
					GridPane.setConstraints(speler1Image, 17, 11, 2, 2);
					GridPane.setConstraints(speler1Label, 17, 11, 1, 1);

					GridPane.setConstraints(speler2Image, 19, 11, 2, 2);
					GridPane.setConstraints(speler2Label, 19, 11, 1, 1);

					GridPane.setConstraints(speler3Image, 17, 13, 2, 2);
					GridPane.setConstraints(speler3Label, 17, 13, 1, 1);

					GridPane.setConstraints(speler4Image, 19, 13, 2, 2);
					GridPane.setConstraints(speler4Label, 19, 13, 1, 1);

					setSpelersVisable(true);
					controller.onResourceButtonClick(1);
				} else if (actionEvent.getSource() == leemGroeveButton) {
					GridPane.setConstraints(speler1Image, 24, 11, 2, 2);
					GridPane.setConstraints(speler1Label, 24, 11, 1, 1);

					GridPane.setConstraints(speler2Image, 26, 11, 2, 2);
					GridPane.setConstraints(speler2Label, 26, 11, 1, 1);

					GridPane.setConstraints(speler3Image, 28, 11, 2, 2);
					GridPane.setConstraints(speler3Label, 28, 11, 1, 1);

					GridPane.setConstraints(speler4Image, 30, 11, 2, 2);
					GridPane.setConstraints(speler4Label, 30, 11, 1, 1);

					setSpelersVisable(true);
					controller.onResourceButtonClick(2);
				} else if (actionEvent.getSource() == steenGroeveButton) {
					GridPane.setConstraints(speler1Image, 42, 11, 2, 2);
					GridPane.setConstraints(speler1Label, 42, 11, 1, 1);

					GridPane.setConstraints(speler2Image, 44, 11, 2, 2);
					GridPane.setConstraints(speler2Label, 44, 11, 1, 1);

					GridPane.setConstraints(speler3Image, 46, 11, 2, 2);
					GridPane.setConstraints(speler3Label, 46, 11, 1, 1);

					GridPane.setConstraints(speler4Image, 44, 13, 2, 2);
					GridPane.setConstraints(speler4Label, 44, 13, 1, 1);

					setSpelersVisable(true);
					controller.onResourceButtonClick(3);
				} else if (actionEvent.getSource() == rivierButton) {
					GridPane.setConstraints(speler1Image, 37, 21, 2, 2);
					GridPane.setConstraints(speler1Label, 37, 21, 1, 1);

					GridPane.setConstraints(speler2Image, 39, 21, 2, 2);
					GridPane.setConstraints(speler2Label, 39, 21, 1, 1);

					GridPane.setConstraints(speler3Image, 37, 23, 2, 2);
					GridPane.setConstraints(speler3Label, 37, 23, 1, 1);

					GridPane.setConstraints(speler4Image, 39, 23, 2, 2);
					GridPane.setConstraints(speler4Label, 39, 23, 1, 1);

					setSpelersVisable(true);
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
		beschavingsKaartButtons.get(0).setOnAction(event);
		beschavingsKaartButtons.get(1).setOnAction(event);
		beschavingsKaartButtons.get(2).setOnAction(event);
		beschavingsKaartButtons.get(3).setOnAction(event);
		hutButton.setOnAction(event);
		gereedschapButton.setOnAction(event);
		akkerbouwButton.setOnAction(event);
		jachtButton.setOnAction(event);
		bosButton.setOnAction(event);
		leemGroeveButton.setOnAction(event);
		steenGroeveButton.setOnAction(event);
		rivierButton.setOnAction(event);
		endTurn.setOnAction(event);

		this.view.getChildren().addAll(imageView, hutKaartButton1, hutKaartButton2, hutKaartButton3, hutKaartButton4,
				beschavingsKaartButton1, beschavingsKaartButton2, beschavingsKaartButton3, beschavingsKaartButton4,
				hutButton, gereedschapButton, akkerbouwButton, jachtButton, bosButton, leemGroeveButton,
				steenGroeveButton, rivierButton, endTurn, speler1Image, speler2Image, speler3Image, speler4Image,
				speler1Label, speler2Label, speler3Label, speler4Label, amountField, amountLabel);
	}

	private void setSpelersVisable(boolean visable) {
		speler1Image.setVisible(visable);
		speler1Label.setVisible(visable);

		speler2Image.setVisible(visable);
		speler2Label.setVisible(visable);

		speler3Image.setVisible(visable);
		speler3Label.setVisible(visable);

		speler4Image.setVisible(visable);
		speler4Label.setVisible(visable);
	}
//	private void addGridpane(ImageView image, Label label) {
//		this.view.getChildren().add(image, label);
//	}
}
