package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.App;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URISyntaxException;

public class GereedschapView {
    private ImageView imageView;
    private String resource;
    private boolean canclick;

    public GereedschapView(int toolnummer, boolean status) {
        this.resource = "/Tools/Tool" + toolnummer + ".png";
        this.canclick = status;
        setupPane();
    }

    public ImageView setScene() {
        return imageView;
    }

    public void resetGereedschap(){
        if(!canclick){
            imageView.setRotate(0);
            canclick = true;
        }
    }

    public void setupPane() {

        Image image = null;

        try {
            image = new Image(String.valueOf(App.class.getResource(this.resource).toURI()));
        }catch (URISyntaxException e) {
            e.printStackTrace();
        }

        assert image != null;
        this.imageView = new ImageView(image);
        this.imageView.setFitHeight(70);
        this.imageView.setFitWidth(70);

        if (!(canclick)){
            imageView.setRotate(90);
        }

        imageView.setOnMouseClicked(e -> {
            if(canclick){
                imageView.setRotate(90);
                this.canclick = false;
            }
        });
    }
}
