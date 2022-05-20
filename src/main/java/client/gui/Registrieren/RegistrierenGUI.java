package client.gui.Registrieren;

import client.gui.DefaultSmallPopup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class RegistrierenGUI extends JFrame{
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

    //TODO Hinweise hinzufügen
    // funktionalität hinzufügen
    // optimieren
    // Hinweise möglicherweise anpassen





    public RegistrierenGUI(String title)    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Registrieren);
        this.pack();


        //reg (succ)
        registrierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                    DefaultSmallPopup smallPopup = new DefaultSmallPopup("Hinweis", "     Sie haben sich\n" +
                            "erfolgreich registriert.\n" +
                            "Schicken Sie noch Ihre Registrierungsdaten und Ihre Mitgliedsbescheinigung an mitarbeiter@vereinev.de");

            }
         });


        /*

        //reg pw nicht überein

        registrierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultSmallPopup smallPopup = new DefaultSmallPopup("Hinweis", "Eingegebene Passwörter sind nicht identisch");
            }
        });

        //reg * markierung nicht ausgeflüllt

        registrierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultSmallPopup smallPopup = new DefaultSmallPopup("Hinweis", "Bitte füllen Sie alle mit einem '*' markierten Felder aus.");
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
                DefaultSmallPopup smallPopup = new DefaultSmallPopup("Hinweis", "Diese E-Mail-Adresse ist ungültig");
            }
        });

        //reg telenummer invalid

        registrierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultSmallPopup smallPopup = new DefaultSmallPopup("Hinweis", "Telfennummer ist ungültig");
            }
        });

        //reg Mitgliedsnummer invalid

        registrierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultSmallPopup smallPopup = new DefaultSmallPopup("Hinweis", "Mitgliedsnummer ist ungültig");
            }
        });


         */
    }

    public static void main(String[] args) {
        JFrame frame = new RegistrierenGUI("Registrieren");
        frame.setVisible(true);
    }
}
