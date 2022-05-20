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

import client.ClientDefaults;
import client.Kategorie;
import client.Vereinssoftware;
import client.gui.DefaultSmallPopup;

import javax.swing.*;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
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

    private Map<JTextField, Boolean> onceChanged = new HashMap<JTextField, Boolean>();

    public DienstleistungsangebotBearbeitenGUI(String angebotsID, String titel, String pathToImage, String beschreibung, String kategorieText, LocalDateTime ab, LocalDateTime bis) {
        frame = new JFrame("Dienstleistungsangebot bearbeiten");
        frame = ClientDefaults.standardizeFrame(frame, this.dienstleistungsangebotBearbeitenPanel);
        JTextField[] allTextFields = new JTextField[]{
                titleTextField,
                verfuegbarAbTextField,
                verfuegbarBisTextField,
                urlTextField
        };

        //TODO ALLE FELDER MIT DEN AKTUELLEN WERTEN FÜLLEN
        //titleTextField.setText(/*Vereinssoftware.dienstleistungsverwaltung.getAngeboteInformationen()*/); usw.

        //Dropdown mit Werten füllen
        //TODO, TODO Kopieren in die anderen Klassen
        try {
            kategorieComboBox.addItem(Vereinssoftware.dienstleistungsverwaltung.getAngeboteInformationen("G00001")[4]);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        for (String kategorie :
                ClientDefaults.getKategorien(Kategorie.class)) {
            kategorieComboBox.addItem(kategorie);
        }


        for (JTextField textField :
                allTextFields) {
            ClientDefaults.enhanceTextField(textField, onceChanged);
        }

        angebotBearbeitenButton.addActionListener(e -> {
            //angebotBearbeitenGUI(); Hier Texte getten
            frame.dispose();
        });

    }

    private void angebotBearbeitenGUI(String title, String url, String beschreibung, String kategorie, LocalDateTime ab, LocalDateTime bis) {
        DefaultSmallPopup smallPopup = new DefaultSmallPopup("Angebot erfolgreich bearbeitet", "Ihr Dienstleistungsangebot wurde erfolgreich bearbeitet!");
    }

}
