package client;
/*
@author
TODO Raphael Kleebaum
TODO Jonny Schlutter
Gabriel Kleebaum
TODO Mhd Esmail Kanaan
TODO Gia Huy Hans Tran
TODO Ole Björn Adelmann
TODO Bastian Reichert
Dennis Kelm
*/

import client.gui.StartseiteGUI;
import com.formdev.flatlaf.FlatLightLaf;
import shared.communication.IDienstleistungsverwaltung;
import shared.communication.IGeraeteverwaltung;
import shared.communication.IMahnungsverwaltung;
import shared.communication.IRollenverwaltung;

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

//Klasse, die das Programm startet
public class Vereinssoftware {
    public static IGeraeteverwaltung geraeteverwaltung;
    public static IDienstleistungsverwaltung dienstleistungsverwaltung;
    public static IMahnungsverwaltung mahnungsverwaltung;
    public static IRollenverwaltung rollenverwaltung;
    public static Session session;

    public static void main(String[] args) {

        //Initialisiert die verschiedenen UI-Einstellungen (Font etc.)
        initializeUISettings();

        //Starte die Startseite
        StartseiteGUI startseiteGUI = new StartseiteGUI();
        System.out.println("Startseite startet");

        session = new Session();

        Object[][] ar = new Object[][]{
                new Object[]{"Kaka machen", "Wieso muss ich nur so doll?"},
                new Object[]{"Kaka machen", "Wieso muss ich nur so doll?"},
                new Object[]{"Kaka machen", "Wieso muss ich nur so doll?"},
                new Object[]{"Kaka machen", "Wieso muss ich nur so doll?"}
        };

        System.out.println(ar[0][0]);

        Object ar2 = (Object) ar;

        System.out.println(((Object[][]) ar2)[0][0]);


        //RMI ermöglichen
        try {
            initializeRMI();
        } catch (RemoteException | NotBoundException e) {
            throw new RuntimeException(e);
        }



    }

    public static void initializeRMI() throws RemoteException, NotBoundException {
        //Registry registry = LocateRegistry.getRegistry("meta.informatik.uni-rostock.de", 1234);
        Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1234);

        geraeteverwaltung = (IGeraeteverwaltung) registry.lookup("Geraeteverwaltung");
        dienstleistungsverwaltung = (IDienstleistungsverwaltung) registry.lookup("Dienstleistungsverwaltung");
        mahnungsverwaltung = (IMahnungsverwaltung) registry.lookup("Mahnungsverwaltung");
        rollenverwaltung = (IRollenverwaltung) registry.lookup("Rollenverwaltung");

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
}
