package client;
/*
@author
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

        String neuigkeiten =
                addNeuigkeit("30.06.2022", "Sommerfest der AUF, Elektrotechnik und Informatik", "Weitere Informationen im Internet") +
                        addNeuigkeit("03.07.2022", "World Meerkat Day", "Endlich ist wieder der internationale Tag der Erdmännchen, wir treffen uns zum gemeinsamen Beobachten im Zoo") +
                        addNeuigkeit("10.07.2022", "Tag des Teddyb" + Umlaut.ae() + "är-Picknicks", "Wow! Diesen Tag hat jeder gebraucht: der internationale Tag w" + Umlaut.ue() +
                                "rdigt endlich dieses unglaublich tolle Ritual, mit seinem Kuscheltier Essen zu gehen") +
                        addNeuigkeit("17.07.2022", "Welt-Emoji-Tag", "Ein Emoji sagt mehr als 1000 Worte! So hat es sich der Erfinder Scott Fahlman wohl 1982 gedacht, als er mit :-) die Grundlage unserer heutigen Kommunikation erschuf");

        return neuigkeiten;
    }

    private static String addNeuigkeit(String datum, String titel, String nachrichtentext) {
        return datum + " - " + "<b>" + titel + "</b> | " + nachrichtentext + "<br/><br/>";
    }

}
