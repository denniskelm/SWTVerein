package client.gui.Registrieren;

import client.ClientDefaults;
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

                DefaultSmallPopup smallPopup = new DefaultSmallPopup("Hinweis", "     Sie haben sich\n" +
                        "erfolgreich registriert.\n" +
                        "Schicken Sie noch Ihre Registrierungsdaten und Ihre Mitgliedsbescheinigung an mitarbeiter@vereinev.de");

                frame.dispose();

            }
        });


        /*

        //reg pw nicht ueberein

        registrierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultSmallPopup smallPopup = new DefaultSmallPopup("Hinweis", "Eingegebene Passwoerter sind nicht identisch");
            }
        });

        //reg * markierung nicht ausgefluellt

        registrierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultSmallPopup smallPopup = new DefaultSmallPopup("Hinweis", "Bitte fuellen Sie alle mit einem '*' markierten Felder aus.");
            }
        });

        //reg Email exists

        registrierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultSmallPopup smallPopup = new DefaultSmallPopup("Hinweis", "Diese E-Mail-Adresse existiert bereits");
            }
        });

        //reg email invalid

        registrierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultSmallPopup smallPopup = new DefaultSmallPopup("Hinweis", "Diese E-Mail-Adresse ist ungueltig");
            }
        });

        //reg telenummer invalid

        registrierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultSmallPopup smallPopup = new DefaultSmallPopup("Hinweis", "Telfennummer ist ungueltig");
            }
        });

        //reg Mitgliedsnummer invalid

        registrierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultSmallPopup smallPopup = new DefaultSmallPopup("Hinweis", "Mitgliedsnummer ist ungueltig");
            }
        });


         */
    }
}
