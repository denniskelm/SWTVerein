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

import client.DefaultsClient;
import client.gui.DefaultSmallPopup;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

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
        frame = DefaultsClient.standardizeFrame(frame, this.dienstleistungsgesuchErstellenPanel);
        JTextField[] allTextFields = new JTextField[]{
                titleTextField,
                urlTextField
        };

        for (JTextField textField :
                allTextFields) {
            DefaultsClient.enhanceTextField(textField, onceChangedFields);
        }

        DefaultsClient.enhanceTextArea(beschreibungTextArea, onceChangedAreas);

        gesuchErstellenButton.addActionListener(e -> {
            gesuchErstellenGUI();
        });

    }

    public static void main(String[] args) {
        DienstleistungsgesuchErstellenGUI thisgui = new DienstleistungsgesuchErstellenGUI();
    }

    private void gesuchErstellenGUI() {
        //TODO Implementierung gesuchErstellen
        DefaultSmallPopup smallPopup = new DefaultSmallPopup("Gesuch erfolgreich erstellt", "Ihr Dienstleistungsgesuch wurde erfolgreich erstellt!");
    }


}
