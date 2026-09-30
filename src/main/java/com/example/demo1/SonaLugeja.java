package com.example.demo1;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Loeb sõnad tekstifailist ja pakub juhusliku sõna valimist.
 * Fail peab olema UTF-8 kodeeringus, üks sõna rea kohta.
 */
public class SonaLugeja {

    private final List<String> sonad;
    private final Random juhuslik;

    /**
     * Loeb sõnad etteantud failist.
     *
     * @param failiNimi sõnu sisaldava faili nimi (nt "sonad.txt")
     * @throws IOException kui faili ei leita või see on tühi
     */
    public SonaLugeja(String failiNimi) throws IOException {
        this.sonad = new ArrayList<>();
        this.juhuslik = new Random();
        loeFailist(failiNimi);
    }

    /**
     * Loeb kõik sõnad failist ja salvestab need listi.
     * Tühjad read ja tühikud eemaldatakse.
     */
    private void loeFailist(String failiNimi) throws IOException {
        try (BufferedReader lugeja = new BufferedReader(
                new InputStreamReader(new FileInputStream(failiNimi), "UTF-8"))) {

            String rida;
            while ((rida = lugeja.readLine()) != null) {
                String sona = rida.trim();
                if (!sona.isEmpty()) {
                    sonad.add(sona.toLowerCase());
                }
            }
        }

        if (sonad.isEmpty()) {
            throw new IOException("Fail " + failiNimi + " on tühi!");
        }
    }

    /**
     * Tagastab juhusliku sõna loetud sõnade hulgast.
     */
    public String juhuslikSona() {
        int juhuslikIndeks = juhuslik.nextInt(sonad.size());
        return sonad.get(juhuslikIndeks);
    }

    /**
     * Tagastab kõikide loetud sõnade arvu.
     */
    public int sonadeSonad() {
        return sonad.size();
    }
}
