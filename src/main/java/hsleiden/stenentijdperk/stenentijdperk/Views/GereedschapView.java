package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.Models.TableauModel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GereedschapView extends Stage {

    public GereedschapView(TableauModel tableau) {
        int[] gereedschap = tableau.getGereedschap();
        boolean[] gereedschapGebruikt = tableau.getGereedschapGebruikt();

        HBox view = new HBox();

        for (int i = 0; i < 3; i++) {
            if (gereedschap[i] == 0) {
                continue;
            }
            // abeelding gereedschap maken
            Image image = new Image("Gereedschap");
            ImageView imageView = new ImageView(image);

            imageView.setFitHeight(100);
            imageView.setFitWidth(100);

            //afbeelding 90 graaden draaien als fiche gebruikt is
            imageView.setRotate(gereedschapGebruikt[i] ? 90 : 0);

            if (!gereedschapGebruikt[i]) {
                // hier gereedschap kunnen gebruiken
                imageView.setOnMouseClicked(e -> {
                    tableau.gebruikGereedschap();
                    imageView.setRotate(90);
                    imageView.setOnMouseClicked(n -> {
                    });
                });
            }

            view.getChildren().add(imageView);
        }

        // Aanmaken bevestiging button voor gebruik gereedschap
        Button bevestigen = new Button("Bevestigen");
        bevestigen.setPrefSize(100, 100);
        bevestigen.setOnAction(e -> this.close());
        view.getChildren().add(bevestigen);

        Scene scene = new Scene(view);
        this.setScene(scene);

    }
}
