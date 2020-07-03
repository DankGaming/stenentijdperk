package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.Controllers.BoardController;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Beschavingskaarten.Kaart;
import hsleiden.stenentijdperk.stenentijdperk.Managers.ViewManager;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.StaticHut;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BoardView implements BoardObserver {
	private BoardController controller;
	private ArrayList<Button> beschavingsKaartButtons;
	private ArrayList<Button> hutKaartButtons;
	private Button toolStapel1;
	private Button toolStapel2;
	private GridPane view;
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
	private ImageView speler1Token;
	private ImageView speler2Token;
	private ImageView speler3Token;
	private ImageView speler4Token;
	private TextField amountField;
	private Button amountButton;
	private Label amountLabel;
	private Label beurtLabel;
	private Label spelerNaam1;
	private Label spelerNaam2;
	private Label spelerNaam3;
	private Label spelerNaam4;
	private Label spelerPunten1;
	private Label spelerPunten2;
	private Label spelerPunten3;
	private Label spelerPunten4;
	private int location;
	private List<ImageView> imageViews;
	private List<Label> labels;

	public BoardView() {
		this.controller = new BoardController();
		this.controller.registerObserver(this);
		this.view = new GridPane();
		this.beschavingsKaartButtons = new ArrayList<>();
		this.imageViews = new ArrayList<>();
		this.hutKaartButtons = new ArrayList<Button>();
		this.labels = new ArrayList<>();

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
				Image image = new Image(input);
				ImageView imageView = new ImageView(image);
				imageView.setFitHeight(48);
				imageView.setPreserveRatio(true);
				this.toolStapel1 = new Button("", imageView);
			} catch (FileNotFoundException fileNotFoundException) {
				System.out.println(fileNotFoundException);
			} finally {
				closeFileStream(input);
			}

		} else {
			FileInputStream input = null;
			try {
				input = new FileInputStream("src/main/Resources/Tools/Tool3.png");
				Image image = new Image(input);
				ImageView imageView = new ImageView(image);
				imageView.setFitHeight(48);
				imageView.setPreserveRatio(true);
				this.toolStapel2 = new Button("", imageView);
			} catch (FileNotFoundException fileNotFoundException) {
				System.out.println(fileNotFoundException);
			} finally {
				closeFileStream(input);
			}
		}
	}

	private void createKaartButtons() {
		for (int i = 0; i < 4; i++) { // maakt 4 beschavingskaart buttons
			FileInputStream input = null;
			try {
				input = new FileInputStream(this.controller.getKaart(i).getPath());
				Image image = new Image(input);
				ImageView imageView = new ImageView(image);
				imageView.setFitHeight(160);
				imageView.setPreserveRatio(true);
				this.beschavingsKaartButtons.add(new Button("", imageView));
			} catch (FileNotFoundException fileNotFoundException) {
				System.out.println(fileNotFoundException);
			} finally {
				closeFileStream(input);
			}
		}
	}

	private void createHutStapels() {
		for (int i = 0; i < 4; i++) { // maakt 4 hutkaart buttons
			FileInputStream input = null;
			try {
				input = new FileInputStream(this.controller.getHut(i).getPath());
				Image image = new Image(input);
				ImageView imageView = new ImageView(image);
				imageView.setFitHeight(100);
				imageView.setPreserveRatio(true);
				this.hutKaartButtons.add(new Button("", imageView));
			} catch (FileNotFoundException fileNotFoundException) {
				System.out.println(fileNotFoundException);
			} finally {
				closeFileStream(input);
			}
		}
	}

	private void renderNewHutten() {
		for (int i = 0; i < 4; i++) { // maakt 4 hutkaart buttons
			List<StaticHut> hutStapel = controller.getHutStapel(i);
			if (hutStapel.size() < 1) {
				this.hutKaartButtons.get(i).setVisible(false);
			} else {
				this.hutKaartButtons.get(i).setVisible(true);
				FileInputStream input = null;
				try {
					input = new FileInputStream(hutStapel.get(0).getPath());
					Image image = new Image(input);
					ImageView imageView = new ImageView(image);
					imageView.setFitHeight(100);
					imageView.setPreserveRatio(true);
					this.hutKaartButtons.get(i).setGraphic(imageView);
				} catch (FileNotFoundException fileNotFoundException) {
					System.out.println(fileNotFoundException);
				} finally {
					closeFileStream(input);
				}
			}
		}
	}

	private void removeKaartButton(int index) {
		this.beschavingsKaartButtons.get(index).setVisible(false);
	}

	private void removeHutButton(int index) {
		this.hutKaartButtons.get(index).setVisible(false);
	}

	private void renderNewKaarten() {
		List<Kaart> array = controller.getKaarten();
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
				Image image = new Image(input);
				ImageView imageView = new ImageView(image);
				imageView.setFitHeight(160);
				imageView.setPreserveRatio(true);
				this.beschavingsKaartButtons.get(i).setGraphic(imageView);
			} catch (FileNotFoundException fileNotFoundException) {
				System.out.println(fileNotFoundException);
			} finally {
				closeFileStream(input);
			}
		}
	}

	private void renderPunten() {
		this.spelerPunten1.setText(String.valueOf(controller.getPlayers().get(0).getPunten()));
		this.spelerPunten2.setText(String.valueOf(controller.getPlayers().get(1).getPunten()));
		this.spelerPunten3.setText(String.valueOf(controller.getPlayers().get(2).getPunten()));
		this.spelerPunten4.setText(String.valueOf(controller.getPlayers().get(3).getPunten()));
	}

	private void closeFileStream(FileInputStream input) {
		if (input != null) {
			try {
				input.close();
			} catch (IOException ioe) {
				System.out.println("I/O error" + ioe.getMessage());
			}
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
			Image image = new Image(input);
			this.imageView = new ImageView(image);
			this.imageView.setFitHeight(800);
			this.imageView.setFitWidth(1200);
			GridPane.setConstraints(imageView, 0, 0, 50, 50);
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println(fileNotFoundException);
		} finally {
			closeFileStream(input);
		}

		// Speler stamleden
		FileInputStream speler1 = null;
		try {
			speler1 = new FileInputStream(this.Speler1);
			Image Speler1 = new Image(speler1);
			speler1Image = new ImageView(Speler1);
			speler1Token = new ImageView(Speler1);
			imageViews.add(speler1Image);
			imageViews.add(speler1Token);
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println(fileNotFoundException);
		} finally {
			closeFileStream(input);
		}

		FileInputStream speler2 = null;
		try {
			speler2 = new FileInputStream(this.Speler2);
			Image Speler2 = new Image(speler2);
			speler2Image = new ImageView(Speler2);
			speler2Token = new ImageView(Speler2);
			imageViews.add(speler2Image);
			imageViews.add(speler2Token);
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println(fileNotFoundException);
		} finally {
			closeFileStream(input);
		}

		FileInputStream speler3 = null;
		try {
			speler3 = new FileInputStream(this.Speler3);
			Image Speler3 = new Image(speler3);
			speler3Image = new ImageView(Speler3);
			speler3Token = new ImageView(Speler3);
			imageViews.add(speler3Image);
			imageViews.add(speler3Token);
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println(fileNotFoundException);
		} finally {
			closeFileStream(input);
		}

		FileInputStream speler4 = null;
		try {
			speler4 = new FileInputStream(this.Speler4);
			Image Speler4 = new Image(speler4);
			speler4Image = new ImageView(Speler4);
			speler4Token = new ImageView(Speler4);
			imageViews.add(speler4Image);
			imageViews.add(speler4Token);
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println(fileNotFoundException);
		} finally {
			closeFileStream(input);
		}

		String styleLabel = "-fx-font-size: 20px; -fx-font-weight: bold";

		// Stamleden hoeveelheden
		speler1Label = new Label("  ");
		speler1Label.setStyle(styleLabel);
		speler1Label.setVisible(false);
		labels.add(speler1Label);

		speler2Label = new Label("  ");
		speler2Label.setStyle(styleLabel);
		speler2Label.setVisible(false);
		labels.add(speler2Label);

		speler3Label = new Label("  ");
		speler3Label.setStyle(styleLabel);
		speler3Label.setVisible(false);
		labels.add(speler3Label);

		speler4Label = new Label("  ");
		speler4Label.setStyle(styleLabel);
		speler4Label.setVisible(false);
		labels.add(speler4Label);

		makePlayerToken();

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

		String spelerNaamLabels = "-fx-font-size: 20px; -fx-font-weight: 700;";

		// Speler Namen
		spelerNaam1 = new Label(controller.getPlayers().get(0).getNaam());
		spelerNaam1.setStyle(spelerNaamLabels);
		GridPane.setConstraints(spelerNaam1, 44, 3, 3, 1);

		spelerNaam2 = new Label(controller.getPlayers().get(1).getNaam());
		spelerNaam2.setStyle(spelerNaamLabels);
		GridPane.setConstraints(spelerNaam2, 44, 5, 3, 1);

		spelerNaam3 = new Label(controller.getPlayers().get(2).getNaam());
		spelerNaam3.setStyle(spelerNaamLabels);
		GridPane.setConstraints(spelerNaam3, 44, 7, 3, 1);

		spelerNaam4 = new Label(controller.getPlayers().get(3).getNaam());
		spelerNaam4.setStyle(spelerNaamLabels);
		GridPane.setConstraints(spelerNaam4, 44, 9, 3, 1);

		// Speler Punten
		spelerPunten1 = new Label("" + controller.getPlayers().get(0).getPunten());
		spelerPunten1.setStyle(spelerNaamLabels);
		GridPane.setConstraints(spelerPunten1, 47, 3, 3, 1);

		spelerPunten2 = new Label("" + controller.getPlayers().get(1).getPunten());
		spelerPunten2.setStyle(spelerNaamLabels);
		GridPane.setConstraints(spelerPunten2, 47, 5, 3, 1);

		spelerPunten3 = new Label("" + controller.getPlayers().get(2).getPunten());
		spelerPunten3.setStyle(spelerNaamLabels);
		GridPane.setConstraints(spelerPunten3, 47, 7, 3, 1);

		spelerPunten4 = new Label("" + controller.getPlayers().get(3).getPunten());
		spelerPunten4.setStyle(spelerNaamLabels);
		GridPane.setConstraints(spelerPunten4, 47, 9, 3, 1);

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

		Button akkerbouwButton = new Button("Akkerbouw");
		akkerbouwButton.setStyle(style);
		GridPane.setConstraints(akkerbouwButton, 15, 25, 5, 1);

		Button jachtButton = new Button("Stamlid jachtvlakte");
		jachtButton.setStyle(style);
		GridPane.setConstraints(jachtButton, 5, 7, 5, 1);

		Button bosButton = new Button("Stamlid bos");
		bosButton.setStyle(style);
		GridPane.setConstraints(bosButton, 21, 15, 5, 1);

		Button leemGroeveButton = new Button("Stamlid leem");
		leemGroeveButton.setStyle(style);
		GridPane.setConstraints(leemGroeveButton, 28, 15, 5, 1);

		Button steenGroeveButton = new Button("Stamlid steen");
		steenGroeveButton.setStyle(style);
		GridPane.setConstraints(steenGroeveButton, 40, 15, 5, 1);

		Button rivierButton = new Button("Stamlid rivier");
		rivierButton.setStyle(style);
		GridPane.setConstraints(rivierButton, 41, 25, 5, 1);

		Button endTurn = new Button("Beurt eindigen");
		endTurn.setStyle(style);
		GridPane.setConstraints(endTurn, 22, 7, 15, 1);

		playerColor(true);
		// TODO maak dit nog kleiner
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if (actionEvent.getSource() == hutKaartButtons.get(0)) {
					makeConstraints(20, 40, 2);
					labelsSetter(8);
				} else if (actionEvent.getSource() == hutKaartButtons.get(1)) {
					makeConstraints(15, 40, 2);
					labelsSetter(9);
				} else if (actionEvent.getSource() == hutKaartButtons.get(2)) {
					makeConstraints(10, 40, 2);
					labelsSetter(10);
				} else if (actionEvent.getSource() == hutKaartButtons.get(3)) {
					makeConstraints(5, 40, 2);
					labelsSetter(11);
				} else if (actionEvent.getSource() == beschavingsKaartButtons.get(0)) {
					makeConstraints(43, 36, 2);
					labelsSetter(12);
				} else if (actionEvent.getSource() == beschavingsKaartButtons.get(1)) {
					makeConstraints(38, 36, 2);
					labelsSetter(13);
				} else if (actionEvent.getSource() == beschavingsKaartButtons.get(2)) {
					makeConstraints(32, 36, 2);
					labelsSetter(14);
				} else if (actionEvent.getSource() == beschavingsKaartButtons.get(3)) {
					makeConstraints(27, 36, 2);
					labelsSetter(15);
				} else if (actionEvent.getSource() == hutButton) {
					makeConstraints(16, 34, 1);
					labelsSetter(6);
				} else if (actionEvent.getSource() == toolStapel1 || actionEvent.getSource() == toolStapel2) {
					makeConstraints(26, 21, 2);
					labelsSetter(7);
				} else if (actionEvent.getSource() == akkerbouwButton) {
					makeConstraints(13, 27, 1);
					labelsSetter(5);
				} else if (actionEvent.getSource() == jachtButton) {
					makeConstraints(5, 11, 1);
					labelsSetter(0);
				} else if (actionEvent.getSource() == bosButton) {
					makeConstraints(17, 11, 2);
					labelsSetter(1);
				} else if (actionEvent.getSource() == leemGroeveButton) {
					makeConstraints(24, 11, 1);
					labelsSetter(2);
				} else if (actionEvent.getSource() == steenGroeveButton) {
					makeConstraints(42, 11, 3);
					labelsSetter(3);
				} else if (actionEvent.getSource() == rivierButton) {
					makeConstraints(37, 21, 2);
					labelsSetter(4);
				} else if (actionEvent.getSource() == endTurn) {
					playerColor(false);
					if (controller.vraagPhase() == 1) {
						controller.endTurn();
					} else {
						boolean einde = controller.EndTurnPhase2();
						if (einde) {
							ViewManager.loadGameOverView(controller.getPlayers());
						};
					}
					beurtLabel.setText(controller.getPlayer().getNaam() + " is aan de beurt.");
					playerColor(true);
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
							setInputVisable(false);
							checkStamleden(location);
						}
					} catch (Exception e) {
						System.out.println(e);
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
				speler1Label, speler2Label, speler3Label, speler4Label, speler1Token, speler2Token, speler3Token,
				speler4Token, amountField, amountLabel, amountButton, beurtLabel, spelerNaam1, spelerNaam2, spelerNaam3,
				spelerNaam4, spelerPunten1, spelerPunten2, spelerPunten3, spelerPunten4);
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

	private void playerColor(boolean seen) {
		int i = 1;
		for (PlayerModel player : controller.getPlayers()) {
			if (player.equals(controller.getPlayer())) {
				GridPane.setConstraints(imageViews.get(i), 2, 6, 1, 1);
				imageViews.get(i).setVisible(seen);
			}
			i += 2;
		}
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

	private void makeConstraints(int x, int y, int buttontype) {
		GridPane.setConstraints(imageViews.get(0), x, y, 2, 2);
		GridPane.setConstraints(labels.get(0), x, y, 1, 1);
		GridPane.setConstraints(imageViews.get(2), x + 2, y, 2, 2);
		GridPane.setConstraints(labels.get(1), x + 2, y, 1, 1);
		if (buttontype != 2) {
			GridPane.setConstraints(imageViews.get(4), x + 4, y, 2, 2);
			GridPane.setConstraints(labels.get(2), x + 4, y, 1, 1);
		} else {
			GridPane.setConstraints(imageViews.get(4), x, y + 2, 2, 2);
			GridPane.setConstraints(labels.get(2), x, y + 2, 1, 1);
		}
		if (buttontype != 1) {
			GridPane.setConstraints(imageViews.get(6), x + 2, y + 2, 2, 2);
			GridPane.setConstraints(labels.get(3), x + 2, y + 2, 1, 1);
		} else {
			GridPane.setConstraints(imageViews.get(6), x + 6, y, 2, 2);
			GridPane.setConstraints(labels.get(3), x + 6, y, 1, 1);
		}
	}

	private void phaseCheck(int location) {
		this.location = location;
		setSpelersVisable(true);
		if (controller.vraagPhase() == 1) {
			setInputVisable(true);
		} else {
			controller.resolveResource(location);
			checkStamleden(location);
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

	private void makePlayerToken() {
		for (ImageView imageview : imageViews) {
			imageview.setFitHeight(30);
			imageview.setFitWidth(30);
			imageview.setVisible(false);
		}
	}

	@Override
	public void update(BoardObservable boardobserver) {
		renderNewHutten();
		renderNewKaarten();
		renderPunten();
	}

	@Override
	public void updatePunten(BoardObservable boardobserver) {
		renderPunten();
	}

	@Override
	public void removeKaart(BoardObservable boardobserver, int index) {
		removeKaartButton(index);
	}

	@Override
	public void removeHut(BoardObservable boardobserver, int index) {
		removeHutButton(index);
	}

	// Voor de ViewManager.
	public ArrayList<PlayerModel> getPlayers() {
		return this.controller.getPlayers();
	}
}
