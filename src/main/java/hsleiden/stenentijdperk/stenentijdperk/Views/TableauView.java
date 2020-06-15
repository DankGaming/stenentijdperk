package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.Controllers.LoginController;
import hsleiden.stenentijdperk.stenentijdperk.Models.TableauModel;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TableauView {
    private ImageView imageView;
    private String RESOURCE = "./Images/tableau.png";
    private GridPane view;
    private Label voedsel;
    private Label hout;
    private Label leem;
    private Label steen;
    private Label goud;
    private Pane textPane;

    public TableauView() {
        setupPane();
    }

    public GridPane setScene() {
        return view;
    }

    public void setupPane() {
        this.view = new GridPane();


        FileInputStream input = null;
        try {
            input = new FileInputStream(this.RESOURCE);
        } catch(FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException);
        }

        assert input != null;
        Image image = new Image(input);
        this.imageView = new ImageView(image);
        this.imageView.setFitHeight(image.getHeight() * 0.35);
        this.imageView.setFitWidth(image.getWidth() * 0.35);



        this.view.getChildren().addAll(imageView);
    }
}
