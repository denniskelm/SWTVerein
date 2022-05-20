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
import client.gui.Geräte.GerätHinzufügenGUI;
import client.gui.Geräte.GeräteDatenbankGUI;
import client.gui.dienstleistungen.dienstleistungsangebote.DienstleistungsangeboteGUI;
import client.gui.dienstleistungen.dienstleistungsangebote.DienstleistungsangebotsVerwaltungGUI;
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
    private JButton firstLeftButton;
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

        geraetHinzufuegen.addActionListener(e -> {
            GerätHinzufügenGUI ghg = new GerätHinzufügenGUI("Neues Gerät hinzufügen");
            ghg.setVisible(true);
        });

        anfragenButton.addActionListener(e -> {
            //TODO Anfrageliste
        });



    }
}
