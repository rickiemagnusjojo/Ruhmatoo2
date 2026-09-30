package com.example.demo1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class HangmanTest extends Application {

    private Canvas hangmanJoonis;
    private int praegusedVead = 0;
    private Label veadLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage peaAken) {
        hangmanJoonis = new Canvas(300, 300);

        StackPane joonisePaneel = new StackPane(hangmanJoonis);
        joonisePaneel.setPadding(new Insets(10));

        // Nupud vigade lisamiseks/eemaldamiseks
        Button lisaViga = new Button("+ Lisa viga");
        lisaViga.setFont(Font.font(14));
        lisaViga.setOnAction(e -> {
            if (praegusedVead < 6) {
                praegusedVead++;
                uuendaKuva();
            }
        });

        Button eemalda = new Button("- Eemalda viga");
        eemalda.setFont(Font.font(14));
        eemalda.setOnAction(e -> {
            if (praegusedVead > 0) {
                praegusedVead--;
                uuendaKuva();
            }
        });

        Button nulli = new Button("Lähtesta");
        nulli.setFont(Font.font(14));
        nulli.setOnAction(e -> {
            praegusedVead = 0;
            uuendaKuva();
        });

        veadLabel = new Label("Vigu: 0 / 6");
        veadLabel.setFont(Font.font(16));

        HBox nupud = new HBox(10, eemalda, nulli, lisaViga);
        nupud.setAlignment(Pos.CENTER);

        VBox paigutus = new VBox(10, joonisePaneel, veadLabel, nupud);
        paigutus.setAlignment(Pos.CENTER);
        paigutus.setPadding(new Insets(20));

        // Joonis skaleerub akna suurusega
        hangmanJoonis.widthProperty().bind(paigutus.widthProperty().multiply(0.6));
        hangmanJoonis.heightProperty().bind(hangmanJoonis.widthProperty());
        hangmanJoonis.widthProperty().addListener(e -> joonistahangman());

        Scene stseen = new Scene(paigutus, 400, 500);
        peaAken.setTitle("Hangman joonise test");
        peaAken.setScene(stseen);
        peaAken.show();

        joonistahangman();
    }

    private void uuendaKuva() {
        veadLabel.setText("Vigu: " + praegusedVead + " / 6");
        joonistahangman();
    }

    private void joonistahangman() {
        GraphicsContext joonistaja = hangmanJoonis.getGraphicsContext2D();
        double laius = hangmanJoonis.getWidth();
        double korgus = hangmanJoonis.getHeight();

        joonistaja.clearRect(0, 0, laius, korgus);
        joonistaja.setStroke(Color.BLACK);
        joonistaja.setLineWidth(3);

        int vead = praegusedVead;

        // Võllas (alati nähtav)
        joonistaja.strokeLine(laius * 0.1, korgus * 0.9, laius * 0.9, korgus * 0.9); // alus
        joonistaja.strokeLine(laius * 0.3, korgus * 0.9, laius * 0.3, korgus * 0.05); // püstpost
        joonistaja.strokeLine(laius * 0.3, korgus * 0.05, laius * 0.65, korgus * 0.05); // rõhtpost
        joonistaja.strokeLine(laius * 0.65, korgus * 0.05, laius * 0.65, korgus * 0.18); // nöör

        if (vead >= 1) { // Pea
            double peaR = laius * 0.08;
            joonistaja.strokeOval(
                    laius * 0.65 - peaR, korgus * 0.18,
                    peaR * 2, peaR * 2
            );
        }
        if (vead >= 2) { // Keha
            joonistaja.strokeLine(
                    laius * 0.65, korgus * 0.34,
                    laius * 0.65, korgus * 0.62
            );
        }
        if (vead >= 3) { // Vasak käsi
            joonistaja.strokeLine(
                    laius * 0.65, korgus * 0.40,
                    laius * 0.50, korgus * 0.52
            );
        }
        if (vead >= 4) { // Parem käsi
            joonistaja.strokeLine(
                    laius * 0.65, korgus * 0.40,
                    laius * 0.80, korgus * 0.52
            );
        }
        if (vead >= 5) { // Vasak jalg
            joonistaja.strokeLine(
                    laius * 0.65, korgus * 0.62,
                    laius * 0.50, korgus * 0.78
            );
        }
        if (vead >= 6) { // Parem jalg
            joonistaja.strokeLine(
                    laius * 0.65, korgus * 0.62,
                    laius * 0.80, korgus * 0.78
            );
        }
    }
}