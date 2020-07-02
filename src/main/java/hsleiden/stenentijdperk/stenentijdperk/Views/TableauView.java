package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.App;
import hsleiden.stenentijdperk.stenentijdperk.Controllers.BoardController;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Tool;
import hsleiden.stenentijdperk.stenentijdperk.Managers.ViewManager;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;
import hsleiden.stenentijdperk.stenentijdperk.observers.PlayerObservable;
import hsleiden.stenentijdperk.stenentijdperk.observers.PlayerObserver;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class TableauView implements PlayerObserver {
    public int waarde;
    List<Integer> recources;
    Hutview hutview1 = new Hutview(1);
    Hutview hutview2 = new Hutview(2);
    Hutview hutview3 = new Hutview(3);
    Hutview hutview4 = new Hutview(2);
    Hutview hutview5 = new Hutview(4);
    GereedschapView gereedschapview = null;
    GereedschapView gereedschapview1 = null;
    GereedschapView gereedschapview2 = null;
    private ImageView[] huttegels;
    private ImageView tableau;
    private String resource = "/Backgrounds/tableau.png";
    private GridPane view;
    private Label voedsel;
    private Label hout;
    private Label leem;
    private Label steen;
    private Label goud;
    private Label stamleden;
    private Label punt;
    private PlayerModel playerModel;
    private Label gegooid;
    private int worp = 0;

    private ArrayList<Label> multipliers;

    // Standard constructor
    public TableauView(PlayerModel playerModel) {
        standardConstructorFunction(playerModel);
    }

    // Constructor for the boardcontroller
    public TableauView(PlayerModel playermodel, BoardController boardController, int worp) {
        this.worp = worp;
        standardConstructorFunction(playermodel);
        showConfirmButton(boardController);
    }

    // The statements that are called every time
    public void standardConstructorFunction(PlayerModel playermodel) {
        this.multipliers = new ArrayList<Label>();
        setupPane();
        this.playerModel = playermodel;
        this.playerModel.registerObserver(this);
    }

    private void showConfirmButton(BoardController boardController) {
        Button button = new Button("Gebruiken");
        button.setOnMouseClicked(event -> {
            boardController.toolsGebruiken(waarde);
            ViewManager.closePopupWindow();
        });
        String style = "-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 10px;";
        button.setStyle(style);
        GridPane.setConstraints(button, 12, 3, 6, 3);
        this.view.getChildren().add(button);
    }

    public GridPane setScene() {
        return view;
    }

    public void setupPane() {
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

        Label multiplier1;
        Label multiplier2;
        Label multiplier3;
        Label multiplier4;

        Image image = null;

        try {
            image = new Image(String.valueOf(App.class.getResource(this.resource).toURI()));
            this.tableau = new ImageView(image);
            this.tableau.setFitWidth(680);
            this.tableau.setFitHeight(460);
            GridPane.setConstraints(tableau, 0, 0, 50, 50);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        stamleden = new Label("Aantal stamleden: 0");
        stamleden.setStyle("-fx-font-size: 30px;");
        GridPane.setConstraints(stamleden, 11, 9, 22, 1);

        voedsel = new Label("0");
        voedsel.setStyle("-fx-font-size: 25px;");
        GridPane.setConstraints(voedsel, 12, 18, 2, 1);

        hout = new Label("0");
        hout.setStyle("-fx-font-size: 25px;");
        GridPane.setConstraints(hout, 17, 18, 2, 1);

        leem = new Label("0");
        leem.setStyle("-fx-font-size: 25px;");
        GridPane.setConstraints(leem, 21, 18, 2, 1);

        steen = new Label("0");
        steen.setStyle("-fx-font-size: 25px;");
        GridPane.setConstraints(steen, 25, 18, 2, 1);

        goud = new Label("0");
        goud.setStyle("-fx-font-size: 25px;");
        GridPane.setConstraints(goud, 30, 18, 2, 1);

        multiplier1 = new Label("0");
        multiplier1.setStyle("-fx-font-size: 25px;");
        GridPane.setConstraints(multiplier1, 42, 7, 2, 1);

        multiplier2 = new Label("0");
        multiplier2.setStyle("-fx-font-size: 25px;");
        GridPane.setConstraints(multiplier2, 42, 13, 2, 1);

        multiplier3 = new Label("0");
        multiplier3.setStyle("-fx-font-size: 25px;");
        GridPane.setConstraints(multiplier3, 42, 19, 2, 1);

        multiplier4 = new Label("0");
        multiplier4.setStyle("-fx-font-size: 25px;");
        GridPane.setConstraints(multiplier4, 42, 25, 2, 1);

        gegooid = new Label("");
        gegooid.setStyle("-fx-font-size: 25px;");
        GridPane.setConstraints(gegooid, 9, 12, 22, 1);
        gegooid.setVisible(false);

        if (worp != 0) {
            gegooid.setText("De worp was " + worp + ":");
            gegooid.setVisible(true);
        }

        this.multipliers.add(multiplier1);
        this.multipliers.add(multiplier2);
        this.multipliers.add(multiplier3);
        this.multipliers.add(multiplier4);

        ImageView imageviewhutkaart1 = hutview1.setScene();
        GridPane.setConstraints(imageviewhutkaart1, 2, 36, 10, 10);

        ImageView imageviewhutkaart2 = hutview2.setScene();
        GridPane.setConstraints(imageviewhutkaart2, 12, 36, 10, 10);

        ImageView imageviewhutkaart3 = hutview3.setScene();
        GridPane.setConstraints(imageviewhutkaart3, 21, 36, 10, 10);

        ImageView imageviewhutkaart4 = hutview4.setScene();
        GridPane.setConstraints(imageviewhutkaart4, 31, 36, 10, 10);

        ImageView imageviewhutkaart5 = hutview5.setScene();
        GridPane.setConstraints(imageviewhutkaart5, 40, 36, 10, 10);

        this.punt = new Label("⬤");
        this.punt.setStyle("-fx-font-size: 12px");

        // Initial constraints for the point
        GridPane.setConstraints(this.punt, 37, 6, 5, 5);

        this.view.getChildren().addAll(tableau, stamleden, voedsel, hout, leem, steen, goud, imageviewhutkaart1,
                imageviewhutkaart2, imageviewhutkaart3, imageviewhutkaart4, imageviewhutkaart5, punt,
                this.multipliers.get(0), this.multipliers.get(1), this.multipliers.get(2), this.multipliers.get(3),
                gegooid);
    }

    public void setPoint(int height) {
        int[] rows = new int[] { 6, 9, 12, 14, 17, 19, 22, 25 };
        if (height > 0) {
            this.punt.setVisible(true);
            GridPane.setConstraints(this.punt, 37, rows[height - 1], 5, 5);
        } else {
            this.punt.setVisible(false);
        }
    }

    public void addImageViewToView(int positie, ImageView imageView) {
        int[][] allConstraints = new int[][] { { 2, 1 }, { 2, 11 }, { 2, 21 } };

        int[] constraints = allConstraints[positie - 1];

        GridPane.setConstraints(imageView, constraints[0], constraints[1], 10, 10);
        this.view.getChildren().add(imageView);
    }

    public void createGereedschap(int positie, Tool tool) {
        ImageView imageView = null;
        switch (positie) {
            case 1:
                this.gereedschapview = new GereedschapView(tool, this);
                imageView = this.gereedschapview.setScene();
                break;
            case 2:
                this.gereedschapview1 = new GereedschapView(tool, this);
                imageView = this.gereedschapview1.setScene();
                break;
            case 3:
                this.gereedschapview2 = new GereedschapView(tool, this);
                imageView = this.gereedschapview2.setScene();
                break;
        }
        addImageViewToView(positie, imageView);
    }

    private void updateTools(PlayerObservable po) {
        ArrayList<Tool> tools = po.getTools();
        int i = 1;
        for (Tool tool : tools) {
            createGereedschap(i, tool);
            i++;
        }
    }

    private void updateStamleden(PlayerObservable po) {
        if (this.stamleden != null)
            this.stamleden.setText("Aantal stamleden: " + po.getVillagers());
    }

    private void updateVoedsel() {
        if (this.voedsel != null)
            this.voedsel.setText(String.valueOf(this.recources.get(0)));
    }

    private void updateHout() {
        if (this.hout != null)
            this.hout.setText(String.valueOf(this.recources.get(1)));
    }

    private void updateLeem() {
        if (this.leem != null)
            this.leem.setText(String.valueOf(this.recources.get(2)));
    }

    private void updateSteen() {
        if (this.steen != null)
            this.steen.setText(String.valueOf(this.recources.get(3)));
    }

    private void updateGoud() {
        if (this.goud != null)
            this.goud.setText(String.valueOf(this.recources.get(4)));
    }

    private void updateTreasures(PlayerObservable po) {
        setPoint(po.getTreasures().size());
    }

    private void updateMultipliers(PlayerObservable po) {
        for (int i = 0; i < multipliers.size(); i++) {
            this.multipliers.get(i).setText(String.valueOf(po.getMultiplier().get(i)));
        }
    }

    @Override
    public void update(PlayerObservable po) {
        this.recources = po.getResources();

        updateTools(po);
        updateStamleden(po);
        updateVoedsel();
        updateHout();
        updateLeem();
        updateSteen();
        updateGoud();
        updateTreasures(po);
        updateMultipliers(po);
    }
}
