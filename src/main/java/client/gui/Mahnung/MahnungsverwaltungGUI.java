package client.gui.Mahnung;

import client.ClientDefaults;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI fuer das Verwalten von Mahnungen
 * <p>
 * Hauptautor
 *
 * @author Gia Huy Hans Tran
 * <p>
 * Kleine Verbesserungen
 * @author Dennis Kelm
 */
public class MahnungsverwaltungGUI {
    private JPanel MahnungVerwaltung;

    private JButton erstellenButton;
    private JTextField verfallsdatumTextField;
    private JTextField GrundfuerMahnung;

    //TODO funktionalitaet
    private static JFrame frame;

    public MahnungsverwaltungGUI() {
        frame = new JFrame("Mahnung erstellen");
        frame = ClientDefaults.standardizeFrame(frame, MahnungVerwaltung);

        erstellenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }
}
