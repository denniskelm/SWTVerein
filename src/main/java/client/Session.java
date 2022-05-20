package client;

import server.users.Rolle;

import java.rmi.NoSuchObjectException;
import java.util.prefs.Preferences;

/*
@author
TODO Raphael Kleebaum
TODO Jonny Schlutter
Gabriel Kleebaum
Mhd Esmail Kanaan
TODO Gia Huy Hans Tran
TODO Ole Björn Adelmann
TODO Bastian Reichert
TODO Dennis Kelm
*/

//Diese Klasse speichert (Nutzer-)Daten auf dem PC des Nutzers
public class Session {
    private Preferences prefs;
    private final String ERROR = "FEHLER";

    public Session() {
        // legt Speicherort für die Preferences fest
        prefs = Preferences.userRoot().node(this.getClass().getName());
    }

    //Speichert den Wert value und macht ihn mit Schlüssel key erreichbar/auslesbar
    public void setPreference(String key, String value) {
        prefs.put(key , value);
    }

    //Liest den Wert vom Schlüssel key aus; wenn kein Wert eingetragen, dann wird def zurückgegeben
    private String getPreference(String key, String def) {
        return prefs.get(key, def);
    }

    //Liest den Wert vom Schlüssel key aus; wenn kein Wert eingetragen, dann wird Exception geworfen
    public String getPreference(String key) throws NoSuchObjectException {
        String preference = getPreference(key, ERROR);

        if(preference.equals(ERROR))
            throw new NoSuchObjectException("Wert nicht gesetzt!");

        return preference;
    }

    //Liest den Nutzernamen des Users aus; Wenn kein Username gesetzt, wird Exception geworfen
    public String getUsername() throws NoSuchObjectException {
        String username = getPreference("Username", ERROR);

        if(username.equals(ERROR))
            throw new NoSuchObjectException("Wert nicht gesetzt!");

        return username;
    }

    //Liest die Rolle aus; Wenn keine Rolle gesetzt, wird Exception geworfen
    public Rolle getRolle() throws NoSuchObjectException {
        String rolleStr = getPreference("Rolle", ERROR);

        if(rolleStr.equals(ERROR))
            throw new NoSuchObjectException("Wert nicht gesetzt!");

        Rolle rolle = Rolle.valueOf(rolleStr);

        return rolle;
    }

    //Liest das Passwort aus; Wenn kein Passwort gesetzt, wird Exception geworfen
    public String getPasswort() throws NoSuchObjectException {
        String passwort = getPreference("Passwort", ERROR);

        if(passwort.equals(ERROR))
            throw new NoSuchObjectException("Wert nicht gesetzt!");

        return passwort;
    }

    //speichert den Nutzernamen in den Preferences
    public void setUsername(String username) {
        setPreference("Username", username);
    }

    //Speichert die Rolle in den Preferences
    public void setRolle(Rolle rolle) {
        setPreference("Rolle", rolle.toString());
    }

    //Speichert die Rolle in den Preferences
    public void setRolle(String rolle) {
        setPreference("Rolle", rolle);
    }

    //Speichert das Passwort in den Preferences
    public void setPasswort(String passwort) {
        setPreference("Passwort", passwort);
    }

    public void setID(String id) {
        setPreference("ID", id);
    }

    public String getID() throws NoSuchObjectException {
        String id = getPreference("ID", ERROR);

        if(id.equals(ERROR))
            throw new NoSuchObjectException("Wert nicht gesetzt");

        return id;
    }

}
