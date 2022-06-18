package client.gui.dienstleistungen.dienstleistungsangebote;
/*
@author
Dennis Kelm
*/

import client.ClientDefaults;
import client.Kategorie;
import client.Umlaut;
import client.Vereinssoftware;
import client.gui.DefaultSmallPopup;
import server.dienstleistungsmodul.Dienstleistungsangebotdaten;

import javax.swing.*;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

//GUI Funktionalitaet fuer das Erstellen von Dienstleistungsangeboten
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
    private JButton angebotLoeschenButton;
    private JFrame frame;

    private final Map<JTextField, Boolean> onceChangedFields = new HashMap<JTextField, Boolean>();

    public DienstleistungsangebotBearbeitenGUI(String angebotsID, String titel, String pathToImage, String beschreibung, String kategorieText, LocalDateTime ab, LocalDateTime bis) {
        frame = new JFrame("Dienstleistungsangebot bearbeiten");
        frame = ClientDefaults.standardizeFrame(frame, this.dienstleistungsangebotBearbeitenPanel);

        angebotLoeschenButton.setText("Angebot l" + Umlaut.oe() + "schen");

        try {
            Object[] info = Vereinssoftware.dienstleistungsverwaltung.getAngeboteInformationen(angebotsID);
            String abString = ab.format(DateTimeFormatter.ISO_LOCAL_DATE);

            LocalDateTime bisTime = ((LocalDateTime) info[4]);
            String bisString = bis.format(DateTimeFormatter.ISO_LOCAL_DATE);

            titleTextField.setText(titel);
            verfuegbarAbTextField.setText(abString);
            verfuegbarBisTextField.setText(bisString);
            urlTextField.setText(pathToImage);
            beschreibungTextArea.setText(beschreibung);
            kategorieComboBox.addItem(kategorieText);

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        //Dropdown mit Werten fuellen
        for (String kategorie :
                ClientDefaults.getKategorien()) {
            kategorieComboBox.addItem(kategorie);
        }


        angebotBearbeitenButton.addActionListener(e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            LocalDateTime abTime = LocalDate.parse(verfuegbarAbTextField.getText(), formatter).atTime(12, 0, 0);
            LocalDateTime bisTime = LocalDate.parse(verfuegbarBisTextField.getText(), formatter).atTime(12, 0, 0);

            angebotBearbeitenGUI(angebotsID, titleTextField.getText(), urlTextField.getText(), beschreibungTextArea.getText(), kategorieComboBox.getSelectedItem().toString(), abTime, bisTime);


        });

        angebotLoeschenButton.addActionListener(e -> {
            try {
                Vereinssoftware.dienstleistungsverwaltung.angebotLoeschen(angebotsID);
                new DefaultSmallPopup("Angebot geloescht", "Ihr Angebot mit dem Titel \"" + titel + "\" wurde erfolgreich geloescht!");
                frame.dispose();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    private void angebotBearbeitenGUI(String angebotsID, String title, String url, String beschreibung, String kategorie, LocalDateTime ab, LocalDateTime bis) {
        try {
            Vereinssoftware.dienstleistungsverwaltung.angebotAendern(angebotsID, Dienstleistungsangebotdaten.TITEL, title);
            Vereinssoftware.dienstleistungsverwaltung.angebotAendern(angebotsID, Dienstleistungsangebotdaten.URL, url);
            Vereinssoftware.dienstleistungsverwaltung.angebotAendern(angebotsID, Dienstleistungsangebotdaten.BESCHREIBUNG, beschreibung);
            Vereinssoftware.dienstleistungsverwaltung.angebotAendern(angebotsID, Dienstleistungsangebotdaten.KATEGORIE, kategorie);
            Vereinssoftware.dienstleistungsverwaltung.angebotAendern(angebotsID, Dienstleistungsangebotdaten.AB, ab);
            Vereinssoftware.dienstleistungsverwaltung.angebotAendern(angebotsID, Dienstleistungsangebotdaten.BIS, bis);

            frame.dispose();
            new DienstleistungsangebotsVerwaltungGUI();
            new DefaultSmallPopup("Erfolgreiche " + Umlaut.Ae() + "nderung", "Die Daten wurden erfolgreich in der Datenbank ge" + Umlaut.ae() + "ndert!");

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }

}
