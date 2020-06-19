package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.App;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URISyntaxException;

public class Hutview {
    private ImageView imageView;
    private String RESOURCE;

    public Hutview(int hutkaartnummer) {
        this.RESOURCE = "/Hutjes/4_" + hutkaartnummer + ".png";
        setupPane();
    }

    public ImageView setScene() {
        return imageView;
    }

    public void setupPane() {
        Image image = null;
        try {
            image = new Image(String.valueOf(App.class.getResource(RESOURCE).toURI()));
        } catch(URISyntaxException e) {
            e.printStackTrace();
        }

        assert image != null;
        this.imageView = new ImageView(image);
        this.imageView.setFitHeight(165);
        this.imageView.setFitWidth(115);
    }
}
