package client.gui.Registrieren;

import client.Vereinssoftware;
import client.gui.DefaultSmallPopup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class RegistrierenGUI extends JFrame {
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


    public RegistrierenGUI(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(Registrieren);
        this.pack();
        setLocationRelativeTo(null);


        //reg (succ)
        registrierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Vereinssoftware.rollenverwaltung.gastHinzufuegen(nachnameTextField.getText(),
                            vornameTextField.getName(),
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

                dispose();

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

    public static void main(String[] args) {
        JFrame frame = new RegistrierenGUI("Registrieren");
        frame.setVisible(true);
    }
}
