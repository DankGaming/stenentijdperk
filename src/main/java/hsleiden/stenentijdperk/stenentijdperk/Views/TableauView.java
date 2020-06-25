package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.App;
import hsleiden.stenentijdperk.stenentijdperk.Controllers.BoardController;
import hsleiden.stenentijdperk.stenentijdperk.Controllers.TableauController;
import hsleiden.stenentijdperk.stenentijdperk.Managers.ViewManager;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;
import hsleiden.stenentijdperk.stenentijdperk.observers.PlayerObservable;
import hsleiden.stenentijdperk.stenentijdperk.observers.PlayerObserver;
import hsleiden.stenentijdperk.stenentijdperk.observers.TableauObservable;
import hsleiden.stenentijdperk.stenentijdperk.observers.TableauObserver;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Tool;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.transform.Shear;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class TableauView implements PlayerObserver {
    Hutview hutview1 = new Hutview(1);
    Hutview hutview2 = new Hutview(2);
    Hutview hutview3 = new Hutview(3);
    Hutview hutview4 = new Hutview(2);
    Hutview hutview5 = new Hutview(4);
    private ImageView[] huttegels;
    GereedschapView gereedschapview = null;
    GereedschapView gereedschapview1 = null;
    GereedschapView gereedschapview2 = null;

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
    private TableauController tableauController;
    private Label multiplier1;
    private Label multiplier2;
    private Label multiplier3;
    private Label multiplier4;

    // Standard constructor
    public TableauView(PlayerModel playermodel) {
        setupPane();
        this.playerModel = playermodel;
        this.playerModel.registerObserver(this);
    }

    // Constructor for the boardcontroller
    public TableauView(PlayerModel playermodel, BoardController boardController) {
        setupPane();
        this.playerModel = playermodel;
        this.playerModel.registerObserver(this);
        showConfirmButton(boardController);
    }

    private void showConfirmButton(BoardController boardController) {
        Button button = new Button("Gebruiken");
        button.setOnMouseClicked(event -> {
            boardController.test();
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

        Image image = null;

        try {
            image = new Image(String.valueOf(App.class.getResource(this.resource).toURI()));
        }catch(URISyntaxException e) {
            e.printStackTrace();
        }

        assert image != null;
        this.tableau = new ImageView(image);
        this.tableau.setFitWidth(680);
        this.tableau.setFitHeight(460);
        GridPane.setConstraints(tableau, 0, 0, 50, 50);

        stamleden = new Label("Aantal stamleden: 0");
        stamleden.setStyle("-fx-font-size: 30px;");
        GridPane.setConstraints(stamleden, 11, 9, 22, 1);

        voedsel = new Label("0");
        voedsel.setStyle("-fx-font-size: 25px;");
        GridPane.setConstraints(voedsel, 12, 18, 1, 1);

        hout = new Label("0");
        hout.setStyle("-fx-font-size: 25px;");
        GridPane.setConstraints(hout, 17, 18, 1, 1);

        leem = new Label("0");
        leem.setStyle("-fx-font-size: 25px;");
        GridPane.setConstraints(leem, 21, 18, 1, 1);

        steen = new Label("0");
        steen.setStyle("-fx-font-size: 25px;");
        GridPane.setConstraints(steen, 25, 18, 1, 1);

        goud = new Label("0");
        goud.setStyle("-fx-font-size: 25px;");
        GridPane.setConstraints(goud, 30, 18, 1, 1);

        multiplier1 = new Label("0");
        multiplier1.setStyle("-fx-font-size: 25px;");
        GridPane.setConstraints(multiplier1, 42, 7, 1, 1);

        multiplier2 = new Label("0");
        multiplier2.setStyle("-fx-font-size: 25px;");
        GridPane.setConstraints(multiplier2, 42, 13, 1, 1);

        multiplier3 = new Label("0");
        multiplier3.setStyle("-fx-font-size: 25px;");
        GridPane.setConstraints(multiplier3, 42, 19, 1, 1);

        multiplier4 = new Label("0");
        multiplier4.setStyle("-fx-font-size: 25px;");
        GridPane.setConstraints(multiplier4, 42, 25, 1, 1);

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

        this.view.getChildren().addAll(tableau, stamleden, voedsel, hout, leem, steen, goud, imageviewhutkaart1, imageviewhutkaart2,
                imageviewhutkaart3, imageviewhutkaart4, imageviewhutkaart5, punt, multiplier1, multiplier2, multiplier3,
                multiplier4);
    }

    public void setPoint(int height) {
        int[] rows = new int[] {6, 9, 12, 14, 17, 19, 22, 25};
        GridPane.setConstraints(this.punt, 37, rows[height-1], 5, 5);
    }

    public void addImageViewToView(int positie, ImageView imageView) {
        int[][] allConstraints = new int[][] { { 2, 1 }, { 2, 11 }, { 2, 21 } };

        int[] constraints = allConstraints[positie - 1];

        GridPane.setConstraints(imageView, constraints[0], constraints[1], 10, 10);
        this.view.getChildren().add(imageView);
    }

    public void createGereedschap(int positie, int waarde, boolean status) {
        ImageView imageView = null;
        System.out.println(waarde);
        switch (positie) {
            case 1:
                System.out.println("Hi");
                this.gereedschapview = new GereedschapView(waarde, status);
                imageView = this.gereedschapview.setScene();
                break;
            case 2:
                this.gereedschapview1 = new GereedschapView(waarde, status);
                imageView = this.gereedschapview1.setScene();
                break;
            case 3:
                this.gereedschapview2 = new GereedschapView(waarde, status);
                imageView = this.gereedschapview2.setScene();
                break;
        }
        System.out.println("bYE");
        addImageViewToView(positie, imageView);     
    }

    @Override
    public void update(PlayerObservable po) {
        ArrayList<Tool> tools = po.getTools();
        //for (int i = 0; i < tools.size(); i++) {
         //   createGereedschap(i + 1, gereedschap[i]);
        int i = 1;
        tools.add(new Tool());
        System.out.println(tools.get(0).getLevel());
        System.out.println(tools.get(0).getStatus());
        for (Tool tool: tools) {
            createGereedschap(i, tool.getLevel(), tool.getStatus());;
            i++;
        }
    }
}
