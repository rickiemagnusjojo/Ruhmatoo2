package com.example.demo1;
import java.util.HashSet;
import java.util.Set;

/**
 * Sisaldab hangman-mängu põhilist loogikat.
 * Hoiab meeles salajast sõna, arvatud tähti ja vigade arvu.
 */
public class Mang {

    public static final int MAX_VEAD = 6;

    private final String salajaneSona;
    private final Set<Character> arvatudTahed;
    private int vigadeArv;

    /**
     * Alustab uut mängu etteantud sõnaga.
     *
     * @param sona salajane sõna, mida mängija peab ära arvama
     */
    public Mang(String sona) {
        this.salajaneSona = sona.toLowerCase();
        this.arvatudTahed = new HashSet<>();
        this.vigadeArv = 0;
    }

    /**
     * Mängija pakub tähe.
     * Kui täht on juba pakutud, visatakse ViganeSisendErind.
     * Kui täht ei esine sõnas, suurendatakse vigade arvu.
     *
     * @param taht mängija pakutud täht
     * @throws ViganeSisendErind kui sama täht on juba pakutud
     */
    public void pakuTaht(char taht) throws ViganeSisendErind {
        char vaike_taht = Character.toLowerCase(taht);

        if (arvatudTahed.contains(vaike_taht)) {
            throw new ViganeSisendErind("Täht '" + vaike_taht + "' on juba pakutud!");
        }

        arvatudTahed.add(vaike_taht);

        if (!salajaneSona.contains(String.valueOf(vaike_taht))) {
            vigadeArv++;
        }
    }

    /**
     * Tagastab sõna kuvamiseks mõeldud versiooni.
     * Arvamata tähtede asemel kuvatakse "_".
     * Näiteks "maja" -> "m _ _ _" kui ainult 'm' on arvatud.
     */
    public String sonaKuvamiseks() {
        StringBuilder tulemus = new StringBuilder();

        for (char taht : salajaneSona.toCharArray()) {
            if (arvatudTahed.contains(taht)) {
                tulemus.append(taht);
            } else {
                tulemus.append("_");
            }
            tulemus.append(" ");
        }

        return tulemus.toString().trim();
    }

    /**
     * Tagastab true, kui mängija on sõna täielikult ära arvanud.
     */
    public boolean onVoidetud() {
        for (char taht : salajaneSona.toCharArray()) {
            if (!arvatudTahed.contains(taht)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Tagastab true, kui mängija on teinud maksimaalse arvu vigu.
     */
    public boolean onKaotatud() {
        return vigadeArv >= MAX_VEAD;
    }

    /**
     * Tagastab true, kui mäng on lõppenud (kas võit või kaotus).
     */
    public boolean onLoppenud() {
        return onVoidetud() || onKaotatud();
    }

    public String getSalajaneSona() { return salajaneSona; }
    public Set<Character> getarvatudTahed() { return arvatudTahed; }
    public int getVigadeArv() { return vigadeArv; }
}
