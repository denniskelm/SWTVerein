package server;
/*
@author
Raphael Kleebaum
Jonny Schlutter
Gabriel Kleebaum
Mhd Esmail Kanaan
Gia Huy Hans Tran
Ole Björn Adelmann
Bastian Reichert
Dennis Kelm
*/

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Geraet {

    private String geraeteID;
    private String name;
    private String spenderName;
    private int leihfrist;
    private String kategorie;
    private String beschreibung;
    //private Ausleiher[] reservierungsliste;
    //private Status leihstatus;

    public void reservierungHinzufuegen(String PersonenID) {

    }

    public void reservierungEntfernen() {

    }

    public void ausgeben() {
        leihstatus = Status.AUSGELIEHEN;
    }

    public void annehmen() {
        leihstatus = Status.FREI;

        LocalDateTime altesAbgabeDatum = reservierungsliste.get(0).getFristBeginn().plusDays(leihfrist);
        long tageZuFrueh = LocalDateTime.now().until(altesAbgabeDatum, ChronoUnit.DAYS);

        // aktuellen Ausleiher entfernen
        reservierungsliste.remove(0);

        // Fristbeginn der anderen Ausleiher neu berechnen
        for (Ausleiher a : reservierungsliste) {
            a.setFristBeginn(a.getFristBeginn().minusDays(tageZuFrueh));
        }

    }
}
