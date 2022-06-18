package client.gui.Rollenverwaltung;

import client.ClientDefaults;
import client.Umlaut;
import client.Vereinssoftware;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI das Aendern der Rolle eines Nutzers
 * <p>
 * Hauptautor
 *
 * @author Gia Huy Hans Tran
 * <p>
 * Kleine Verbesserungen
 * @author Dennis Kelm
 */
public class RolleAuswaehlenGUI {
    private JPanel RolleAuswaehlen;
    private JButton OKButton;
    private JButton abbrechenButton;
    private JRadioButton MitgliedButton;
    private JRadioButton MitarbeiterButton;
    private JRadioButton VereinsvorstandButton;
    private JRadioButton LoeschenButton;

    public static JFrame frame;

    public RolleAuswaehlenGUI(String mitgliedsID) {

        frame = new JFrame("Rolle " + Umlaut.ae() + "ndern");
        frame = ClientDefaults.standardizeFrame(frame, RolleAuswaehlen);




        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if()
                Vereinssoftware.rollenverwaltung.rolleAendern(mitgliedsID, );

            }
        });

        abbrechenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
