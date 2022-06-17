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

    private static String newLine = "<br/>";

    private static final String Vereinsname = "Verein e.V.";
    private static final String Kontaktmailadresse = "email@denniskelm.de";


    public static String getVereinsname() {
        return Vereinsname;
    }

    public static String getKontaktmailadresse() {
        return Kontaktmailadresse;
    }

    public static String getNeuigkeiten() {

        String neuigkeiten =
                addNeuigkeit("30.06.2022", "Sommerfest der AUF, Elektrotechnik und Informatik", "Weitere Informationen im Internet") +
                        addNeuigkeit("03.07.2022", "World Meerkat Day", "Endlich ist wieder der internationale Tag der Erdmännchen") +
                        addNeuigkeit("10.07.2022", "Tag des Teddyb" + Umlaut.ae() + "är-Picknicks", "Wow! Diesen Tag hat jeder gebraucht: der internationale Tag w" + Umlaut.ue() + "");


        return neuigkeiten;
    }

    private static String addNeuigkeit(String datum, String titel, String nachrichtentext) {
        return datum + " - " + "<b>" + titel + "</b> | " + nachrichtentext + "<br/>";
    }

}
