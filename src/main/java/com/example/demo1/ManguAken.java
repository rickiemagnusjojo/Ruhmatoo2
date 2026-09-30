package com.example.demo1;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX aken, mis kuvab hangman-mängu.
 * Sisaldab hangmani joonist, sõna kuvamist, tähenuppude rida ja teavet tulemuste kohta.
 */

public class ManguAken extends BorderPane {

    private static final String[] EESTI_TAHED = {
            "a", "b", "d", "e", "f", "g", "h", "i", "j", "k",
            "l", "m", "n", "o", "p", "r", "s", "š", "z", "ž",
            "t", "u", "v", "õ", "ä", "ö", "ü"
    };

    private Mang mangObjekt;
    private SonaLugeja sonaLugeja;

    private Canvas hangmanJoonis;
    private Label sonaLabel;
    private Label veadLabel;
    private Label teadeLabel;
    private List<Button> taheNupud;
    private Button uusMangNupp;

    /**
     * Loob mänguakna ja käivitab esimese mängu.
     *
     * @param sonaLugeja juba laetud sõnalugeja objekt
     */
    public ManguAken(SonaLugeja sonaLugeja) {
        this.sonaLugeja = sonaLugeja;
        this.taheNupud = new ArrayList<>();

        ehitaLiides();
        alustauutMangu();
    }

    /**
     * JavaFX struktuur.
     * Ülaosas on hangmani joonis, keskel sõna, all tähenupud.
     */
    private void ehitaLiides() {
        // Ülaosa: hangmani joonis
        hangmanJoonis = new Canvas(300, 300);
        StackPane joonisePaneel = new StackPane(hangmanJoonis);
        joonisePaneel.setPadding(new Insets(10));

        // Keskosa: sõna ja vead
        sonaLabel = new Label();
        sonaLabel.setFont(Font.font("Monospaced", 32));
        sonaLabel.setAlignment(Pos.CENTER);

        veadLabel = new Label();
        veadLabel.setFont(Font.font(18));

        teadeLabel = new Label();
        teadeLabel.setFont(Font.font(20));
        teadeLabel.setStyle("-fx-font-weight: bold;");

        VBox kesk = new VBox(10, sonaLabel, veadLabel, teadeLabel);
        kesk.setAlignment(Pos.CENTER);
        kesk.setPadding(new Insets(10));

        // Alumine osa: tähenupud ja uue mängu nupp
        FlowPane tahedeRida = new FlowPane();
        tahedeRida.setHgap(6);
        tahedeRida.setVgap(6);
        tahedeRida.setAlignment(Pos.CENTER);
        tahedeRida.setPadding(new Insets(10));

        for (String taht : EESTI_TAHED) {
            Button nupp = new Button(taht.toUpperCase());
            nupp.setPrefWidth(45);
            nupp.setPrefHeight(40);
            nupp.setFont(Font.font(14));
            nupp.setOnAction(e -> tootleTaheSisestus(taht.charAt(0)));
            taheNupud.add(nupp);
            tahedeRida.getChildren().add(nupp);
        }

        uusMangNupp = new Button("Uus mäng");
        uusMangNupp.setFont(Font.font(16));
        uusMangNupp.setOnAction(e -> alustauutMangu());

        VBox alaosa = new VBox(10, tahedeRida, uusMangNupp);
        alaosa.setAlignment(Pos.CENTER);
        alaosa.setPadding(new Insets(10));

        // Paigutus aknasse
        setTop(joonisePaneel);
        setCenter(kesk);
        setBottom(alaosa);

        // Hangmani joonis skaleerub akna laiusega
        hangmanJoonis.widthProperty().bind(
                this.widthProperty().multiply(0.4)
        );
        hangmanJoonis.heightProperty().bind(
                hangmanJoonis.widthProperty()
        );
        hangmanJoonis.widthProperty().addListener(e -> joonistahangman());
    }

    /**
     * Alustab uut mängu — valib juhusliku sõna ja lähtestab kõik.
     */
    private void alustauutMangu() {
        String uusSona = sonaLugeja.juhuslikSona();
        mangObjekt = new Mang(uusSona);

        // Lähtesta tähenupud
        for (Button nupp : taheNupud) {
            nupp.setDisable(false);
            nupp.setStyle("");
        }

        teadeLabel.setText("");
        uuendaKuva();
    }

    /**
     * Töötleb mängija valitud tähe.
     * Kuvab veateate kui täht on juba pakutud, muidu uuendab mängu seisu.
     */
    private void tootleTaheSisestus(char taht) {
        try {
            mangObjekt.pakuTaht(taht);
            keelaNupp(taht);
            uuendaKuva();

            if (mangObjekt.onVoidetud()) {
                teadeLabel.setText("Võitsid! Sõna oli: " + mangObjekt.getSalajaneSona());
                keelakõikNupud();
                salvestaTulemus(true);
            } else if (mangObjekt.onKaotatud()) {
                teadeLabel.setText("Kaotasid! Sõna oli: " + mangObjekt.getSalajaneSona());
                keelakõikNupud();
                salvestaTulemus(false);
            }

        } catch (ViganeSisendErind e) {
            teadeLabel.setText(e.getMessage());
        }
    }

    /**
     * Uuendab sõna kuvamist ja vigade arvu ekraanil ning joonistab hangmani uuesti.
     */
    private void uuendaKuva() {
        sonaLabel.setText(mangObjekt.sonaKuvamiseks());
        veadLabel.setText("Vigu: " + mangObjekt.getVigadeArv() + " / " + Mang.MAX_VEAD);
        joonistahangman();
    }

    /**
     * Keelab konkreetse tähe nupu ja värvib selle vastavalt —
     * roheline kui täht oli sõnas, punane kui ei olnud.
     */
    private void keelaNupp(char taht) {
        for (Button nupp : taheNupud) {
            if (nupp.getText().equalsIgnoreCase(String.valueOf(taht))) {
                nupp.setDisable(true);
                boolean onSonas = mangObjekt.getSalajaneSona().contains(String.valueOf(taht));
                nupp.setStyle(onSonas
                        ? "-fx-background-color: #90ee90;"  // roheline
                        : "-fx-background-color: #ff9999;"); // punane
                break;
            }
        }
    }

    /**
     * Keelab kõik tähenupud (kutsutakse mängu lõppedes).
     */
    private void keelakõikNupud() {
        for (Button nupp : taheNupud) {
            nupp.setDisable(true);
        }
    }

    /**
     * Salvestab mängu tulemuse faili.
     * Kui salvestamine ebaõnnestub, kuvatakse veateade — mäng jätkub siiski.
     */
    private void salvestaTulemus(boolean voitis) {
        try {
            TulemusteFail.salvestaTulemus(mangObjekt.getSalajaneSona(), voitis);
        } catch (IOException e) {
            teadeLabel.setText(teadeLabel.getText() + "\n(Tulemuse salvestamine ebaõnnestus)");
        }
    }

    /**
     * Joonistab hangmani joonise Canvas-ile vastavalt vigade arvule.
     * 0 viga = tühi võllas, 6 viga = täielik hangman.
     */
    private void joonistahangman() {
        GraphicsContext joonistaja = hangmanJoonis.getGraphicsContext2D();
        double laius = hangmanJoonis.getWidth();
        double korgus = hangmanJoonis.getHeight();

        // Puhasta joonis
        joonistaja.clearRect(0, 0, laius, korgus);
        joonistaja.setStroke(Color.BLACK);
        joonistaja.setLineWidth(3);

        int vead = mangObjekt.getVigadeArv();

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


