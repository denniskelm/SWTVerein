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
import java.awt.*;
import java.io.File;
import java.io.IOException;

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
    private JLabel secondLeftDescriptionText;
    private JLabel secondLeftHeadline;
    private JLabel firstLeftDescriptionText;
    private JButton jetztHelferImVereinButton;
    private JButton bietenSieJetztIhreButton;
    private JPanel newsPanel;
    private JLabel newsHeadline;

    public static void main(String[] args) {

        FlatLightLaf.setup();
        JFrame frame = new JFrame("Startseite");
        frame = DefaultsClient.standardizeFrame(frame, new Startseite().startseite);
    }
}
