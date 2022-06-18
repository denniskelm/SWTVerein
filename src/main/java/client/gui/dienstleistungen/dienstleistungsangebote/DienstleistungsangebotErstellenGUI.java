package client.gui.dienstleistungen.dienstleistungsangebote;
/*
@author
Dennis Kelm
*/

import client.ClientDefaults;
import client.Kategorie;
import client.Vereinssoftware;
import client.gui.DefaultSmallPopup;

import javax.swing.*;
import java.lang.ref.Cleaner;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//GUI Funktionalitaet fuer das Erstellen von Dienstleistungsangeboten
public class DienstleistungsangebotErstellenGUI {
    private JPanel dienstleistungsangebotErstellenPanel;
    private JFormattedTextField titleTextField;
    private JTextArea beschreibungTextArea;
    private JComboBox kategorieComboBox;
    private JFormattedTextField verfuegbarAbTextField;
    private JFormattedTextField verfuegbarBisTextField;
    private JButton angebotErstellenButton;
    private JPanel verfuegbarPanel;
    private JPanel kategoriePanel;
    private JPanel beschreibungPanel;
    private JPanel titlePanel;
    private JPanel verfuegbarAbPanel;
    private JPanel verfuegbarBisPanel;
    private JFormattedTextField urlTextField;
    private JPanel urlPanel;

    private static JFrame frame;

    private final Map<JTextField, Boolean> onceChangedFields = new HashMap<JTextField, Boolean>();
    private final Map<JTextArea, Boolean> onceChangedAreas = new HashMap<JTextArea, Boolean>();

    public DienstleistungsangebotErstellenGUI() {
        frame = new JFrame("Dienstleistungsangebot erstellen");
        frame = ClientDefaults.standardizeFrame(frame, this.dienstleistungsangebotErstellenPanel);
        JTextField[] allTextFields = new JTextField[]{
                titleTextField,
                verfuegbarAbTextField,
                verfuegbarBisTextField,
                urlTextField
        };

        for (JTextField textField :
                allTextFields) {
            ClientDefaults.enhanceTextField(textField, onceChangedFields);
        }

        for (String kategorie :
                ClientDefaults.getKategorien()) {
            kategorieComboBox.addItem(kategorie);
        }

        ClientDefaults.enhanceTextArea(beschreibungTextArea, onceChangedAreas);

        angebotErstellenButton.addActionListener(e -> {
            angebotErstellenGUI(
                    titleTextField.getText(),
                    urlTextField.getText(),
                    beschreibungTextArea.getText(),
                    Objects.requireNonNull(kategorieComboBox.getSelectedItem()).toString(),
                    verfuegbarAbTextField.getText(),
                    verfuegbarBisTextField.getText());
        });

    }

    private void angebotErstellenGUI(String titel, String urlToImage, String beschreibung, String kategorie, String ab, String bis) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDateTime abTime = LocalDate.parse(ab, formatter).atTime(12, 0, 0);
        LocalDateTime bisTime = LocalDate.parse(bis, formatter).atTime(12, 0, 0);

        boolean allFieldsValid = true;

        //Prueft hier, ob 1. was eingegeben wurde und 2. ob noch was leer ist
        for (String eingabe :
                new String[]{titel, beschreibung, kategorie, urlToImage}) {
            if (Objects.equals(eingabe, "Eingeben...") || Objects.equals(eingabe, "")) {
                allFieldsValid = false;
                DefaultSmallPopup defaultSmallPopup = new DefaultSmallPopup("Falsche Eingaben", "Du hast einen Fehler bei der Eingabe gemacht!");
            }
        }

        //Prueft gleich noch, ob die URL wirklich eine URL ist
        if (allFieldsValid && ClientDefaults.checkIfValidURL(urlToImage)) {
            try {
                frame.dispose();
                Vereinssoftware.dienstleistungsverwaltung.angebotErstellen(titel, beschreibung, kategorie, abTime, bisTime, urlToImage, Vereinssoftware.session.getID());
                new DienstleistungsangeboteGUI();
                DefaultSmallPopup smallPopup = new DefaultSmallPopup("Angebot erfolgreich erstellt", "Ihr Dienstleistungsangebot wurde erfolgreich erstellt!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            DefaultSmallPopup defaultSmallPopup = new DefaultSmallPopup("Falsche URL", "Die Bild-URL, die du eingegeben hast, ist falsch!");
        }


    }

}
