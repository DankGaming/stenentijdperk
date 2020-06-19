package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.App;
import hsleiden.stenentijdperk.stenentijdperk.Models.TableauModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.net.URISyntaxException;

public class GereedschapView {
    private ImageView imageView;
    private String resource;
    private boolean canclick = true;

    public GereedschapView(int toolnummer) {
        TableauModel tableau = new TableauModel();
        this.resource = "/Tools/Tool" + toolnummer + ".png";
        setupPane(tableau);
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

    public void setupPane(TableauModel tableau) {
        int[] gereedschap = tableau.getGereedschap();
        boolean[] gereedschapGebruikt = tableau.getGereedschapGebruikt();

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

        imageView.setOnMouseClicked(e -> {
            if(canclick){
                imageView.setRotate(90);
                this.canclick = false;
            }
        });
    }
}
