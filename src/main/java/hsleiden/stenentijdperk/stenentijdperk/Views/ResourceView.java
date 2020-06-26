package hsleiden.stenentijdperk.stenentijdperk.Views;

import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;


public class ResourceView {

    private GridPane view;
    private Label hout;
    private Label houtHoeveelheid;
    private Label goud;
    private Label goudHoeveelheid;
    private Label leem;
    private Label leemHoeveelheid;
    private Label steen;
    private Label steenHoeveelheid;
    private Label voedsel;
    private Label voedselHoeveelheid;

    public ResourceView(){
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
        //Labels
        hout = new Label("Hout");
        GridPane.setConstraints(hout, 3, 4, 4, 2);

        leem = new Label("Leem");
        GridPane.setConstraints(leem, 3, 6, 4, 2);

        steen = new Label("Steen");
        GridPane.setConstraints(steen, 3, 8, 4,2);

        goud = new Label("Goud");
        GridPane.setConstraints(goud, 3, 10, 4, 2);

        voedsel = new Label("Voedsel");
        GridPane.setConstraints(voedsel, 3, 12, 4,2);

        //Hoeveelheden
        houtHoeveelheid = new Label("0");
        GridPane.setConstraints(houtHoeveelheid, 10, 4, 2, 2);

        leemHoeveelheid = new Label("0");
        GridPane.setConstraints(leemHoeveelheid, 10, 6, 2, 2);

        steenHoeveelheid = new Label("0");
        GridPane.setConstraints(steenHoeveelheid, 10, 8, 2, 2);

        goudHoeveelheid = new Label("0");
        GridPane.setConstraints(goudHoeveelheid, 10, 10, 2, 2);

        voedselHoeveelheid = new Label("0");
        GridPane.setConstraints(voedselHoeveelheid, 10, 10, 2, 2);


        this.view.getChildren().addAll(hout, leem, steen, goud, voedsel, houtHoeveelheid, leemHoeveelheid, steenHoeveelheid, goudHoeveelheid, voedselHoeveelheid);
    }
}
