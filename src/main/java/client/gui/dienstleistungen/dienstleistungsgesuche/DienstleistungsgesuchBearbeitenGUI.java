package client.gui.dienstleistungen.dienstleistungsgesuche;
/*
@author
TODO Raphael Kleebaum
TODO Jonny Schlutter
TODO Gabriel Kleebaum
TODO Mhd Esmail Kanaan
TODO Gia Huy Hans Tran
TODO Ole Bjoern Adelmann
TODO Bastian Reichert
Dennis Kelm
*/

import client.ClientDefaults;
import client.Kategorie;
import client.Vereinssoftware;
import client.gui.DefaultSmallPopup;
import server.dienstleistungsmodul.Dienstleistungsgesuchdaten;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

//GUI Funktionalitaet fuer das Erstellen von Dienstleistungsgesuchen
public class DienstleistungsgesuchBearbeitenGUI {
    private JPanel dienstleistungsgesuchBearbeitenPanel;
    private JFormattedTextField titleTextField;
    private JTextArea beschreibungTextArea;
    private JComboBox kategorieComboBox;
    private JButton gesuchBearbeitenButton;
    private JPanel kategoriePanel;
    private JPanel beschreibungPanel;
    private JPanel titlePanel;
    private JFormattedTextField urlTextField;
    private JPanel urlPanel;

    private JFrame frame;

    private final Map<JTextField, Boolean> onceChanged = new HashMap<JTextField, Boolean>();

    public DienstleistungsgesuchBearbeitenGUI(String gesuchsID, String titel, String pathToImage, String beschreibung, String kategorieText) {
        frame = new JFrame("Dienstleistungsgesuch bearbeiten");
        frame = ClientDefaults.standardizeFrame(frame, this.dienstleistungsgesuchBearbeitenPanel);

        titleTextField.setText(titel);
        urlTextField.setText(pathToImage);
        beschreibungTextArea.setText(beschreibung);
        kategorieComboBox.addItem(kategorieText);

        //Dropdown mit Werten fuellen
        for (String kategorie :
                ClientDefaults.getKategorien()) {
            kategorieComboBox.addItem(kategorie);
        }


        gesuchBearbeitenButton.addActionListener(e -> {

            gesuchBearbeiten(gesuchsID, titleTextField.getText(), urlTextField.getText(), beschreibungTextArea.getText(), kategorieComboBox.getSelectedItem().toString());
            frame.dispose();
        });

    }

    private void gesuchBearbeiten(String gesuchID, String title, String url, String beschreibung, String kategorie) {
        try {
            Vereinssoftware.dienstleistungsverwaltung.gesuchAendern(gesuchID, Dienstleistungsgesuchdaten.TITEL, title);
            Vereinssoftware.dienstleistungsverwaltung.gesuchAendern(gesuchID, Dienstleistungsgesuchdaten.URL, url);
            Vereinssoftware.dienstleistungsverwaltung.gesuchAendern(gesuchID, Dienstleistungsgesuchdaten.BESCHREIBUNG, beschreibung);
            Vereinssoftware.dienstleistungsverwaltung.gesuchAendern(gesuchID, Dienstleistungsgesuchdaten.KATEGORIE, kategorie);

            DefaultSmallPopup defaultSmallPopup = new DefaultSmallPopup("aenderung erfolgreich", "Das Dienstleistungsgesuch fuer \"" + title + "\" wurde erfolgreich geaendert!");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }

}
