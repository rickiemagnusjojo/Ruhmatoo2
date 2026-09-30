package com.example.demo1;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;

/**
 * Salvestab ja loeb tulemusi tekstifailist.
 */
public class TulemusteFail {

    /**
     * Salvestab mängu tulemuse tekstifaili.
     */
    public static void salvestaTulemus(String sona, boolean voitis) throws IOException {
        try (OutputStreamWriter valja = new OutputStreamWriter(
                new FileOutputStream("tulemused.txt", true), "UTF-8")) {

            String tulemus = voitis ? "Võit" : "Kaotus";
            valja.write(tulemus + ";" + sona + ";" + LocalDate.now().toString() + "\n");
        }

    /**
     * Loeb kõik tulemused failist ja tagastab seda listina.
     */
    }
    public static List<String> loeKoikTulemused() throws IOException {
        List<String> tulemused = new ArrayList<>();
        try (BufferedReader lugeja = new BufferedReader(
                new InputStreamReader(new FileInputStream("tulemused.txt"), "UTF-8"))) {

            String rida;
            while ((rida = lugeja.readLine()) != null) {
                rida = rida.trim();
                if (!rida.isEmpty()) tulemused.add(rida);
            }
        }
        return tulemused;
    }
}
