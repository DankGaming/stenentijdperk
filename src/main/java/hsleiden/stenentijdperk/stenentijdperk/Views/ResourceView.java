package hsleiden.stenentijdperk.stenentijdperk.Views;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;


public class ResourceView {

    private GridPane view;
    private Label hout;
    private Label houtHoeveelheid;
    private TextField houtField;
    private Label goud;
    private Label goudHoeveelheid;
    private TextField goudField;
    private Label leem;
    private Label leemHoeveelheid;
    private TextField leemField;
    private Label steen;
    private Label steenHoeveelheid;
    private TextField steenField;
    private Label voedsel;
    private Label voedselHoeveelheid;
    private TextField voedselField;
    private Button resourceButton;


    public ResourceView(){
        setupPane();
    }

    public GridPane setScene() {
        return view;
    }

    public void setupPane() {
        this.view = new GridPane();
        this.view.setStyle("-fx-background-color: #d0c9b5");
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

        String textFont = "-fx-font-size: 15px;";

        //Labels
        hout = new Label("Hout");
        hout.setStyle(textFont);
        GridPane.setConstraints(hout, 3, 4, 5, 2);

        leem = new Label("Leem");
        leem.setStyle(textFont);
        GridPane.setConstraints(leem, 3, 6, 5, 2);

        steen = new Label("Steen");
        steen.setStyle(textFont);
        GridPane.setConstraints(steen, 3, 8, 5,2);

        goud = new Label("Goud");
        goud.setStyle(textFont);
        GridPane.setConstraints(goud, 3, 10, 5, 2);

        voedsel = new Label("Voedsel");
        voedsel.setStyle(textFont);
        GridPane.setConstraints(voedsel, 3, 12, 5,2);

        //Hoeveelheden
        houtHoeveelheid = new Label("0");
        houtHoeveelheid.setStyle(textFont);
        GridPane.setConstraints(houtHoeveelheid, 10, 4, 2, 2);

        leemHoeveelheid = new Label("0");
        leemHoeveelheid.setStyle(textFont);
        GridPane.setConstraints(leemHoeveelheid, 10, 6, 2, 2);

        steenHoeveelheid = new Label("0");
        steenHoeveelheid.setStyle(textFont);
        GridPane.setConstraints(steenHoeveelheid, 10, 8, 2, 2);

        goudHoeveelheid = new Label("0");
        goudHoeveelheid.setStyle(textFont);
        GridPane.setConstraints(goudHoeveelheid, 10, 10, 2, 2);

        voedselHoeveelheid = new Label("0");
        voedselHoeveelheid.setStyle(textFont);
        GridPane.setConstraints(voedselHoeveelheid, 10, 12, 2, 2);

        //Text field
        houtField = new TextField();
        GridPane.setConstraints(houtField, 15, 4, 5, 1);

        leemField = new TextField();
        GridPane.setConstraints(leemField, 15, 6, 5, 1);

        steenField = new TextField();
        GridPane.setConstraints(steenField, 15, 8, 5, 1);

        goudField = new TextField();
        GridPane.setConstraints(goudField, 15, 10, 5, 1);

        voedselField= new TextField();
        GridPane.setConstraints(voedselField, 15, 12, 5, 1);

        //button
        resourceButton = new Button("Confirm");
        resourceButton.setMinSize(220, 50);
        resourceButton.setStyle("-fx-background-color: #dfa231; -fx-text-fill: #f6e5b6; -fx-border-color:#453b1b; -fx-border-width: 1px; -fx-border-radius: 1px; -fx-font-size: 20px;");
        GridPane.setConstraints(resourceButton, 3 ,15, 5, 5);

        this.view.getChildren().addAll(hout, leem, steen, goud, voedsel, houtHoeveelheid, leemHoeveelheid, steenHoeveelheid, goudHoeveelheid, voedselHoeveelheid,
                houtField, leemField, steenField, goudField, voedselField, resourceButton);
    }
}
