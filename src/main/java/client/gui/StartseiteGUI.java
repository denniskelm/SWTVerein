package client.gui;
/*
@author
TODO Raphael Kleebaum
TODO Jonny Schlutter
TODO Gabriel Kleebaum
TODO Mhd Esmail Kanaan
TODO Gia Huy Hans Tran
TODO Ole Björn Adelmann
TODO Bastian Reichert
Dennis Kelm
*/

import client.ClientDefaults;
import client.gui.Geräte.GeräteDatenbankGUI;
import client.gui.Geräte.GeräteListeGUI;
import client.gui.Login.LoginGUI;
import client.gui.Registrieren.RegistrierenGUI;
import client.gui.dienstleistungen.dienstleistungsangebote.DienstleistungsangeboteGUI;
import client.gui.dienstleistungen.dienstleistungsangebote.DienstleistungsangebotsVerwaltungGUI;
import client.gui.dienstleistungen.dienstleistungsgesuche.DienstleistungsgesuchVerwaltungGUI;
import client.gui.dienstleistungen.dienstleistungsgesuche.DienstleistungsgesucheGUI;

import javax.swing.*;

/*Stellt die Startseite dar, und leitet somit auf die weiteren Fenster der Anwendung weiter (unterscheidet dabei
 * in angemeldet und unangemeldet)
 * */
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
    private JButton anfragenButton;


    //Fügt Funktionalität der Startseite hinzu
    public StartseiteGUI() {
        JFrame frame = new JFrame("Startseite");
        frame = ClientDefaults.standardizeFrame(frame, startseite);

        dienstleistungsangeboteButton.addActionListener(e -> {
            DienstleistungsangeboteGUI dienstleistungsangeboteGUI = new DienstleistungsangeboteGUI();
        });
        dienstleistungsgesucheButton.addActionListener(e -> {
            DienstleistungsgesucheGUI dienstleistungsgesucheGUI = new DienstleistungsgesucheGUI();
        });
        dienstleistungsangebotsverwaltungButton.addActionListener(e -> {
            DienstleistungsangebotsVerwaltungGUI dienstleistungsangebotsVerwaltungGUI = new DienstleistungsangebotsVerwaltungGUI();
        });

        geraetHinzufuegen.addActionListener(e -> { //TODO Email Client öffnen?
            // hier vielleicht pop-up mit einer Beschreibung wie man Mitarbeiter kontaktieren kann um ein Gerät zu spenden.
            JOptionPane.showMessageDialog(startseite, "Sie können einen unserer Mitarbeiter über ... kontaktieren.", "Sie möchten ein Gerät spenden?", JOptionPane.PLAIN_MESSAGE);
        });

        dienstleistungsgesuchsverwaltungButton.addActionListener(e -> {
            DienstleistungsgesuchVerwaltungGUI dienstleistungsangebotsVerwaltungGUI = new DienstleistungsgesuchVerwaltungGUI();
        });

        geraeteverwaltungButton.addActionListener(e -> {
            GeräteDatenbankGUI ghg = new GeräteDatenbankGUI("Gerätedatenbank");
            ghg.setVisible(true);
        });

        geraeteAnzeigen.addActionListener(e -> {
            GeräteListeGUI ghg = new GeräteListeGUI("Geräteliste");
            ghg.setVisible(true);
        });

        anfragenButton.addActionListener(e -> {
            //TODO Anfrageliste
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
