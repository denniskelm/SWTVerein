package client.gui.Geraete;

import client.ClientDefaults;
import client.Umlaut;
import client.Vereinssoftware;
import client.gui.DefaultSmallPopup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GeraetHinzufuegenGUI {
    private final Map<JTextField, Boolean> onceChangedFields = new HashMap<>();
    private JPanel Geraethinzufuegen;
    private JTextField geraetenameTextField;
    private JTextField spenderTextField;
    private JTextField geraetebeschreibungTextField;
    private JTextField leihfristTextField;
    private JTextField abholortTextField;
    private JButton geraetErstellenButton;
    private JComboBox kategorieComboBox;
    private JTextField imageTextField;

    //TODO TEXTAREA

    private static JFrame frame;

    public GeraetHinzufuegenGUI() {

        frame = new JFrame("Ger" + Umlaut.ae() + "t hinzuf" + Umlaut.ue() + "gen");
        frame = ClientDefaults.standardizeFrame(frame, Geraethinzufuegen);

        JTextField[] allTextFields = new JTextField[]{
                geraetenameTextField,
                spenderTextField,
                leihfristTextField,
                abholortTextField,
                imageTextField,
                geraetebeschreibungTextField
        };

        for (JTextField textField :
                allTextFields) {
            ClientDefaults.enhanceTextField(textField, onceChangedFields);
        }

        for (String kategorie :
                ClientDefaults.getKategorien()) {
            kategorieComboBox.addItem(kategorie);
        }


        geraetErstellenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Vereinssoftware.geraeteverwaltung.geraetHinzufuegen(
                            geraetenameTextField.getText(),
                            spenderTextField.getText(),
                            Integer.parseInt(leihfristTextField.getText()),
                            Objects.requireNonNull(kategorieComboBox.getSelectedItem()).toString(),
                            geraetebeschreibungTextField.getText(),
                            abholortTextField.getText(),
                            imageTextField.getText());
                } catch (RemoteException ex) {
                    new DefaultSmallPopup("Geraet nicht hinzugef" + Umlaut.ue() + "gt",
                            "Es ist beim Erstellen des Ge" + Umlaut.ae() + "ts ein Fehler aufgetreten: " + ex);
                    throw new RuntimeException(ex);

                }

                DefaultSmallPopup defaultSmallPopup = new DefaultSmallPopup("Geraet erfolgreich hinzugefuegt", "Das Geraet wurde erfolgreich der Datenbank hinzugefuegt!");

                frame.dispose();
            }
        });
    }

}
