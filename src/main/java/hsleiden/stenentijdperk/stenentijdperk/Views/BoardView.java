package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.App;
import hsleiden.stenentijdperk.stenentijdperk.Controllers.BoardController;
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BoardView {
	private BoardController controller;
	private GridPane view = new GridPane();
	private String spelbordImage = "src/main/java/hsleiden/stenentijdperk/stenentijdperk/Resources/Achtergronden/spelbord2.jpg";
	private ImageView imageView;
	private String Speler1 = "src/main/java/hsleiden/stenentijdperk/stenentijdperk/Resources/Spelers/Speler1.png";


	public BoardView() {
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
		GridPane.setConstraints(imageView, 0, 0, 50 ,50);

		FileInputStream speler1 = null;
		try {
			speler1 = new FileInputStream(this.Speler1);
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println(fileNotFoundException);
		}

		assert input != null;
		Image Speler1 = new Image(speler1);
		ImageView Speler1Image = new ImageView(Speler1);
		Speler1Image.setFitHeight(30);
		Speler1Image.setFitWidth(30);
		Speler1Image.setVisible(false);
		GridPane.setConstraints(Speler1Image, 23, 2, 2 ,2);



		Label amountLabel = new Label("Hoeveel:");
		amountLabel.setStyle("-fx-font-size: 20px;");
		GridPane.setConstraints(amountLabel, 1, 1, 10, 3);

		TextField amountField = new TextField();
		GridPane.setConstraints(amountField, 6, 1, 15, 3);

		/*
		 * locaties jacht: onbeperkt hut: 2 hutkaart: 1 beschavingskaart: 1 gereedschap:
		 * 1 akkerbouw: 1 steen, leem, goud, hout: 7
		 */

		Button hutKaartButton1 = new Button("stamlid op een hut");
		hutKaartButton1.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 10px;");
		GridPane.setConstraints(hutKaartButton1, 5, 36, 5, 3);

		Button hutKaartButton2 = new Button("sta2lid op een hut");
		hutKaartButton2.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 10px;");
		GridPane.setConstraints(hutKaartButton2, 10, 36, 5, 3);

		Button hutKaartButton3 = new Button("sta3lid op een hut");
		hutKaartButton3.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 10px;");
		GridPane.setConstraints(hutKaartButton3, 15, 36, 5, 3);

		Button hutKaartButton4 = new Button("st4mlid op een hut");
		hutKaartButton4.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 10px;");
		GridPane.setConstraints(hutKaartButton4, 20, 36, 5, 3);

		Button beschavingsKaartButton1 = new Button("beschavingskaart1");
		beschavingsKaartButton1.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 10px;");
		GridPane.setConstraints(beschavingsKaartButton1, 26, 32, 5, 1);

		Button beschavingsKaartButton2 = new Button("beschavingskaart2");
		beschavingsKaartButton2.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 10px;");
		GridPane.setConstraints(beschavingsKaartButton2, 32, 32, 5, 1);

		Button beschavingsKaartButton3 = new Button("beschavingskaart3");
		beschavingsKaartButton3.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 10px;");
		GridPane.setConstraints(beschavingsKaartButton3, 37, 32, 5, 1);

		Button beschavingsKaartButton4 = new Button("beschavingskaart4");
		beschavingsKaartButton4.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 10px;");
		GridPane.setConstraints(beschavingsKaartButton4, 43, 32, 5, 1);

		Button hutButton = new Button("Stamlid hut");
		hutButton.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 10px;");
		GridPane.setConstraints(hutButton, 18, 32, 5, 1);

		Button gereedschapButton = new Button("gereedschapsmaker");
		gereedschapButton.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 10px;");
		GridPane.setConstraints(gereedschapButton, 25, 26, 5, 1);

		Button akkerbouwButton = new Button("akkerbouw");
		akkerbouwButton.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 10px;");
		GridPane.setConstraints(akkerbouwButton, 15, 25, 5, 1);

		Button jachtButton = new Button("stamlid jachtvlakte");
		jachtButton.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size:10px;");
		GridPane.setConstraints(jachtButton, 5, 7, 5, 1);

		Button bosButton = new Button("stamlid bos");
		bosButton.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 10px;");
		GridPane.setConstraints(bosButton, 21, 15, 5, 1);

		Button leemGroeveButton = new Button("stamlid leem");
		leemGroeveButton.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 10px");
		GridPane.setConstraints(leemGroeveButton, 28, 15, 5, 1);

		Button steenGroeveButton = new Button("stamlid steen");
		steenGroeveButton.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 10px");
		GridPane.setConstraints(steenGroeveButton, 40, 15, 5, 1);

		Button rivierButton = new Button("stamlid rivier");
		rivierButton.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 10px");
		GridPane.setConstraints(rivierButton, 41, 25, 5, 1);

		Button endTurn = new Button("Beurt eindigen");
		endTurn.setStyle(
				"-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 20px");
		GridPane.setConstraints(endTurn, 22, 5, 15, 1);

//		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent actionEvent) {
//				if (actionEvent.getSource() == hutKaartButton1) {
//					controller.onKaartButtonClick(1);
//				} else if (actionEvent.getSource() == hutKaartButton2) {
//					controller.onKaartButtonClick(2);
//				} else if (actionEvent.getSource() == hutKaartButton3) {
//					controller.onKaartButtonClick(3);
//				} else if (actionEvent.getSource() == hutKaartButton4) {
//					controller.onKaartButtonClick(4);
//				} else if (actionEvent.getSource() == beschavingsKaartButton1) {
//					controller.onKaartButtonClick(5);
//				} else if (actionEvent.getSource() == beschavingsKaartButton2) {
//					controller.onKaartButtonClick(6);
//				} else if (actionEvent.getSource() == beschavingsKaartButton3) {
//					controller.onKaartButtonClick(7);
//				} else if (actionEvent.getSource() == beschavingsKaartButton4) {
//					controller.onKaartButtonClick(8);
//				} else if (actionEvent.getSource() == hutButton) {
//					controller.onVillageButtonClick(1);
//				} else if (actionEvent.getSource() == gereedschapButton) {
//					controller.onVillageButtonClick(2);
//				} else if (actionEvent.getSource() == akkerbouwButton) {
//					controller.onVillageButtonClick(3);
//				} else if (actionEvent.getSource() == jachtButton) {
//					controller.onResourceButtonClick(1);
//				} else if (actionEvent.getSource() == bosButton) {
//					controller.onResourceButtonClick(2);
//				} else if (actionEvent.getSource() == leemGroeveButton) {
//					controller.onResourceButtonClick(3);
//				} else if (actionEvent.getSource() == steenGroeveButton) {
//					controller.onResourceButtonClick(4);
//				} else if (actionEvent.getSource() == rivierButton) {
//					controller.onResourceButtonClick(5);
//				} else if (actionEvent.getSource() == endTurn) {
//					controller.endTurn();
//				}
//			}
//		};
//		hutKaartButton1.setOnAction(event);
//		hutKaartButton2.setOnAction(event);
//		hutKaartButton3.setOnAction(event);
//		hutKaartButton4.setOnAction(event);
//		beschavingsKaartButton1.setOnAction(event);
//		beschavingsKaartButton2.setOnAction(event);
//		beschavingsKaartButton3.setOnAction(event);
//		beschavingsKaartButton4.setOnAction(event);
//		hutButton.setOnAction(event);
//		gereedschapButton.setOnAction(event);
//		akkerbouwButton.setOnAction(event);
//		jachtButton.setOnAction(event);
//		bosButton.setOnAction(event);
//		leemGroeveButton.setOnAction(event);
//		steenGroeveButton.setOnAction(event);
//		rivierButton.setOnAction(event);
//		endTurn.setOnAction(event);
//
		this.view.getChildren().addAll(imageView, hutKaartButton1, hutKaartButton2, hutKaartButton3, hutKaartButton4,
				beschavingsKaartButton1, beschavingsKaartButton2, beschavingsKaartButton3, beschavingsKaartButton4,
				hutButton, gereedschapButton, akkerbouwButton, jachtButton, bosButton, leemGroeveButton,
				steenGroeveButton, rivierButton, endTurn, Speler1Image);
	}
}
