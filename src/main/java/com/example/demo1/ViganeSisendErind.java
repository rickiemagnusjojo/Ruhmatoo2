package com.example.demo1;
/**
 * Visatakse siis, kui mängija sisestab vigase tähe —
 * näiteks tähe, mis on juba varem pakutud.
 */
public class ViganeSisendErind extends Exception {

    /**
     * Loob erindi koos selgitava veateatega.
     *
     * @param teade selgitus selle kohta, mis läks valesti
     */
    public ViganeSisendErind(String teade) {
        super(teade);
    }
}
