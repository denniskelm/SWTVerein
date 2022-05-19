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

import client.DefaultsClient;
import client.Vereinssoftware;
import client.gui.Geräte.GeraetAnzeigenGUI;
import client.gui.Profilseite.ProfilseiteEigene;
import client.gui.dienstleistungen.dienstleistungsangebote.DienstleistungsangeboteGUI;
import client.gui.dienstleistungen.dienstleistungsangebote.DienstleistungsangebotsVerwaltungGUI;
import client.gui.dienstleistungen.dienstleistungsgesuche.DienstleistungsgesucheGUI;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Enumeration;

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
    private JButton secondLeftButton;
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


    //Fügt Funktionalität der Startseite hinzu
    public StartseiteGUI() {
        JFrame frame = new JFrame("Startseite");
        frame = DefaultsClient.standardizeFrame(frame, startseite);


        dienstleistungsangeboteButton.addActionListener(e -> {
            DienstleistungsangeboteGUI dienstleistungsangeboteGUI = new DienstleistungsangeboteGUI();
        });
        dienstleistungsgesucheButton.addActionListener(e -> {
            DienstleistungsgesucheGUI dienstleistungsgesucheGUI = new DienstleistungsgesucheGUI();
        });
        dienstleistungsangebotsverwaltungButton.addActionListener(e -> {
            DienstleistungsangebotsVerwaltungGUI dienstleistungsangebotsVerwaltungGUI = new DienstleistungsangebotsVerwaltungGUI();
        });

        secondLeftButton.addActionListener(e -> {
            DefaultTextWithButton defaultTextWithButton = new DefaultTextWithButton("String titel", "String popupText", "String buttonText");
            defaultTextWithButton.getActionButton().addActionListener(ev -> System.out.println("Funktioniert"));

        });

    }
}
