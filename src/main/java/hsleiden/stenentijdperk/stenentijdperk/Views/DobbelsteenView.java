package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.Helpers.Dobbelsteen;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class DobbelsteenView extends Pane {

    private ImageView imageView;
    private String RESOURCE = "file:Images/dobbelsteen.png";
    private GridPane view;

    public GridPane setScene() {
        return view;
    }

    public DobbelsteenView() {

        Image image = new Image("file:Images/dobbelsteen.png");
        imageView = new ImageView(image);
        imageView.setFitHeight(image.getHeight() * 0.75);
        imageView.setFitWidth(image.getWidth() * 0.75);

        this.view = new GridPane();
        view.getChildren().add(imageView);

    }

    public void modelChanged(Dobbelsteen model) {
        Platform.runLater(() -> {
                Image image = new Image(String.format(RESOURCE, model.getOgen()));
                imageView.setImage(image);
        });
    }
}
