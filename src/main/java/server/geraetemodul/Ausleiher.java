package server.geraetemodul;

import java.time.LocalDateTime;

public class Ausleiher {
    private final LocalDateTime reservierdatum;
    private LocalDateTime fristBeginn;
    private final boolean abgegeben;
    private final String mitgliedsID;

    public Ausleiher(String mitgliedsID) {
        this.reservierdatum = LocalDateTime.now();
        this.mitgliedsID = mitgliedsID;
        abgegeben = false;
    }

    public LocalDateTime getFristBeginn() {
        return fristBeginn;
    }

    public void setFristBeginn(LocalDateTime fristBeginn) {
        this.fristBeginn = fristBeginn;
    }

    public String getMitlgiedsID() {
        return mitgliedsID;
    }

    public LocalDateTime getReservierdatum() {
        return reservierdatum;
    }

    public boolean isAbgegeben() {
        return abgegeben;
    }

}
