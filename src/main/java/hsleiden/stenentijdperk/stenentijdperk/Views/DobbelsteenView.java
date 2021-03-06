package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.App;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URISyntaxException;

public class DobbelsteenView extends Pane {

    private ImageView imageView;
    private String resource = "/Dice/dobbelsteen.png";

    public DobbelsteenView() {
        setupPane();
    }

    public ImageView setScene() {
        return imageView;
    }

    public void setupPane() {
        Image image = null;

        try {
            image = new Image(String.valueOf(App.class.getResource(this.resource).toURI()));
            this.imageView = new ImageView(image);
            this.imageView.setFitHeight(image.getHeight() * 0.75);
            this.imageView.setFitWidth(image.getWidth() * 0.75);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
