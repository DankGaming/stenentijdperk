package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.App;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URISyntaxException;

public class SpelregelView {
    VBox vBox = new VBox();

    private String[] imageNames = new String[]{"1", "2", "3", "5"};

    public SpelregelView() {
        setupPane();
    }

    public VBox setScene() {
        return this.vBox;
    }

    public void generateVbox() {
        for (String imageName : imageNames) {
            try {
                Image image = new Image(String.valueOf(App.class.getResource("/Spelregels/" + imageName + ".png").toURI()), 800, 600, true, false);
                ImageView imageView = new ImageView(image);
                this.vBox.getChildren().add(imageView);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    private void setupPane() {
        generateVbox();
    }
}
