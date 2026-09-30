package com.example.demo1;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class Main extends Application {
    @Override
    public void start(Stage primary) throws Exception {
        primary.setScene(new Scene(new ManguAken(new SonaLugeja("sonad.txt")), 600, 600, Color.SNOW));
        primary.show();
    }
    public static void main(String[] args) { launch(); }
}
