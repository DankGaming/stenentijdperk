package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.Models.TableauModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Hutview {
    private ImageView imageView;
    private String RESOURCE;

    public Hutview(int hutkaartnummer) {
        this.RESOURCE = "./Resources/Hutjes/4_" + hutkaartnummer + ".png";
        setupPane();
    }

    public ImageView setScene() {
        return imageView;
    }

    public void setupPane() {

        FileInputStream input = null;
        try {
            input = new FileInputStream(this.RESOURCE);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException);
        }

        assert input != null;

        Image image = new Image(input);
        this.imageView = new ImageView(image);
        this.imageView.setFitHeight(165);
        this.imageView.setFitWidth(115);
    }
}
