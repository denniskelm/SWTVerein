package client.gui.dienstleistungen.dienstleistungsgesuche;
/*
@author
Dennis Kelm
*/

import client.ClientDefaults;
import client.Kategorie;
import client.Umlaut;
import client.Vereinssoftware;
import client.gui.DefaultSmallPopup;
import client.gui.dienstleistungen.dienstleistungsangebote.DienstleistungsangebotsVerwaltungGUI;
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
    private JButton gesuchLoeschenButton;
    private JPanel buttonPanel;

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

        gesuchLoeschenButton.setText("Gesuch l" + Umlaut.oe() + "schen");


        gesuchBearbeitenButton.addActionListener(e -> {

            gesuchBearbeiten(gesuchsID, titleTextField.getText(), urlTextField.getText(), beschreibungTextArea.getText(), kategorieComboBox.getSelectedItem().toString());

        });

        gesuchLoeschenButton.addActionListener(e -> {
            try {
                Vereinssoftware.dienstleistungsverwaltung.gesuchLoeschen(gesuchsID);
                new DefaultSmallPopup("Gesuch geloescht", "Ihr Gesuch mit dem Titel \"" + titel + "\" wurde erfolgreich gel" + Umlaut.oe() + "scht!");
                frame.dispose();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    private void gesuchBearbeiten(String gesuchID, String title, String url, String beschreibung, String kategorie) {
        try {
            Vereinssoftware.dienstleistungsverwaltung.gesuchAendern(gesuchID, Dienstleistungsgesuchdaten.TITEL, title);
            Vereinssoftware.dienstleistungsverwaltung.gesuchAendern(gesuchID, Dienstleistungsgesuchdaten.URL, url);
            Vereinssoftware.dienstleistungsverwaltung.gesuchAendern(gesuchID, Dienstleistungsgesuchdaten.BESCHREIBUNG, beschreibung);
            Vereinssoftware.dienstleistungsverwaltung.gesuchAendern(gesuchID, Dienstleistungsgesuchdaten.KATEGORIE, kategorie);

            frame.dispose();
            new DienstleistungsgesuchVerwaltungGUI();
            new DefaultSmallPopup("Erfolgreiche " + Umlaut.Ae() + "nderung", "Die Daten wurden erfolgreich in der Datenbank ge" + Umlaut.ae() + "ndert!");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }

}
