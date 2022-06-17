package client.gui.Registrieren;

import client.ClientDefaults;
import client.Vereinssoftware;
import client.gui.DefaultSmallPopup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

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
    private JTextField passwortTextField;
    private JTextField passwortWiederholungTextField;
    private JButton registrierenButton;

    //TODO Hinweise hinzufuegen
    // funktionalitaet hinzufuegen
    // optimieren
    // Hinweise moeglicherweise anpassen
    // checkbox fuer Spender

    private static JFrame frame;

    public RegistrierenGUI() {
        frame = new JFrame("Registrieren");
        frame = ClientDefaults.standardizeFrame(frame, Registrieren);


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
