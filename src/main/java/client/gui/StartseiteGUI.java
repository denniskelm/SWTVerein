package client.gui;

import client.*;
import client.gui.Geraete.GeraeteDatenbankGUI;
import client.gui.Geraete.GeraeteListeGUI;
import client.gui.Login.LoginGUI;
import client.gui.Profilseite.Profilseite;
import client.gui.Registrieren.RegistrierenGUI;
import client.gui.Rollenverwaltung.RollenVerwaltungGastGUI;
import client.gui.dienstleistungen.dienstleistungsangebote.DienstleistungsangeboteGUI;
import client.gui.dienstleistungen.dienstleistungsangebote.DienstleistungsangebotsVerwaltungGUI;
import client.gui.dienstleistungen.dienstleistungsgesuche.DienstleistungsgesuchVerwaltungGUI;
import client.gui.dienstleistungen.dienstleistungsgesuche.DienstleistungsgesucheGUI;
import server.users.Rolle;

import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.rmi.NoSuchObjectException;

/**
 * @author Dennis Kelm
 * Stellt die Startseite dar, und leitet somit auf die weiteren Fenster der Anwendung weiter (unterscheidet dabei in angemeldet und unangemeldet)
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
    private JButton logoutButton;
    private JLabel secondLeftDescriptionText;


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
        secondLeftDescriptionText.setText("<html><p style=\"width:300px\">Sie haben Ger" + Umlaut.ae() + "te " + Umlaut.ue() + "brig? " +
                "Dann spenden Sie sie uns! Wir werden Sie f" + Umlaut.ue() + "r Ihr Engagement auf Ihrer Profilseite w" + Umlaut.ue() + "rdigen! Sie k" + Umlaut.oe() + "nnen uns hier auch direkt kontaktieren</p>");
        secondLeftHeadline.setText("<html>... oder lieber ein <b>Ger" + Umlaut.ae() + "t spenden</b>?</html>");
        firstRightText.setText("<html><p style=\"width:300px\">Hier finden Sie alle Angebote von Mitgliedern, die Ihnen bei Ihrer Arbeit unter die Arme greifen m" + Umlaut.oe() + "chten!</p>");
        newsHeadline.setText("Neuigkeiten und Ank" + Umlaut.ue() + "ndigungen");
        welcomeBigHeadline.setText("Willkommen! Was m" + Umlaut.oe() + "chten Sie tun?");
        geraeteAnzeigen.setText("Jetzt alle Ger" + Umlaut.ae() + "te anzeigen");
        geraeteverwaltungButton.setText("Ger" + Umlaut.ae() + "tedatenbank");

        vereinsnameText.setText(Einstellungen.getVereinsname());

        updateProfilButtons();

        newsText.setText("<html><p style=\"width: 270px\"><small><br/></small>" + Einstellungen.getNeuigkeiten() + " </p>");
        scrollNews.getVerticalScrollBar().setUnitIncrement(5);


        //Untertitel setzen mit Namen, falls angemeldet (Gast-Text kommt bei Abmeldung bei repaint wieder)
        //Ausserdem werden die sensiblen Buttons fuer die Verwaltung aus- bzw eingeblendet


        dienstleistungsangeboteButton.addActionListener(e -> {
            new DienstleistungsangeboteGUI();
        });
        dienstleistungsgesucheButton.addActionListener(e -> {
            new DienstleistungsgesucheGUI();
        });
        dienstleistungsangebotsverwaltungButton.addActionListener(e -> {
            new DienstleistungsangebotsVerwaltungGUI();
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
                            "%0D%0A%0D%0ALiebe%20Gr%C3%BC%C3%9Fe%0D%0ANAME%20EINF%C3%9CGEN";
                    URI mailto = new URI(link);
                    desktop.mail(mailto);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                throw new RuntimeException("Dieses Geraet hat keine Mail-Funktion!");
            }
        });

        dienstleistungsgesuchsverwaltungButton.addActionListener(e -> {
            new DienstleistungsgesuchVerwaltungGUI();
        });

        geraeteverwaltungButton.addActionListener(e -> {
            new GeraeteDatenbankGUI();
        });

        geraeteAnzeigen.addActionListener(e -> {
            new GeraeteListeGUI();
        });

        profilButton.addActionListener(e -> {
            try {
                new Profilseite(Vereinssoftware.session.getID());
            } catch (NoSuchObjectException ex) {
                throw new RuntimeException(ex);
            }
        });

        loginButton.addActionListener(e -> {
            new LoginGUI();
        });

        registrierenButton.addActionListener(e -> {
            new RegistrierenGUI();
        });

        rollenverwaltungButton.addActionListener(e -> {
            new RollenVerwaltungGastGUI();
        });

        logoutButton.addActionListener(e -> {
            Vereinssoftware.session.logout();
            updateProfilButtons();
        });

    }

    /**
     * Zeigt an, ob der Nutzer bereits in der Software eingeloggt ist oder nicht
     */
    public boolean isLoggedIn() {
        try {
            return Vereinssoftware.session.getID() != null && Vereinssoftware.rollenverwaltung != null;
        } catch (NoSuchObjectException e) {
            return false;
        }
    }

    /**
     * ??ndert die Sichtbarkeit der Buttons "Profilseite", "Login", "Registrieren"
     */
    public void updateProfilButtons() {

        //Test auf Einloggen
        try {
            Object[] loginData = Vereinssoftware.rollenverwaltung.login(Vereinssoftware.rollenverwaltung.getMitgliedsMail(Vereinssoftware.session.getID()), Vereinssoftware.session.getPasswort());
            String userId = loginData[0].toString();
            Rolle rolle = Rolle.valueOf(loginData[1].toString());

            Vereinssoftware.session.setID(userId);
            Vereinssoftware.session.setRolle(rolle);

            System.out.println("Eingeloggt: " + isLoggedIn() + " mit Rolle " + Vereinssoftware.session.getRolle());
        } catch (Exception e) {
            System.out.println("Nicht angemeldet");
        }

        profilButton.setVisible(isLoggedIn());
        loginButton.setVisible(!isLoggedIn());
        logoutButton.setVisible(isLoggedIn());
        registrierenButton.setVisible(!isLoggedIn());
        managePanel.setVisible(false);

        try {
            if (isLoggedIn()) {
                subtitleHeaderText.setText("Hallo " + Vereinssoftware.rollenverwaltung.getMitgliedsNamen(Vereinssoftware.session.getID()) + "! Sie sind jetzt erfolgreich angemeldet.");
                if (Vereinssoftware.session.getRolle() == Rolle.MITARBEITER || Vereinssoftware.session.getRolle() == Rolle.VORSITZ) {
                    managePanel.setVisible(true);
                }
            } else {
                subtitleHeaderText.setText("Hallo Gast! Registrieren Sie sich jetzt, oder melden Sie sich an, um alle Funktionen nutzen zu k" + Umlaut.oe() + "nnen!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        startseite.revalidate();
        startseite.repaint();
    }
}
