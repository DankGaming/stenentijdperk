package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.App;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Tool;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URISyntaxException;

public class GereedschapView {
    private ImageView imageView;
    private String resource;
    private TableauView tableauView;
    private Tool tool;

    public GereedschapView(Tool tool, TableauView tableauView) {
        this.tool = tool;
        this.tableauView = tableauView;
        this.resource = "/Tools/Tool" + tool.getLevel() + ".png";
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
            this.imageView.setFitHeight(70);
            this.imageView.setFitWidth(70);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        if (!(tool.getStatus())) {
            imageView.setRotate(90);
        }

        imageView.setOnMouseClicked(e -> {
            if (tool.getStatus()) {
                imageView.setRotate(90);
                tool.setStatus(false);
                tableauView.waarde += tool.getLevel();
            }
        });
    }
}
