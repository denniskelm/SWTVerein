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
            try {
                System.out.println(Vereinssoftware.dienstleistungsverwaltung.angebotErstellen("Hilfe bei Kaka machen",
                        "Ich helfe Ihnen", "Kaka machen", LocalDateTime.now(),
                        LocalDateTime.now().plusDays(15), "P00001"));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            //ProfilseiteEigene profilseiteEigene = new ProfilseiteEigene();

        });

    }

    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put(key, f);
        }
    }
}
