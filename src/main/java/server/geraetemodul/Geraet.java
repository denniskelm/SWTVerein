package server.geraetemodul;
/*
@author
Raphael Kleebaum
Jonny Schlutter
//TODO Gabriel Kleebaum
Mhd Esmail Kanaan
//TODO Gia Huy Hans Tran
//TODO Ole Björn Adelmann
//TODO Bastian Reichert
//TODO Dennis Kelm
*/

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Objects;

public class Geraet {

    private String geraeteID;
    private String name;
    private String spenderName;
    private int leihfrist; // muss in Tagen angegeben werden
    private String kategorie;
    private String beschreibung;
    private String abholort;
    private ArrayList<Ausleiher> reservierungsliste, historie;

    public ArrayList<Ausleiher> getHistorie() {
        return historie;
    }

    // reservierdatum: LocalDateTime
    // fristbeginn: LocalDateTime
    // abgegeben: boolean
    // mitgliedsID: int
    private Status leihstatus; // frei / beansprucht / ausgeliehen

    public Geraet(String geraeteID, String name, String spenderName, int leihfrist, String kategorie, String beschreibung, String abholort) {
        this.geraeteID = geraeteID;
        this.name = name;
        this.spenderName = spenderName;
        this.leihfrist = leihfrist;
        this.kategorie = kategorie;
        this.beschreibung = beschreibung;
        this.abholort = abholort;
        reservierungsliste = new ArrayList<Ausleiher>();
        historie = new ArrayList<>();
        leihstatus = Status.FREI;
    }

    public void reservierungHinzufuegen(String personenID) {
        Ausleiher ausleiher = new Ausleiher(personenID);

        // ausrechnen, wann er das Gerät abholen kann
        if (leihstatus == Status.FREI) {
            ausleiher.setFristBeginn(LocalDateTime.now());
            leihstatus = Status.BEANSPRUCHT;
        } else {
            Ausleiher letzter = reservierungsliste.get(reservierungsliste.size() - 1);
            ausleiher.setFristBeginn(letzter.getFristBeginn().plusDays(leihfrist));
        }

        reservierungsliste.add(ausleiher);
    }


    // Entfernt eine Reservierung von der Reservierungsliste.
    public void reservierungEntfernen(String personenID) {
        for (Ausleiher a : reservierungsliste) {
            if (Objects.equals(a.getMitlgiedsID(), personenID)) {
                reservierungsliste.remove(a);
                break;
            }
        }
        updateFristen();
    }

    public void ausgeben() throws Exception {
        leihstatus = Status.AUSGELIEHEN;
    }

    public void updateFristen() {
        if (reservierungsliste.isEmpty()) leihstatus = Status.FREI;

        for (int i = 0; i < reservierungsliste.size(); i++) {
            reservierungsliste.get(i).setFristBeginn(LocalDateTime.now().plusDays((long) leihfrist * i));
        }
    }

    public void annehmen() {
        historie.add(reservierungsliste.get(0));//aktuellen Ausleiher zur Historie hinzufügen
        reservierungsliste.remove(0);// aktuellen Ausleiher aus Reservierungsliste entfernen
        leihstatus = Status.BEANSPRUCHT;
        updateFristen();// Fristbeginn der anderen Ausleiher neu berechnen oder Leihstatus auf frei setzten
    }

    public String getGeraeteID() {
        return geraeteID;
    }

    public String getSpenderName() {
        return spenderName;
    }

    public String getGeraetName() {
        return name;
    }

    public String getGeraetAbholort() {
        return abholort;
    }

    public String getKategorie() {
        return kategorie;
    }

    public String getGeraetBeschreibung() {
        return beschreibung;
    }

    public int getLeihfrist() {
        return leihfrist;
    }

    public Status getLeihstatus() {
        return leihstatus;
    }

    public ArrayList<Ausleiher> getReservierungsliste() {
        return reservierungsliste;
    }


    public void setHistorie(ArrayList<Ausleiher> historie) {
        this.historie = historie;
        this.leihstatus = Status.FREI;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpenderName(String spenderName) {
        this.spenderName = spenderName;
    }

    public void setLeihfrist(int leihfrist) {
        this.leihfrist = leihfrist;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public void setAbholort(String abholort) {
        this.abholort = abholort;
    }
}