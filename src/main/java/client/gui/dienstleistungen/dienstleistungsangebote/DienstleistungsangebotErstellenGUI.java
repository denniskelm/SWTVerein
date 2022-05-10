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
import java.util.HashMap;
import java.util.Map;

//GUI Funktionalität für das Erstellen von Dienstleistungsangeboten
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

    private Map<JTextField, Boolean> onceChangedFields = new HashMap<JTextField, Boolean>();
    private Map<JTextArea, Boolean> onceChangedAreas = new HashMap<JTextArea, Boolean>();

    public DienstleistungsangebotErstellenGUI() {
        JFrame frame = new JFrame("Dienstleistungsangebot erstellen");
        frame = DefaultsClient.standardizeFrame(frame, this.dienstleistungsangebotErstellenPanel);
        JTextField[] allTextFields = new JTextField[]{
                titleTextField,
                verfuegbarAbTextField,
                verfuegbarBisTextField,
                urlTextField
        };


        for (JTextField textField :
                allTextFields) {
            DefaultsClient.enhanceTextField(textField, onceChangedFields);
        }

        DefaultsClient.enhanceTextArea(beschreibungTextArea, onceChangedAreas);

        angebotErstellenButton.addActionListener(e -> {
            angebotErstellenGUI();
        });

    }

    public static void main(String[] args) {
        DienstleistungsangebotErstellenGUI thisgui = new DienstleistungsangebotErstellenGUI();
    }

    private void angebotErstellenGUI() {
        DefaultSmallPopup smallPopup = new DefaultSmallPopup("Angebot erfolgreich erstellt", "Ihr Dienstleistungsangebot wurde erfolgreich erstellt!");
    }

}
