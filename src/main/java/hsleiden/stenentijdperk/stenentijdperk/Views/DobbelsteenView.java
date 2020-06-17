package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.Helpers.Dobbelsteen;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DobbelsteenView extends Pane {

    private ImageView imageView;
    private String RESOURCE = "./Images/dobbelsteen.png";


    public DobbelsteenView() {
        setupPane();
    }

    public ImageView setScene() {
        return imageView;
    }

    public void setupPane() {
        FileInputStream input = null;
        try {
            input = new FileInputStream(this.RESOURCE);
        } catch(FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException);
        }

        assert input != null;
        Image image = new Image(input);
        this.imageView = new ImageView(image);
        this.imageView.setFitHeight(image.getHeight() * 0.75);
        this.imageView.setFitWidth(image.getWidth() * 0.75);
    }

    public void modelChanged(Dobbelsteen model) {
        Platform.runLater(() -> {
                Image image = new Image(String.format(RESOURCE, model.getOgen()));
                imageView.setImage(image);
        });
    }
}
