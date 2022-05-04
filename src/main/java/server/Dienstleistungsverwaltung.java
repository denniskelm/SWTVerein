package server;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.awt.Image;
import java.time.LocalDateTime;
import java.lang.Object;

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

public class Dienstleistungsverwaltung {

    public static void gesuchErstellen(String titel, String beschreibung, String kategorie, Image[] bilder, String ersteller) {

    }

    public static void angebotErstellen(String titel, String beschreibung, String kategorie, LocalDateTime ab, LocalDateTime bis) {

    }

    public static void gesuchLoeschen(Dienstleistungsgesuch gesuch) {

    }

    public static void angebotLoeschen(Dienstleistungsangebot angebot) {

    }

    public static void gesuchAendern(Dienstleistungsgesuch gesuch, Object attr, Object wert) {

    }

    public static void angebotAendern(Dienstleistungsangebot angebot, Object attr, Object wert) {

    }

    public static void gesuchAnnehmen(Dienstleistungsgesuch gesuch, String ersteller, String nutzer, int stunden) {

    }

    public static void angebotAnfragen(Dienstleistungsangebot angebot, String ersteller, String fragender) {

    }

}
