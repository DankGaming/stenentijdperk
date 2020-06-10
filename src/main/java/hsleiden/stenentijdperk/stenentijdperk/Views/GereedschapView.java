package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.Models.TableauModel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GereedschapView extends Stage {
    private ImageView imageView;
    private String RESOURCE = "./Images/dobbelsteen.png";

    public GereedschapView() {
        TableauModel tableau = new TableauModel();
        setupPane(tableau);
    }

    public ImageView setScene() {
        return imageView;
    }

    public void setupPane(TableauModel tableau) {
        int[] gereedschap = tableau.getGereedschap();
        boolean[] gereedschapGebruikt = tableau.getGereedschapGebruikt();

        FileInputStream input = null;
        try {
            input = new FileInputStream(this.RESOURCE);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException);
        }

        assert input != null;

        // abeelding gereedschap maken
        Image image = new Image(input);
        this.imageView = new ImageView(image);
        this.imageView.setFitHeight(100);
        this.imageView.setFitWidth(100);

        //afbeelding 90 graaden draaien als fiche gebruikt is


    }
}
