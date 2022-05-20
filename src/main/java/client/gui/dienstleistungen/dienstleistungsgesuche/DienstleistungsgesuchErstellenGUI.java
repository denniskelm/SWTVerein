package client.gui.dienstleistungen.dienstleistungsgesuche;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//GUI Funktionalität für das Erstellen von Dienstleistungsgesuchen
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

    private Map<JTextField, Boolean> onceChangedFields = new HashMap<JTextField, Boolean>();
    private Map<JTextArea, Boolean> onceChangedAreas = new HashMap<JTextArea, Boolean>();

    public DienstleistungsgesuchErstellenGUI() {
        JFrame frame = new JFrame("Dienstleistungsgesuch erstellen");
        frame = ClientDefaults.standardizeFrame(frame, this.dienstleistungsgesuchErstellenPanel);
        JTextField[] allTextFields = new JTextField[]{
                titleTextField,
                urlTextField
        };

        frame.setLocationRelativeTo(null);

        for (JTextField textField :
                allTextFields) {
            ClientDefaults.enhanceTextField(textField, onceChangedFields);
        }

        ClientDefaults.enhanceTextArea(beschreibungTextArea, onceChangedAreas);

        for (String kategorie :
                ClientDefaults.getKategorien(Kategorie.class)) {
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
        try {
            Vereinssoftware.dienstleistungsverwaltung.gesuchErstellen(titel, beschreibung, kategorie, urlToImage, Vereinssoftware.session.getID());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        DefaultSmallPopup smallPopup = new DefaultSmallPopup("Gesuch erfolgreich erstellt", "Ihr Dienstleistungsgesuch wurde erfolgreich erstellt!");
    }
}



