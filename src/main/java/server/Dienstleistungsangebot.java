package server;

import java.time.LocalDateTime;

public class Dienstleistungsangebot {

    private String d_angebotID;
    private String titel;
    private String beschreibung;
    private String kategorie;
    private LocalDateTime ab;
    private LocalDateTime bis;
    private Mitglied dienstleister;

    public Dienstleistungsangebot(String d_angebotID, String titel, String beschreibung, String kategorie, LocalDateTime ab, LocalDateTime bis , Mitglied dienstleister){
        this.d_angebotID = d_angebotID;
        this.titel = titel;
        this.beschreibung = beschreibung;
        this.kategorie = kategorie;
        this.ab = ab;
        this.bis = bis;
        this.dienstleister = dienstleister;
    }


}
