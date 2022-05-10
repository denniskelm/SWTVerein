package client.gui.dienstleistungen.dienstleistungsangebote;
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
import client.gui.DefaultSmallPopup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

//GUI Funktionalität für das Erstellen von Dienstleistungsangeboten
public class DienstleistungsangebotBearbeitenGUI {
    private JPanel dienstleistungsangebotBearbeitenPanel;
    private JFormattedTextField titleTextField;
    private JTextArea beschreibungTextArea;
    private JComboBox kategorieComboBox;
    private JFormattedTextField verfuegbarAbTextField;
    private JFormattedTextField verfuegbarBisTextField;
    private JButton angebotBearbeitenButton;
    private JPanel verfuegbarPanel;
    private JPanel kategoriePanel;
    private JPanel beschreibungPanel;
    private JPanel titlePanel;
    private JPanel verfuegbarAbPanel;
    private JPanel verfuegbarBisPanel;
    private JFormattedTextField urlTextField;
    private JPanel urlPanel;

    private JFrame frame;


    public DienstleistungsangebotBearbeitenGUI() {
        frame = new JFrame("Dienstleistungsangebot bearbeiten");
        frame = DefaultsClient.standardizeFrame(frame, this.dienstleistungsangebotBearbeitenPanel);
        JTextField[] allTextFields = new JTextField[]{
                titleTextField,
                verfuegbarAbTextField,
                verfuegbarBisTextField,
                urlTextField
        };

        //TODO ALLE FELDER MIT DEN AKTUELLEN WERTEN FÜLLEN

        angebotBearbeitenButton.addActionListener(e -> {
            angebotBearbeitenGUI();
            frame.dispose();
        });

    }

    public static void main(String[] args) {
        DienstleistungsangebotBearbeitenGUI thisgui = new DienstleistungsangebotBearbeitenGUI();
    }

    private void angebotBearbeitenGUI() {
        DefaultSmallPopup smallPopup = new DefaultSmallPopup("Angebot erfolgreich bearbeitet", "Ihr Dienstleistungsangebot wurde erfolgreich bearbeitet!");
    }

}
