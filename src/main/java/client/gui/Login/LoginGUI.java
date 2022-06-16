package client.gui.Login;

import client.ClientDefaults;
import client.Umlaut;
import client.Vereinssoftware;
import client.gui.DefaultSmallPopup;
import client.gui.Registrieren.RegistrierenGUI;
import server.users.Rolle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI fuer Login
 * <p>
 * Hauptautor
 *
 * @author Gia Huy Hans Tran
 * <p>
 * Kleine Verbesserungen
 * @author Dennis Kelm
 */

public class LoginGUI {
    private JPanel Login;
    private JTextField eMailAdresseTextField;
    private JTextField passwortTextField;
    private JButton loginButton;
    private JButton registrierenButton;


    //TODO error msg hinzufuegen falls login falsch (via DefaultSmallPopup)
    // funktionalitaet hinzufuegen
    // optimieren
    // pw textfield hinzufuegen

    private static JFrame frame;

    public LoginGUI() {
        frame = new JFrame("Login");
        frame = ClientDefaults.standardizeFrame(frame, Login);

        // Login Button (error)
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Object[] loginData = Vereinssoftware.rollenverwaltung.login(eMailAdresseTextField.getText(), passwortTextField.getText());

                    //Daten auslesen und in Session speichern
                    String userId = loginData[0].toString();
                    Rolle rolle = Rolle.valueOf(loginData[1].toString());

                    Vereinssoftware.session.setID(userId);
                    Vereinssoftware.session.setRolle(rolle);

                    Vereinssoftware.session.setMitgliedsName(Vereinssoftware.rollenverwaltung.getMitgliedsNamen(userId));

                    //Daten anzeigen
                    Vereinssoftware.getStartseite().updateProfilButtons();
                } catch (Exception ex) {
                    DefaultSmallPopup smallPopup = new DefaultSmallPopup("Hinweis", "E-Mail-Adresse und/oder Passwort sind falsch, Fehler " + ex);
                }

                frame.dispose();

            }
        });

        // Login Button (succ)


       /* loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        */


        // Registrieren Button
        registrierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrierenGUI registrierenGUI = new RegistrierenGUI();
                frame.setVisible(false);
            }


        });


    }


}
