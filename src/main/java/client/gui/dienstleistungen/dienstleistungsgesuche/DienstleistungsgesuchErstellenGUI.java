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

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//GUI Funktionalitaet fuer das Erstellen von Dienstleistungsgesuchen
public class DienstleistungsgesuchErstellenGUI {
    private JPanel dienstleistungsgesuchErstellenPanel;
    private JFormattedTextField titleTextField;
    private JTextArea beschreibungTextArea;
    private JComboBox kategorieComboBox;
    private JButton gesuchErstellenButton;
    private JPanel kategoriePanel;
    private JPanel beschreibungPanel;
    private JPanel titlePanel;
    private JFormattedTextField urlTextField;
    private JPanel urlPanel;

    private final Map<JTextField, Boolean> onceChangedFields = new HashMap<JTextField, Boolean>();
    private final Map<JTextArea, Boolean> onceChangedAreas = new HashMap<JTextArea, Boolean>();

    private static JFrame frame;

    public DienstleistungsgesuchErstellenGUI() {
        frame = new JFrame("Dienstleistungsgesuch erstellen");
        frame = ClientDefaults.standardizeFrame(frame, this.dienstleistungsgesuchErstellenPanel);
        JTextField[] allTextFields = new JTextField[]{
                titleTextField,
                urlTextField
        };


        for (JTextField textField :
                allTextFields) {
            ClientDefaults.enhanceTextField(textField, onceChangedFields);
        }

        ClientDefaults.enhanceTextArea(beschreibungTextArea, onceChangedAreas);

        for (String kategorie :
                ClientDefaults.getKategorien()) {
            kategorieComboBox.addItem(kategorie);
        }

        gesuchErstellenButton.addActionListener(e -> {
            gesuchErstellenGUI(
                    titleTextField.getText(),
                    beschreibungTextArea.getText(),
                    Objects.requireNonNull(kategorieComboBox.getSelectedItem()).toString(),
                    urlTextField.getText());
        });

    }

    private void gesuchErstellenGUI(String titel, String beschreibung, String kategorie, String urlToImage) {
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
                Vereinssoftware.dienstleistungsverwaltung.gesuchErstellen(titel, beschreibung, kategorie, urlToImage, Vereinssoftware.session.getID());
                new DienstleistungsgesucheGUI();
                DefaultSmallPopup smallPopup = new DefaultSmallPopup("Gesuch erfolgreich erstellt", "Ihr Dienstleistungsgesuch wurde erfolgreich erstellt!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            DefaultSmallPopup defaultSmallPopup = new DefaultSmallPopup("Falsche URL", "Die Bild-URL, die du eingegeben hast, ist falsch!");
        }

    }
}



