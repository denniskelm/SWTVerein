package client;
/*
@author
TODO Raphael Kleebaum
TODO Jonny Schlutter
TODO Gabriel Kleebaum
TODO Mhd Esmail Kanaan
TODO Gia Huy Hans Tran
TODO Ole Bjoern Adelmann
TODO Bastian Reichert
Dennis Kelm
*/

//Setze die vereinsspezifischen Einstellungen wie Name etc.
public class Einstellungen {

    private static final String Vereinsname = "Verein e.V.";
    private static final String Kontaktmailadresse = "email@denniskelm.de";


    public static String getVereinsname() {
        return Vereinsname;
    }

    public static String getKontaktmailadresse() {
        return Kontaktmailadresse;
    }

    public static String getNeuigkeiten() {

        return "14.06.2022 - Das sind doch tolle Neuigkeiten, oder? <br/>" +
                "neue Zeile";

    }
}
