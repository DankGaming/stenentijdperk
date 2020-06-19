package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.Controllers.LoginController;
import hsleiden.stenentijdperk.stenentijdperk.Controllers.TableauController;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;
import hsleiden.stenentijdperk.stenentijdperk.Models.TableauModel;
import hsleiden.stenentijdperk.stenentijdperk.observers.TableauObservable;
import hsleiden.stenentijdperk.stenentijdperk.observers.TableauObserver;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TableauView implements TableauObserver {
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
    private String RESOURCE = "./Images/tableau.png";
    private GridPane view;
    private Label voedsel;
    private Label hout;
    private Label leem;
    private Label steen;
    private Label goud;
    private Label stamleden;

    private PlayerModel playerModel;
    private TableauController tableauController;

    public TableauView(PlayerModel playermodel) {
        this.playerModel = playermodel;
        setupPane();
        this.tableauController = new TableauController();
        this.tableauController.registerObserver(this);
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

        FileInputStream input = null;
        try {
            input = new FileInputStream(this.RESOURCE);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException);
        }

        assert input != null;
        Image image = new Image(input);
        this.tableau = new ImageView(image);
        this.tableau.setFitWidth(680);
        this.tableau.setFitHeight(460);
        GridPane.setConstraints(tableau, 0, 0, 50, 50);

        stamleden = new Label("Aantal stamleden: 0");
        stamleden.setStyle("-fx-font-size: 22px;");
        GridPane.setConstraints(stamleden, 15, 9, 18, 1);

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

        this.view.getChildren().addAll(tableau, stamleden, voedsel, hout, leem, steen, goud);

        ImageView imageviewgereedschap1 = gereedschapview1.setScene();
        GridPane.setConstraints(imageviewgereedschap1, 2, 1, 10, 10);

        ImageView imageviewgereedschap2 = gereedschapview2.setScene();
        GridPane.setConstraints(imageviewgereedschap2, 2, 11, 10, 10);

        ImageView imageviewgereedschap3 = gereedschapview3.setScene();
        GridPane.setConstraints(imageviewgereedschap3, 2, 21, 10, 10);

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

        this.view.getChildren().addAll(tableau, stamleden, voedsel, hout, leem, steen, goud, imageviewgereedschap1,
                imageviewgereedschap2, imageviewgereedschap3, imageviewhutkaart1, imageviewhutkaart2,
                imageviewhutkaart3, imageviewhutkaart4, imageviewhutkaart5);
    }

    public void addImageViewToView(int positie, ImageView imageView) {
        int[][] allConstraints = new int[][] { { 2, 1 }, { 2, 11 }, { 2, 21 } };

        int[] constraints = allConstraints[positie - 1];

        GridPane.setConstraints(imageView, constraints[0], constraints[1], 10, 10);
        ;
        this.view.getChildren().add(imageView);
    }

    public void createGereedschap(int positie, int waarde) {
        ImageView imageView = null;
        if (waarde > 0) {
            switch (positie) {
                case 1:
                    this.gereedschapview = new GereedschapView(waarde);
                    imageView = this.gereedschapview.setScene();
                    break;
                case 2:
                    this.gereedschapview1 = new GereedschapView(waarde);
                    imageView = this.gereedschapview1.setScene();
                    break;
                case 3:
                    this.gereedschapview2 = new GereedschapView(waarde);
                    imageView = this.gereedschapview2.setScene();
                    break;
            }
            addImageViewToView(positie, imageView);
        }
    }

    @Override
    public void update(TableauObservable to) {
        int[] gereedschap = to.getTools();
        for (int i = 0; i < gereedschap.length; i++) {
            createGereedschap(i + 1, gereedschap[i]);
        }
    }
}
