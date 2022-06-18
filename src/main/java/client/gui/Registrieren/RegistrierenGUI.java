package client.gui.Registrieren;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * GUI fuer das Registrieren von Gaesten
 * <p>
 * Hauptautor
 *
 * @author Gia Huy Hans Tran
 * <p>
 * Kleine Verbesserungen
 * @author Dennis Kelm
 */
public class RegistrierenGUI {
    private JPanel Registrieren;
    private JTextField vornameTextField;
    private JTextField nachnameTextField;
    private JTextField anschriftTextField;
    private JTextField mitgliedsnummerTextField;
    private JTextField telefonnummerTextField;
    private JTextField eMailTextField;
    private JPasswordField passwortTextField;
    private JPasswordField passwortWiederholungTextField;
    private JButton registrierenButton;
    private JPanel vornamePanel;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    //TODO Hinweise hinzufuegen
    // funktionalitaet hinzufuegen
    // optimieren
    // Hinweise moeglicherweise anpassen
    // checkbox fuer Spender

    private static JFrame frame;

    private Map<JTextField, Boolean> onceChanged = new HashMap<>();

    public RegistrierenGUI() {
        frame = new JFrame("Registrieren");
        frame = ClientDefaults.standardizeFrame(frame, Registrieren);

        JTextField[] allTextFields = {
                vornameTextField,
                nachnameTextField,
                anschriftTextField,
                anschriftTextField,
                mitgliedsnummerTextField,
                eMailTextField,
                passwortTextField,
                passwortWiederholungTextField
        };

        for (JTextField textField : allTextFields)
            ClientDefaults.enhanceTextField(textField, onceChanged);

        //Prueft hier, ob 1. was eingegeben wurde und 2. ob noch was leer ist
        for (JTextField textField : allTextFields) {
            String eingabe = textField.getText();

            if (Objects.equals(eingabe, "Eingeben...") || Objects.equals(eingabe, "")) {
                new DefaultSmallPopup("Falsche Eingaben", "Du hast einen Fehler bei der Eingabe gemacht!");
                return;
            }

        }

        //reg (succ)
        registrierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!String.valueOf(passwortTextField.getPassword()).equals(String.valueOf(passwortWiederholungTextField.getPassword()))) {
                    new DefaultSmallPopup("Hinweis", "Die eingegebenen Passw" + Umlaut.oe() + "rter sind nicht identisch!");
                    return;
                }

                if (!telefonnummerTextField.getText().matches("[0-9]+")) {
                    new DefaultSmallPopup("Hinweis", "Die eingegebene Telefonnummer ist falsch!");
                    return;
                }

                if (!mitgliedsnummerTextField.getText().matches("[0-9]+")) {
                    new DefaultSmallPopup("Hinweis", "Die eingegebene Mitgliedsnummer ist falsch!");
                    return;
                }

                Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(eMailTextField.getText());
                if (!matcher.find()) {
                    new DefaultSmallPopup("Hinweis", "Die eingegebene E-Mail ist falsch!");
                    return;
                }

                if (Vereinssoftware.rollenverwaltung.existiertEMail(eMailTextField.getText())) {
                    new DefaultSmallPopup("Hinweis", "Die eingegebene E-Mail existiert bereits!");
                    return;
                }

                try {
                    Vereinssoftware.rollenverwaltung.gastHinzufuegen(nachnameTextField.getText(),
                            vornameTextField.getText(),
                            eMailTextField.getText(),
                            passwortTextField.getText(),
                            anschriftTextField.getText(),
                            mitgliedsnummerTextField.getText(),
                            telefonnummerTextField.getText(),
                            false);
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }

                new DefaultSmallPopup("Hinweis", "     Sie haben sich\n erfolgreich registriert.\n" +
                        "Schicken Sie noch Ihre Registrierungsdaten und Ihre Mitgliedsbescheinigung an mitarbeiter@vereinev.de");

                frame.dispose();

            }
        });

    }
}
