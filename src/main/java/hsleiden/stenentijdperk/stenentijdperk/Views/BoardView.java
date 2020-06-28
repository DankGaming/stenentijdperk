package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.Controllers.BoardController;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Kaart;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.StaticHut;
import hsleiden.stenentijdperk.stenentijdperk.Models.BoardModel;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;
import hsleiden.stenentijdperk.stenentijdperk.observers.BoardObservable;
import hsleiden.stenentijdperk.stenentijdperk.observers.BoardObserver;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleToIntFunction;

public class BoardView implements BoardObserver {
	private BoardController controller;
	private ArrayList<Button> beschavingsKaartButtons = new ArrayList<Button>();
	private ArrayList<Button> hutKaartButtons = new ArrayList<Button>();
	private Button toolStapel1;
	private Button toolStapel2;
	private GridPane view = new GridPane();
	private String spelbordImage = "src/main/Resources/Backgrounds/spelbord2.jpg";
	private ImageView imageView;
	private String Speler1 = "src/main/Resources/Spelers/Speler1.png";
	private String Speler2 = "src/main/Resources/Spelers/Speler2.png";
	private String Speler3 = "src/main/Resources/Spelers/Speler3.png";
	private String Speler4 = "src/main/Resources/Spelers/Speler4.png";
	private Label speler1Label;
	private Label speler2Label;
	private Label speler3Label;
	private Label speler4Label;
	private ImageView speler1Image;
	private ImageView speler2Image;
	private ImageView speler3Image;
	private ImageView speler4Image;
	private TextField amountField;
	private Button amountButton;
	private Label amountLabel;
	private Label beurtLabel;
	private int location;

	public BoardView() {
		this.controller = new BoardController();
		setupPane();
	}

	public GridPane setScene() {
		return view;
	}

	private void createToolButton(int stapel) {
		if (stapel == 0) {
			FileInputStream input = null;
			try {
				input = new FileInputStream("src/main/Resources/Tools/Tool1.png");
			} catch (FileNotFoundException fileNotFoundException) {
				System.out.println(fileNotFoundException);
			}

			assert input != null;
			Image image = new Image(input);
			ImageView imageView = new ImageView(image);
			imageView.setFitHeight(48);
			imageView.setPreserveRatio(true);
			this.toolStapel1 = new Button("", imageView);
		} else {
			FileInputStream input = null;
			try {
				input = new FileInputStream("src/main/Resources/Tools/Tool3.png");
			} catch (FileNotFoundException fileNotFoundException) {
				System.out.println(fileNotFoundException);
			}

			assert input != null;
			Image image = new Image(input);
			ImageView imageView = new ImageView(image);
			imageView.setFitHeight(48);
			imageView.setPreserveRatio(true);
			this.toolStapel2 = new Button("", imageView);
		}
	}

	private void createKaartButtons() {
		for (int i = 0; i < 4; i++) { // maakt 4 beschavingskaart buttons
			FileInputStream input = null;
			try {
				input = new FileInputStream(this.controller.getKaart(i).getPath());
			} catch (FileNotFoundException fileNotFoundException) {
				System.out.println(fileNotFoundException);
			}

			assert input != null;
			Image image = new Image(input);
			ImageView imageView = new ImageView(image);
			imageView.setFitHeight(160);
			imageView.setPreserveRatio(true);
			this.beschavingsKaartButtons.add(new Button("", imageView));
		}
	}

	private void createHutStapels() {
		for (int i = 0; i < 4; i++) { // maakt 4 beschavingskaart buttons
			FileInputStream input = null;
			try {
				input = new FileInputStream(this.controller.getHut(i).getPath());
			} catch (FileNotFoundException fileNotFoundException) {
				System.out.println(fileNotFoundException);
			}

			assert input != null;
			Image image = new Image(input);
			ImageView imageView = new ImageView(image);
			imageView.setFitHeight(100);
			imageView.setPreserveRatio(true);
			this.hutKaartButtons.add(new Button("", imageView));
		}
	}

