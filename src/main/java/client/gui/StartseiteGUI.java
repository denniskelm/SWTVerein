package client.gui;

import client.ClientDefaults;
import client.Einstellungen;
import client.Umlaut;
import client.Vereinssoftware;
import client.gui.Geraete.GeraeteDatenbankGUI;
import client.gui.Geraete.GeraeteListeGUI;
import client.gui.Login.LoginGUI;
import client.gui.Profilseite.Profilseite;
import client.gui.Registrieren.RegistrierenGUI;
import client.gui.dienstleistungen.dienstleistungsangebote.DienstleistungsangeboteGUI;
import client.gui.dienstleistungen.dienstleistungsangebote.DienstleistungsangebotsVerwaltungGUI;
import client.gui.dienstleistungen.dienstleistungsgesuche.DienstleistungsgesuchVerwaltungGUI;
import client.gui.dienstleistungen.dienstleistungsgesuche.DienstleistungsgesucheGUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.NoSuchObjectException;

/**
 * @author Dennis Kelm
 * Stellt die Startseite dar, und leitet somit auf die weiteren Fenster der Anwendung weiter (unterscheidet dabeiin angemeldet und unangemeldet)
 */
public class StartseiteGUI {
    private JPanel startseite;
    private JPanel buttonsTopRight;
    private JButton loginButton;
    private JButton registrierenButton;
    private JLabel welcomeBigHeadline;
    private JPanel leftMainMenu;
    private JPanel rightMainMenu;
    private JLabel headlineLeftMainMenu;
    private JLabel headlineRightMainMenu;
    private JButton geraeteAnzeigen;
    private JButton geraetHinzufuegen;
    private JLabel secondLeftHeadline;
    private JLabel firstLeftDescriptionText;
    private JButton dienstleistungsangeboteButton;
    private JButton dienstleistungsgesucheButton;
    private JLabel newsHeadline;
    private JPanel newsPanel;
    private JScrollPane scrollNews;
    private JButton rollenverwaltungButton;
    private JPanel managePanel;
    private JLabel newsText;
    private JButton dienstleistungsgesuchsverwaltungButton;
    private JButton dienstleistungsangebotsverwaltungButton;
    private JButton geraeteverwaltungButton;
    private JButton profilButton;
    private JLabel vereinsnameText;
    private JLabel subtitleHeaderText;
    private JLabel firstRightText;


    //Fuegt Funktionalitaet der Startseite hinzu
    public StartseiteGUI() {
        JFrame frame = new JFrame("Startseite");
        frame = ClientDefaults.standardizeFrame(frame, startseite);

        //Stoppe das Programm, wenn die Startseite geschlossen wird
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //UE AE und OE richtig setzen
        headlineRightMainMenu.setText("<html>Wir <b>helfen</b> uns nat" + Umlaut.ue() + "rlich <b>gegenseitig</b>!</html>");
        headlineLeftMainMenu.setText("<html>M" + Umlaut.oe() + "chten Sie ein <b>Ger" + Umlaut.ae() + "t ausleihen</b>?");
        firstLeftDescriptionText.setText("<html><p style=\"width:300px\">Sehen Sie sich einfach alle Ger" + Umlaut.ae() + "te an, die wir Ihnen zum Verleih zur Verf" + Umlaut.ue() + "gung stellen!</p>");
        secondLeftHeadline.setText("<html>... oder lieber ein <b>Ger" + Umlaut.ae() + "t spenden</b>?</html>");
        firstRightText.setText("<html><p style=\"width:300px\">Hier finden Sie alle Angebote von Mitgliedern, die Ihnen bei Ihrer Arbeit unter die Arme greifen m" + Umlaut.oe() + "chten!</p>");
        newsHeadline.setText("Neuigkeiten und Ank" + Umlaut.ae() + "ndigungen");

        vereinsnameText.setText(Einstellungen.getVereinsname());

        //Untertitel setzen mit Namen, falls angemeldet
        /*try {
            if (Vereinssoftware.session.getID() != null && Vereinssoftware.rollenverwaltung != null) {
                //subtitleHeaderText.setText("Hallo " + Vereinssoftware.rollenverwaltung.getMitgliedsNamen(Vereinssoftware.session.getID()) + "!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } */

        dienstleistungsangeboteButton.addActionListener(e -> {
            DienstleistungsangeboteGUI dienstleistungsangeboteGUI = new DienstleistungsangeboteGUI();
        });
        dienstleistungsgesucheButton.addActionListener(e -> {
            DienstleistungsgesucheGUI dienstleistungsgesucheGUI = new DienstleistungsgesucheGUI();
        });
        dienstleistungsangebotsverwaltungButton.addActionListener(e -> {
            DienstleistungsangebotsVerwaltungGUI dienstleistungsangebotsVerwaltungGUI = new DienstleistungsangebotsVerwaltungGUI();
        });

        geraetHinzufuegen.addActionListener(e -> {
            Desktop desktop;
            if (Desktop.isDesktopSupported()
                    && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
                try {
                    String link = "mailto:" + Einstellungen.getKontaktmailadresse() + "?subject=Ich%20m%C3%B6chte" +
                            "%20ein%20Ger%C3%A4t%20spenden!&body=Liebe%20Vereinsmitarbeiter%2C%0D%0A%0D%0Aich%20m%C3%" +
                            "B6chte%20unserem%20Verein%20ein%20Ger%C3%A4t%20spenden!%0D%0A%0D%0AGer%C3%A4tename%3A%20" +
                            "HIER%20GER%C3%84TENAME%20EINGEBEN%0D%0A%0D%0AHIER%20WEITERE%20INFOS%20EINGEBEN" +
                            "%0D%0A%0D%0ALiebe%20Gr%C3%BC%C3%9Fe%0D%0A";// + Vereinssoftware.rollenverwaltung.getMitgliedsNamen(Vereinssoftware.session.getID()))
                    URI mailto = new URI(link);
                    desktop.mail(mailto);
                } catch (URISyntaxException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                throw new RuntimeException("Dieses Geraet hat keine Mail-Funktion!");
            }
        });

        dienstleistungsgesuchsverwaltungButton.addActionListener(e -> {
            DienstleistungsgesuchVerwaltungGUI dienstleistungsangebotsVerwaltungGUI = new DienstleistungsgesuchVerwaltungGUI();
        });

        geraeteverwaltungButton.addActionListener(e -> {
            GeraeteDatenbankGUI ghg = new GeraeteDatenbankGUI("Geraetedatenbank");
            ghg.setVisible(true);
        });

        geraeteAnzeigen.addActionListener(e -> {
            GeraeteListeGUI ghg = new GeraeteListeGUI("Geraeteliste");
            ghg.setVisible(true);
        });

        profilButton.addActionListener(e -> {
            try {
                Profilseite profilseite = new Profilseite(Vereinssoftware.session.getID());
            } catch (NoSuchObjectException ex) {
                throw new RuntimeException(ex);
            }
        });

        loginButton.addActionListener(e -> {
            LoginGUI loginGUI = new LoginGUI("Login");
            loginGUI.setVisible(true);
        });

        registrierenButton.addActionListener(e -> {
            RegistrierenGUI registrierenGUI = new RegistrierenGUI("Registrierung");
            registrierenGUI.setVisible(true);
        });

    }
}
