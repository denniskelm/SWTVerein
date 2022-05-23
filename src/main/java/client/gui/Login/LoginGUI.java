package client.gui.Login;

import client.Vereinssoftware;
import client.gui.DefaultSmallPopup;
import client.gui.Registrieren.RegistrierenGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    private JPanel Login;
    private JTextField eMailAdresseTextField;
    private JTextField passwortTextField;
    private JButton loginButton;
    private JButton registrierenButton;


    //TODO error msg hinzufuegen falls login falsch (via DefaultSmallPopup)
    // funktionalität hinzufügen
    // optimieren
    // pw textfield hinzufügen


    public LoginGUI(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(Login);
        this.pack();
        setLocationRelativeTo(null);

        // Login Button (error)
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Vereinssoftware.rollenverwaltung.login(eMailAdresseTextField.getText(), passwortTextField.getText());
                } catch (Exception ex) {
                    DefaultSmallPopup smallPopup = new DefaultSmallPopup("Hinweis", "E-Mail-Adresse und/oder Passwort sind falsch");
                }

                dispose();

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
                RegistrierenGUI registrierenGUI = new RegistrierenGUI("Registrieren");
                registrierenGUI.setVisible(true);
                LoginGUI.this.setVisible(false);
            }


        });


    }


    // zum Testen
    public static void main(String[] args) {
        JFrame frame = new LoginGUI("Registrieren");
        frame.setVisible(true);
    }
}