	private void renderNewHutten(List<StaticHut> array, int index) {
		if (array.size() < 1) {
			this.hutKaartButtons.get(index).setVisible(false);
		} else {
			this.hutKaartButtons.get(index).setVisible(true);
			FileInputStream input = null;
			try {
				input = new FileInputStream(array.get(0).getPath());
			} catch (FileNotFoundException fileNotFoundException) {
				System.out.println(fileNotFoundException);
			}

			assert input != null;
			Image image = new Image(input);
			ImageView imageView = new ImageView(image);
			imageView.setFitHeight(100);
			imageView.setPreserveRatio(true);
			this.hutKaartButtons.get(index).setGraphic(imageView);
		}

	}

	// dit kan gebruikt worden als de kaarten worden gekocht in een actie fase
	private void removeKaartButton(int index) {
		this.beschavingsKaartButtons.get(index).setVisible(false);
	}

	private void renderNewKaarten(List<Kaart> array) {
		int kaartAmount = 4;
		if (array.size() < 4) {
			kaartAmount = array.size();
		}
		switch (array.size()) { // kaarten verdwijnen als ze niet aangevuld kunnen worden
			case 3:
				removeKaartButton(3);

				break;
			case 2:
				removeKaartButton(2);

				break;
			case 1:
				removeKaartButton(1);

				break;
			case 0:
				removeKaartButton(0);

				break;
		}
		for (int i = 0; i < kaartAmount; i++) { // maakt 4 beschavingskaart buttons
			this.beschavingsKaartButtons.get(i).setVisible(true);
			FileInputStream input = null;
			try {
				input = new FileInputStream(array.get(i).getPath());
			} catch (FileNotFoundException fileNotFoundException) {
				System.out.println(fileNotFoundException);
			}

			assert input != null;
			Image image = new Image(input);
			ImageView imageView = new ImageView(image);
			imageView.setFitHeight(160);
			imageView.setPreserveRatio(true);
			this.beschavingsKaartButtons.get(i).setGraphic(imageView);
		}
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
		makePlayerToken(speler1Image);

		FileInputStream speler2 = null;
		try {
			speler2 = new FileInputStream(this.Speler2);
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println(fileNotFoundException);
		}

		assert speler2 != null;
		Image Speler2 = new Image(speler2);
		speler2Image = new ImageView(Speler2);
		makePlayerToken(speler2Image);

		FileInputStream speler3 = null;
		try {
			speler3 = new FileInputStream(this.Speler3);
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println(fileNotFoundException);
		}

		assert speler3 != null;
		Image Speler3 = new Image(speler3);
		speler3Image = new ImageView(Speler3);
		makePlayerToken(speler3Image);

		FileInputStream speler4 = null;
		try {
			speler4 = new FileInputStream(this.Speler4);
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println(fileNotFoundException);
		}

		assert speler4 != null;
		Image Speler4 = new Image(speler4);
		speler4Image = new ImageView(Speler4);
		makePlayerToken(speler4Image);

		String styleLabel = "-fx-font-size: 20px; -fx-font-weight: bold";

		// Stamleden hoeveelheden
		speler1Label = new Label("  ");
		speler1Label.setStyle(styleLabel);
		speler1Label.setVisible(false);

		speler2Label = new Label("  ");
		speler2Label.setStyle(styleLabel);
		speler2Label.setVisible(false);

		speler3Label = new Label("  ");
		speler3Label.setStyle(styleLabel);
		speler3Label.setVisible(false);

		speler4Label = new Label("  ");
		speler4Label.setStyle(styleLabel);
		speler4Label.setVisible(false);

		String style = "-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 10px;";

		String textStyle = "-fx-font-size: 25px; -fx-text-fill: #f6e5b6; -fx-background-color: #dfa231; ";
		// invoeren aantal stamleden
		amountLabel = new Label("Hoeveel: ");
		amountLabel.setStyle(textStyle);
		amountLabel.setVisible(false);
		GridPane.setConstraints(amountLabel, 18, 18, 5, 3);

		amountField = new TextField();
		amountField.setVisible(false);
		amountField.setEditable(false);
		GridPane.setConstraints(amountField, 23, 18, 6, 3);

		amountButton = new Button("Verder");
		amountButton.setStyle(style);
		amountButton.setVisible(false);
		GridPane.setConstraints(amountButton, 30, 18, 5, 3);

		// laten zijn welke speler aan de beurt is.
		beurtLabel = new Label(controller.getPlayer().getNaam() + " is aan de beurt.");
		beurtLabel.setStyle(textStyle);
		GridPane.setConstraints(beurtLabel, 2, 3, 50, 3);

		this.createKaartButtons();
		this.createHutStapels();
		this.createToolButton(0);
		this.createToolButton(1);
		// Buttons

		GridPane.setConstraints(this.beschavingsKaartButtons.get(0), 42, 40, 1, 1);
		GridPane.setConstraints(this.beschavingsKaartButtons.get(1), 37, 40, 1, 1);
		GridPane.setConstraints(this.beschavingsKaartButtons.get(2), 31, 40, 1, 1);
		GridPane.setConstraints(this.beschavingsKaartButtons.get(3), 26, 40, 1, 1);

		GridPane.setConstraints(this.hutKaartButtons.get(0), 20, 42, 1, 1);
		GridPane.setConstraints(this.hutKaartButtons.get(1), 15, 42, 1, 1);
		GridPane.setConstraints(this.hutKaartButtons.get(2), 10, 42, 1, 1);
		GridPane.setConstraints(this.hutKaartButtons.get(3), 5, 42, 1, 1);

		GridPane.setConstraints(this.toolStapel1, 28, 29, 1, 1);
		GridPane.setConstraints(this.toolStapel2, 24, 29, 1, 1);

		Button hutButton = new Button("Stamlid hut");
		hutButton.setStyle(style);
		GridPane.setConstraints(hutButton, 18, 32, 5, 1);

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
		GridPane.setConstraints(endTurn, 22, 7, 15, 1);
		// TODO maak dit nog kleiner
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if (actionEvent.getSource() == hutKaartButtons.get(0)) {
					List<StaticHut> array = controller.onHutButtonClick(0); // TODO verplaatsen naar acties
					renderNewHutten(array, 0);

					hutKaart1();
					labelsSetter(8);
				} else if (actionEvent.getSource() == hutKaartButtons.get(1)) {
					List<StaticHut> array = controller.onHutButtonClick(1); // TODO verplaatsen naar acties
					renderNewHutten(array, 1);

					hutKaart2();
					labelsSetter(9);
				} else if (actionEvent.getSource() == hutKaartButtons.get(2)) {
					List<StaticHut> array = controller.onHutButtonClick(2); // TODO verplaatsen naar acties
					renderNewHutten(array, 2);

					hutKaart3();
					labelsSetter(10);
				} else if (actionEvent.getSource() == hutKaartButtons.get(3)) {
					List<StaticHut> array = controller.onHutButtonClick(3); // TODO verplaatsen naar acties
					renderNewHutten(array, 3);

					hutKaart4();
					labelsSetter(11);
				} else if (actionEvent.getSource() == beschavingsKaartButtons.get(0)) {
					List<Kaart> array = controller.onKaartButtonClick(0); // TODO verplaatsen naar acties
					renderNewKaarten(array);

					beschavingsKaart1();
					labelsSetter(12);
				} else if (actionEvent.getSource() == beschavingsKaartButtons.get(1)) {
					List<Kaart> array = controller.onKaartButtonClick(1); // TODO verplaatsen naar acties
					renderNewKaarten(array);

					beschavingsKaart2();
					labelsSetter(13);
				} else if (actionEvent.getSource() == beschavingsKaartButtons.get(2)) {
					List<Kaart> array = controller.onKaartButtonClick(2); // TODO verplaatsen naar acties
					renderNewKaarten(array);

					beschavingsKaart3();
					labelsSetter(14);
				} else if (actionEvent.getSource() == beschavingsKaartButtons.get(3)) {
					List<Kaart> array = controller.onKaartButtonClick(3); // TODO verplaatsen naar acties
					renderNewKaarten(array);

					beschavingsKaart4();
					labelsSetter(15);
				} else if (actionEvent.getSource() == hutButton) {
					hutKaart();
					labelsSetter(6);
				} else if (actionEvent.getSource() == toolStapel1 || actionEvent.getSource() == toolStapel2) {
					gereedschapKaart();
					labelsSetter(7);
				} else if (actionEvent.getSource() == akkerbouwButton) {
					akkerbouwKaart();
					labelsSetter(5);
				} else if (actionEvent.getSource() == jachtButton) {
					jachtKaart();
					labelsSetter(0);
				} else if (actionEvent.getSource() == bosButton) {
					bosKaart();
					labelsSetter(1);
				} else if (actionEvent.getSource() == leemGroeveButton) {
					leemKaart();
					labelsSetter(2);
				} else if (actionEvent.getSource() == steenGroeveButton) {
					steenKaart();
					labelsSetter(3);
				} else if (actionEvent.getSource() == rivierButton) {
					rivierKaart();
					labelsSetter(4);
				} else if (actionEvent.getSource() == endTurn) {
					if (controller.vraagPhase() == 1) {
						controller.endTurn();
					} else {
						controller.EndTurnPhase2();
					}
					beurtLabel.setText(controller.getPlayer().getNaam() + " is aan de beurt.");
				}
			}
		};

		EventHandler<ActionEvent> buttonEvent = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (event.getSource() == amountButton) {
					int aantalStamleden = 0;
					try {
						aantalStamleden = Integer.parseInt(amountField.getText());
						if (controller.stamledenCheck(location, aantalStamleden)) {
							controller.onResourceButtonClick(location, aantalStamleden);
							System.out.println(aantalStamleden);
							setInputVisable(false);
							checkStamleden(location);
						}
					} catch (Exception e) {
						System.out.println("test2");
					}
				}
			}
		};

		hutKaartButtons.get(0).setOnAction(event);
		hutKaartButtons.get(1).setOnAction(event);
		hutKaartButtons.get(2).setOnAction(event);
		hutKaartButtons.get(3).setOnAction(event);
		beschavingsKaartButtons.get(0).setOnAction(event);
		beschavingsKaartButtons.get(1).setOnAction(event);
		beschavingsKaartButtons.get(2).setOnAction(event);
		beschavingsKaartButtons.get(3).setOnAction(event);
		hutButton.setOnAction(event);
		toolStapel1.setOnAction(event);
		toolStapel2.setOnAction(event);
		akkerbouwButton.setOnAction(event);
		jachtButton.setOnAction(event);
		bosButton.setOnAction(event);
		leemGroeveButton.setOnAction(event);
		steenGroeveButton.setOnAction(event);
		rivierButton.setOnAction(event);
		endTurn.setOnAction(event);
		amountButton.setOnAction(buttonEvent);

		this.view.getChildren().addAll(imageView, hutKaartButtons.get(0), hutKaartButtons.get(1),
				hutKaartButtons.get(2), hutKaartButtons.get(3), beschavingsKaartButtons.get(0),
				beschavingsKaartButtons.get(1), beschavingsKaartButtons.get(2), beschavingsKaartButtons.get(3),
				hutButton, toolStapel1, toolStapel2, akkerbouwButton, jachtButton, bosButton, leemGroeveButton,
				steenGroeveButton, rivierButton, endTurn, speler1Image, speler2Image, speler3Image, speler4Image,
				speler1Label, speler2Label, speler3Label, speler4Label, amountField, amountLabel, amountButton,
				beurtLabel);
	}

	private void labelsSetter(int location) {
		checkStamleden(location);
		setSpelersVisable(true);
		if (location < 5) {
			phaseCheck(location);
		} else {
			controller.onButtonClick(location);
		}
		checkStamleden(location);

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

	private void setInputVisable(boolean visable) {
		amountButton.setVisible(visable);

		amountLabel.setVisible(visable);

		amountField.setEditable(visable);
		amountField.setVisible(visable);
	}

	private void hutKaart1() {
		GridPane.setConstraints(speler1Image, 20, 40, 2, 2);
		GridPane.setConstraints(speler1Label, 20, 40, 1, 1);

		GridPane.setConstraints(speler2Image, 22, 40, 2, 2);
		GridPane.setConstraints(speler2Label, 22, 40, 1, 1);

		GridPane.setConstraints(speler3Image, 20, 42, 2, 2);
		GridPane.setConstraints(speler3Label, 20, 42, 1, 1);

		GridPane.setConstraints(speler4Image, 22, 42, 2, 2);
		GridPane.setConstraints(speler4Label, 22, 42, 1, 1);
	}

	private void hutKaart2() {
		GridPane.setConstraints(speler1Image, 15, 40, 2, 2);
		GridPane.setConstraints(speler1Label, 15, 40, 1, 1);

		GridPane.setConstraints(speler2Image, 17, 40, 2, 2);
		GridPane.setConstraints(speler2Label, 17, 40, 1, 1);

		GridPane.setConstraints(speler3Image, 15, 42, 2, 2);
		GridPane.setConstraints(speler3Label, 15, 42, 1, 1);

		GridPane.setConstraints(speler4Image, 17, 42, 2, 2);
		GridPane.setConstraints(speler4Label, 17, 42, 1, 1);
	}

	private void hutKaart3() {
		GridPane.setConstraints(speler1Image, 10, 40, 2, 2);
		GridPane.setConstraints(speler1Label, 10, 40, 1, 1);

		GridPane.setConstraints(speler2Image, 12, 40, 2, 2);
		GridPane.setConstraints(speler2Label, 12, 40, 1, 1);

		GridPane.setConstraints(speler3Image, 10, 42, 2, 2);
		GridPane.setConstraints(speler3Label, 10, 42, 1, 1);

		GridPane.setConstraints(speler4Image, 12, 42, 2, 2);
		GridPane.setConstraints(speler4Label, 12, 42, 1, 1);
	}

	private void hutKaart4() {
		GridPane.setConstraints(speler1Image, 5, 40, 2, 2);
		GridPane.setConstraints(speler1Label, 5, 40, 1, 1);

		GridPane.setConstraints(speler2Image, 7, 40, 2, 2);
		GridPane.setConstraints(speler2Label, 7, 40, 1, 1);

		GridPane.setConstraints(speler3Image, 5, 42, 2, 2);
		GridPane.setConstraints(speler3Label, 5, 42, 1, 1);

		GridPane.setConstraints(speler4Image, 7, 42, 2, 2);
		GridPane.setConstraints(speler4Label, 7, 42, 1, 1);
	}

	private void beschavingsKaart1() {
		GridPane.setConstraints(speler1Image, 43, 36, 2, 2);
		GridPane.setConstraints(speler1Label, 43, 36, 1, 1);

		GridPane.setConstraints(speler2Image, 45, 36, 2, 2);
		GridPane.setConstraints(speler2Label, 45, 36, 1, 1);

		GridPane.setConstraints(speler3Image, 43, 38, 2, 2);
		GridPane.setConstraints(speler3Label, 43, 38, 1, 1);

		GridPane.setConstraints(speler4Image, 45, 38, 2, 2);
		GridPane.setConstraints(speler4Label, 45, 38, 1, 1);
	}

	private void beschavingsKaart2() {
		GridPane.setConstraints(speler1Image, 38, 36, 2, 2);
		GridPane.setConstraints(speler1Label, 38, 36, 1, 1);

		GridPane.setConstraints(speler2Image, 40, 36, 2, 2);
		GridPane.setConstraints(speler2Label, 40, 36, 1, 1);

		GridPane.setConstraints(speler3Image, 38, 38, 2, 2);
		GridPane.setConstraints(speler3Label, 38, 38, 1, 1);

		GridPane.setConstraints(speler4Image, 40, 38, 2, 2);
		GridPane.setConstraints(speler4Label, 40, 38, 1, 1);
	}

	private void beschavingsKaart3() {
		GridPane.setConstraints(speler1Image, 32, 36, 2, 2);
		GridPane.setConstraints(speler1Label, 32, 36, 1, 1);

		GridPane.setConstraints(speler2Image, 34, 36, 2, 2);
		GridPane.setConstraints(speler2Label, 34, 36, 1, 1);

		GridPane.setConstraints(speler3Image, 32, 38, 2, 2);
		GridPane.setConstraints(speler3Label, 32, 38, 1, 1);

		GridPane.setConstraints(speler4Image, 34, 38, 2, 2);
		GridPane.setConstraints(speler4Label, 34, 38, 1, 1);
	}

	private void beschavingsKaart4() {
		GridPane.setConstraints(speler1Image, 27, 36, 2, 2);
		GridPane.setConstraints(speler1Label, 27, 36, 1, 1);

		GridPane.setConstraints(speler2Image, 29, 36, 2, 2);
		GridPane.setConstraints(speler2Label, 29, 36, 1, 1);

		GridPane.setConstraints(speler3Image, 27, 38, 2, 2);
		GridPane.setConstraints(speler3Label, 27, 38, 1, 1);

		GridPane.setConstraints(speler4Image, 29, 38, 2, 2);
		GridPane.setConstraints(speler4Label, 29, 38, 1, 1);
	}

	private void hutKaart() {
		GridPane.setConstraints(speler1Image, 16, 34, 2, 2);
		GridPane.setConstraints(speler1Label, 16, 34, 1, 1);

		GridPane.setConstraints(speler2Image, 18, 34, 2, 2);
		GridPane.setConstraints(speler2Label, 18, 34, 1, 1);

		GridPane.setConstraints(speler3Image, 20, 34, 2, 2);
		GridPane.setConstraints(speler3Label, 20, 34, 1, 1);

		GridPane.setConstraints(speler4Image, 22, 34, 2, 2);
		GridPane.setConstraints(speler4Label, 22, 34, 1, 1);
	}

	private void gereedschapKaart() {
		GridPane.setConstraints(speler1Image, 26, 21, 2, 2);
		GridPane.setConstraints(speler1Label, 26, 21, 1, 1);

		GridPane.setConstraints(speler2Image, 28, 21, 2, 2);
		GridPane.setConstraints(speler2Label, 28, 21, 1, 1);

		GridPane.setConstraints(speler3Image, 26, 23, 2, 2);
		GridPane.setConstraints(speler3Label, 26, 23, 1, 1);

		GridPane.setConstraints(speler4Image, 28, 23, 2, 2);
		GridPane.setConstraints(speler4Label, 28, 23, 1, 1);
	}

	private void akkerbouwKaart() {
		GridPane.setConstraints(speler1Image, 13, 27, 2, 2);
		GridPane.setConstraints(speler1Label, 13, 27, 1, 1);

		GridPane.setConstraints(speler2Image, 15, 27, 2, 2);
		GridPane.setConstraints(speler2Label, 15, 27, 1, 1);

		GridPane.setConstraints(speler3Image, 17, 27, 2, 2);
		GridPane.setConstraints(speler3Label, 17, 27, 1, 1);

		GridPane.setConstraints(speler4Image, 19, 27, 2, 2);
		GridPane.setConstraints(speler4Label, 19, 27, 1, 1);
	}

	private void jachtKaart() {
		GridPane.setConstraints(speler1Image, 5, 11, 2, 2);
		GridPane.setConstraints(speler1Label, 5, 11, 1, 1);

		GridPane.setConstraints(speler2Image, 7, 11, 2, 2);
		GridPane.setConstraints(speler2Label, 7, 11, 1, 1);

		GridPane.setConstraints(speler3Image, 9, 11, 2, 2);
		GridPane.setConstraints(speler3Label, 9, 11, 1, 1);

		GridPane.setConstraints(speler4Image, 11, 11, 2, 2);
		GridPane.setConstraints(speler4Label, 11, 11, 1, 1);
	}

	private void bosKaart() {
		GridPane.setConstraints(speler1Image, 17, 11, 2, 2);
		GridPane.setConstraints(speler1Label, 17, 11, 1, 1);

		GridPane.setConstraints(speler2Image, 19, 11, 2, 2);
		GridPane.setConstraints(speler2Label, 19, 11, 1, 1);

		GridPane.setConstraints(speler3Image, 17, 13, 2, 2);
		GridPane.setConstraints(speler3Label, 17, 13, 1, 1);

		GridPane.setConstraints(speler4Image, 19, 13, 2, 2);
		GridPane.setConstraints(speler4Label, 19, 13, 1, 1);
	}

	private void leemKaart() {
		GridPane.setConstraints(speler1Image, 24, 11, 2, 2);
		GridPane.setConstraints(speler1Label, 24, 11, 1, 1);

		GridPane.setConstraints(speler2Image, 26, 11, 2, 2);
		GridPane.setConstraints(speler2Label, 26, 11, 1, 1);

		GridPane.setConstraints(speler3Image, 28, 11, 2, 2);
		GridPane.setConstraints(speler3Label, 28, 11, 1, 1);

		GridPane.setConstraints(speler4Image, 30, 11, 2, 2);
		GridPane.setConstraints(speler4Label, 30, 11, 1, 1);
	}

	private void steenKaart() {
		GridPane.setConstraints(speler1Image, 42, 11, 2, 2);
		GridPane.setConstraints(speler1Label, 42, 11, 1, 1);

		GridPane.setConstraints(speler2Image, 44, 11, 2, 2);
		GridPane.setConstraints(speler2Label, 44, 11, 1, 1);

		GridPane.setConstraints(speler3Image, 46, 11, 2, 2);
		GridPane.setConstraints(speler3Label, 46, 11, 1, 1);

		GridPane.setConstraints(speler4Image, 44, 13, 2, 2);
		GridPane.setConstraints(speler4Label, 44, 13, 1, 1);
	}

	private void rivierKaart() {
		GridPane.setConstraints(speler1Image, 37, 21, 2, 2);
		GridPane.setConstraints(speler1Label, 37, 21, 1, 1);

		GridPane.setConstraints(speler2Image, 39, 21, 2, 2);
		GridPane.setConstraints(speler2Label, 39, 21, 1, 1);

		GridPane.setConstraints(speler3Image, 37, 23, 2, 2);
		GridPane.setConstraints(speler3Label, 37, 23, 1, 1);

		GridPane.setConstraints(speler4Image, 39, 23, 2, 2);
		GridPane.setConstraints(speler4Label, 39, 23, 1, 1);
	}

	private void phaseCheck(int location) {
		this.location = location;
		setSpelersVisable(true);
		if (controller.vraagPhase() == 1) {
			setInputVisable(true);
		} else {
			controller.resolveResource(location);
		}
	}

	private void checkStamleden(int location) {

		for (int i = 0; i < 4; i++) {
			int stamledenGeplaats = controller.getPlayers().get(i).getPositie(location);
			if (i == 0) {
				speler1Label.setText("" + stamledenGeplaats);
			}

			else if (i == 1) {
				speler2Label.setText("" + stamledenGeplaats);
			}

			else if (i == 2) {
				speler3Label.setText("" + stamledenGeplaats);
			}

			else if (i == 3) {
				speler4Label.setText("" + stamledenGeplaats);
			}
		}
	}

	private void makePlayerToken(ImageView speler) {
		speler.setFitHeight(30);
		speler.setFitWidth(30);
		speler.setVisible(false);
	}

	@Override
	public void update(BoardObservable boardobserver) {

	}

	// Voor de ViewManager.
	public ArrayList<PlayerModel> getPlayers() {
		return this.controller.getPlayers();
	}
}
