package client.gui.Geraete;

import client.ClientDefaults;
import client.Umlaut;
import client.Vereinssoftware;
import client.gui.DefaultSmallPopup;
import com.mysql.cj.xdevapi.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GeraetHinzufuegenGUI {
    private final Map<JTextField, Boolean> onceChangedFields = new HashMap<>();
    private final Map<JTextArea, Boolean> onceChangedAreas = new HashMap<>();
    private JPanel Geraethinzufuegen;
    private JTextField geraetenameTextField;
    private JTextField spenderTextField;
    private JTextArea geraetebeschreibungTextArea;
    private JTextField leihfristTextField;
    private JTextField abholortTextField;
    private JButton geraetErstellenButton;
    private JComboBox kategorieComboBox;
    private JTextField imageTextField;

    private static JFrame frame;

    public GeraetHinzufuegenGUI() {

        frame = new JFrame("Ger" + Umlaut.ae() + "t hinzuf" + Umlaut.ue() + "gen");
        frame = ClientDefaults.standardizeFrame(frame, Geraethinzufuegen);

        JTextField[] allTextFields = new JTextField[]{
                geraetenameTextField,
                spenderTextField,
                leihfristTextField,
                abholortTextField,
                imageTextField
        };

        for (JTextField textField :
                allTextFields) {
            ClientDefaults.enhanceTextField(textField, onceChangedFields);
        }
        ClientDefaults.enhanceTextArea(geraetebeschreibungTextArea, onceChangedAreas);

        for (String kategorie :
                ClientDefaults.getKategorien()) {
            kategorieComboBox.addItem(kategorie);
        }


        geraetErstellenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean allFieldsValid = true;

                //Prueft hier, ob 1. was eingegeben wurde und 2. ob noch was leer ist
                for (String eingabe :
                        new String[]{geraetenameTextField.getText(),
                                spenderTextField.getText(),
                                geraetebeschreibungTextArea.getText(),
                                abholortTextField.getText(),
                                imageTextField.getText()}) {
                    if (Objects.equals(eingabe, "Eingeben...") || Objects.equals(eingabe, "")) {
                        allFieldsValid = false;
                        DefaultSmallPopup defaultSmallPopup = new DefaultSmallPopup("Falsche Eingaben", "Du hast einen Fehler bei der Eingabe gemacht!");
                    }
                }

                //Prueft gleich noch, ob die URL wirklich eine URL ist
                if (allFieldsValid && ClientDefaults.checkIfValidURL(imageTextField.getText())) {
                    try {
                        Vereinssoftware.geraeteverwaltung.geraetHinzufuegen(
                                geraetenameTextField.getText(),
                                spenderTextField.getText(),
                                Integer.parseInt(leihfristTextField.getText()),
                                Objects.requireNonNull(kategorieComboBox.getSelectedItem()).toString(),
                                geraetebeschreibungTextArea.getText(),
                                abholortTextField.getText(),
                                imageTextField.getText());
                        new DefaultSmallPopup("Geraet erfolgreich hinzugefuegt", "Das Geraet wurde erfolgreich der Datenbank hinzugefuegt!");
                        frame.dispose();
                    } catch (Exception ex) {
                        new DefaultSmallPopup("Geraet nicht hinzugef" + Umlaut.ue() + "gt",
                                "Es ist beim Erstellen des Ge" + Umlaut.ae() + "ts ein Fehler aufgetreten: " + ex);
                        throw new RuntimeException(ex);
                    }
                } else {
                    DefaultSmallPopup defaultSmallPopup = new DefaultSmallPopup("Falsche URL", "Die Bild-URL, die du eingegeben hast, ist falsch!");
                }
            }
        });
    }

}
