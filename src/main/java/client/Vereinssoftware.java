package client;
/*
@author
Gabriel Kleebaum
Dennis Kelm
*/

import client.gui.DefaultSmallPopup;
import client.gui.StartseiteGUI;
import com.formdev.flatlaf.FlatLightLaf;
import shared.communication.*;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Enumeration;
import java.util.prefs.Preferences;

//Klasse, die das Programm startet
public class Vereinssoftware {
    public static IGeraeteverwaltung geraeteverwaltung;
    public static IDienstleistungsverwaltung dienstleistungsverwaltung;
    public static IRollenverwaltung rollenverwaltung;
    public static IAnfragenVerwaltung anfragenVerwaltung;
    public static Session session;
    private static boolean connectionSuccessful = false;
    public static StartseiteGUI startseite;

    public static void main(String[] args) {


        //Initialisiert die verschiedenen UI-Einstellungen (Font etc.)
        initializeUISettings();

        session = new Session();


        //RMI ermoeglichen
        try {
            initializeRMI();
            connectionSuccessful = true;
        } catch (RemoteException | NotBoundException e) {
            DefaultSmallPopup connectionErrorFrame = new DefaultSmallPopup("Fehler bei der Serververbindung",
                    "Die Verbindung zum Server schlug mit diesem Fehler fehl: " + e);
            throw new RuntimeException(e);
        }

        //Starte die Startseite
        startseite = new StartseiteGUI();
        System.out.println("Startseite startet");

        if (connectionSuccessful) {
            DefaultSmallPopup connectionSuccessfulFrame = new DefaultSmallPopup("Erfolgreiche Serververbindung",
                    "Die Verbindung zum Server wurde erfolgreich hergestellt!");
        }
    }

    public static void initializeRMI() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("meta.informatik.uni-rostock.de", 5678);
        //Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1234);

        geraeteverwaltung = (IGeraeteverwaltung) registry.lookup("Geraeteverwaltung");
        dienstleistungsverwaltung = (IDienstleistungsverwaltung) registry.lookup("Dienstleistungsverwaltung");
        //mahnungsverwaltung = (IMahnungsverwaltung) registry.lookup("Mahnungsverwaltung");
        rollenverwaltung = (IRollenverwaltung) registry.lookup("Rollenverwaltung");
        anfragenVerwaltung = (IAnfragenVerwaltung) registry.lookup("AnfragenVerwaltung");
    }

    private static void initializeUISettings() {
        FlatLightLaf.setup();

        try {
            GraphicsEnvironment ge =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font newFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/fonts/Asap-VariableFont_wght.ttf")).deriveFont(Font.PLAIN, 15);
            ge.registerFont(newFont);
            setUIFont(new FontUIResource(newFont));
        } catch (IOException | FontFormatException e) {
            System.out.println("Fehler " + e);
        }
    }

    private static void setUIFont(javax.swing.plaf.FontUIResource f) {
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put(key, f);
        }
    }

    public static StartseiteGUI getStartseite() {
        return startseite;
    }
}
