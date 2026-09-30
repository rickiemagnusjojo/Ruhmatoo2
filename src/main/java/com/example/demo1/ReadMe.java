package com.example.demo1;

public class ReadMe {
    /**
     * PROJEKTI KIRJELDUS:
     * Objektorienteeritud programmeerimine 2.rühmatöö
     * Autorid: Rickie Magnus Jojo Roberts, Gabriel Kalvet
     * Programm on hangman (poomismäng) sõnamäng,
     * kus mängija peab tähtede kaupa ära arvama peidetud eestikeelse sõna.
     * Mängijal on maksimaalselt 6 viga, mille järel mäng lõpeb.
     * Iga vale tähe pakkumisega lisandub hangmani joonisele uus kehaosa.
     * Õige sõna äraarvamisel kuulutatakse mängija võitjaks.
     *
     * PROGRAMMI TÖÖ:
     * Käivitumisel loetakse sõnad failist sonad.txt,
     * misjärel valitakse juhuslik sõna ja kuvatakse mänguaken.
     * Mängija pakub tähti ekraanil olevatele nuppudele klõpsates.
     * Pakutud tähed värvitakse roheliseks (täht on sõnas) või punaseks (täht pole sõnas).
     * Mängu lõppedes kuvatakse tulemus ja mängija saab alustada uut mängu.
     * Iga mängu tulemus salvestatakse faili tulemused.txt.
     *
     * KLASSID:
     * Main — käivitab JavaFX rakenduse ja loob peaakna koos SonaLugeja objektiga.
     *
     * ManguAken — JavaFX põhiaken, mis kuvab hangmani joonise, peidetud sõna,
     * tähenupud ja tulemuste teated. Olulisemad meetodid:
     * ehitaLiides() ehitab kogu liidese struktuuri,
     * tootleTaheSisestus() töötleb mängija pakutud tähe,
     * joonistahangman() joonistab hangmani ekraanile vastavalt vigade arvule.
     *
     * Mang — sisaldab mängu põhilist loogikat. Olulisemad meetodid:
     * pakuTaht() lisab tähe arvatute hulka ja uuendab vigade arvu,
     * sonaKuvamiseks() tagastab sõna kuvamiseks sobival kujul (nt m _ j _),
     * onVoidetud() ja onKaotatud() kontrollivad mängu seisu.
     *
     * SonaLugeja — loeb sõnad UTF-8 kodeeringus tekstifailist.
     * Olulisemad meetodid: loeFailist() loeb ja salvestab sõnad listi,
     * juhuslikSona() tagastab juhusliku sõna.
     *
     * TulemusteFail — salvestab ja loeb mängu tulemusi failist tulemused.txt.
     * Olulisemad meetodid: salvestaTulemus() kirjutab tulemuse faili,
     * loeKoikTulemused() loeb kõik varasemad tulemused.
     *
     * ViganeSisendErind — kohandatud erindiklass,
     * mida visatakse kui mängija pakub tähte, mis on juba varem pakutud.
     *
     * PROTSESS:
     * Rickie - mõte, Mang, ManguAken, SonaLugeja
     * Gabriel - TulemusteFail, Main, muudatused ka teistes klassides enne lõppversiooni
     * esitamist, bugidega tegelemine
     * Mõlemad - ReadMe
     *
     * TEHISINTELLEKT:
     * Kasutasin JavaFX hangmani joonistamise puhul päris palju Claude AI abi.
     * Küll aga ma ei saa öelda, et mingi osa lahendusest oleks otseselt tema loodud,
     * üritasin alguses ise ja siis sain temalt lihtsalt parandusi,
     * seega see on justkui "meie mõlema töö tulemus". HangmanTest on nt enamjaolt AI
     * loodud, selle pealt oli hea edasi minna. - Rickie
     *
     * Ajakulu - Kogu töö peale kokku mõlemal ca 6h.
     *
     * Mured - JavaFX, kuna seda ei olnud vaja KT jaoks otseselt osata ning
     * oli meelest läinud.
     *
     * HINNANG - Ütleksime, hea on et klasside jaotus, mängu loogika, JavaFX lõpptulemus,
     * failidega töö; vajab arendamist failiteede käsitlemine, alguses esines probleeme
     * sellega, et programm ei leidnud sonad.txt faili (pidi lihtsalt ümber paigutama),
     * veahaldus, tulemuste kuvamine (salvestatakse, aga otseselt ei näidata) ning oleks
     * võinud hakata varem tegeleda, kuna nii hilja alustades läks veidi kiireks.
     * Üldpildis 5-palli süsteemis 4.
     *
     * TESTIMINE - Kuna kõik klassid on omavahel seotud, siis testida sai vaid Main jooksutades,
     * küll aga HangmanTest oli loodud selleks, et lihtsalt JavaFX pildi tegemist proovida.
     */
}
