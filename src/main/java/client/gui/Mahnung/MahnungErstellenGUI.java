package client.gui.Mahnung;

import client.ClientDefaults;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * GUI fuer das Erstellen von Mahnungen
 * <p>
 * Hauptautor
 *
 * @author Gia Huy Hans Tran
 * <p>
 * Kleine Verbesserungen
 * @author Dennis Kelm
 */
public class MahnungErstellenGUI {
    private JPanel Mahnungsverwaltung;
    private JButton jaButton;
    private JButton neinButton;

    //TODO funktionalitaet

    private static JFrame frame;

    public MahnungErstellenGUI() {
        frame = new JFrame("Mahnung erstellen");
        frame = ClientDefaults.standardizeFrame(frame, Mahnungsverwaltung);

        jaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        neinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

}
