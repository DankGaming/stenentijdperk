package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.Controllers.LoginController;
import hsleiden.stenentijdperk.stenentijdperk.Models.TableauModel;
import hsleiden.stenentijdperk.stenentijdperk.observers.TableauObservable;
import hsleiden.stenentijdperk.stenentijdperk.observers.TableauObserver;
import javafx.geometry.Insets;
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
    GereedschapView gereedschapview = new GereedschapView(1);
    GereedschapView gereedschapview1 = new GereedschapView(1);
    GereedschapView gereedschapview2 = new GereedschapView(1);
    private ImageView tableau;
    private String RESOURCE = "./Images/tableau.png";
    private GridPane view;
    private Label voedsel;
    private Label hout;
    private Label leem;
    private Label steen;
    private Label goud;
    private Label stamleden;

    public TableauView() {
        setupPane();
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
        GridPane.setConstraints(tableau, 0, 0, 50 ,50);


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


        ImageView imageviewgereedschap = gereedschapview.setScene();
        GridPane.setConstraints(imageviewgereedschap, 2, 1, 10, 10);

        ImageView imageviewgereedschap1 = gereedschapview1.setScene();
        GridPane.setConstraints(imageviewgereedschap1, 2, 11, 10, 10);

        ImageView imageviewgereedschap2 = gereedschapview2.setScene();
        GridPane.setConstraints(imageviewgereedschap2, 2, 21, 10, 10);

        this.view.getChildren().addAll(tableau, stamleden , voedsel, hout, leem, steen, goud, imageviewgereedschap,
                imageviewgereedschap1, imageviewgereedschap2);

    }

    @Override
    public void update(TableauObservable lo) {

    }
}
