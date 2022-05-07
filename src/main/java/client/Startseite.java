package client;
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

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

/*Stellt die Startseite dar, und leitet somit auf die weiteren Fenster der Anwendung weiter (unterscheidet dabei
 * in angemeldet und unangemeldet)
 * */
public class Startseite {
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
    private JButton jetztHelferImVereinButton;
    private JButton bietenSieJetztIhreButton;
    private JLabel newsHeadline;
    private JPanel newsPanel;
    private JScrollPane scrollNews;
    private JButton rollenverwaltungButton;
    private JPanel managePanel;
    private JLabel newsText;
    private JButton dienstleistungsdatenbankButton;

    public Startseite() {

    }

    public static void main(String[] args) {

        FlatLightLaf.setup();
        try {
            GraphicsEnvironment ge =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font newFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/fonts/Asap-VariableFont_wght.ttf")).deriveFont(Font.PLAIN, 15);
            ge.registerFont(newFont);
            System.out.println(Arrays.toString(ge.getAvailableFontFamilyNames()));
            setUIFont(new FontUIResource(newFont));
        } catch (IOException | FontFormatException e) {
            System.out.println("Fehler " + e);
        }

        JFrame frame = new JFrame("Startseite");
        frame = DefaultsClient.standardizeFrame(frame, new Startseite().startseite);
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
