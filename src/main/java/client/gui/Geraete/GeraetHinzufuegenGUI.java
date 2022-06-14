package client.gui.Geraete;

import client.ClientDefaults;
import client.Umlaut;
import client.Vereinssoftware;
import client.gui.DefaultSmallPopup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class GeraetHinzufuegenGUI {
    private JPanel Geraethinzufuegen;
    private JTextField geraetenameTextField;
    private JTextField spenderTextField;
    private JTextField kategorieTextField;
    private JTextField geraetebeschreibungTextField;
    private JTextField leihfristTextField;
    private JTextField abholortTextField;
    private JLabel Picture;
    private JButton geraetErstellenButton;


    public static JFrame frame;

    public GeraetHinzufuegenGUI(String title) {

        frame = new JFrame("Ger" + Umlaut.ae() + "t hinzuf" + Umlaut.ue() + "gen");
        frame = ClientDefaults.standardizeFrame(frame, Geraethinzufuegen);

        //TODO Geraet erstellen
        geraetErstellenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*Vereinssoftware.geraeteverwaltung.geraetHinzufuegen(
                        geraetenameTextField.getText(),
                        spenderTextField.getText(),
                        Integer.parseInt(leihfristTextField.getText()),
                        kategorieTextField.getText(),
                        geraetebeschreibungTextField.getText(),
                        abholortTextField.getText()); */ //TODO URL Textfeld

                DefaultSmallPopup defaultSmallPopup = new DefaultSmallPopup("Geraet erfolgreich hinzugefuegt", "Das Geraet wurde erfolgreich der Datenbank hinzugefuegt!");

                frame.dispose();


            }
        });
    }
}
