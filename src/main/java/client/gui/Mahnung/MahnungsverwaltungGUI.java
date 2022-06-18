package client.gui.Mahnung;

import client.ClientDefaults;
import client.Vereinssoftware;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;
import java.time.LocalDateTime;

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
    private JTextField GrundfuerMahnungTextfield;

    private static JFrame frame;


    public MahnungsverwaltungGUI(String mitgliedsID) {
        frame = new JFrame("Mahnung erstellen");
        frame = ClientDefaults.standardizeFrame(frame, MahnungVerwaltung);


        //Button erst aktivieren, wenn Grund und Datum eingegeben
        erstellenButton.setEnabled(false);

        KeyListener buttonAktivierenListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(GrundfuerMahnungTextfield.getText().length() > 0 && verfallsdatumTextField.getText().length() > 0)
                    erstellenButton.setEnabled(true);
                else
                    erstellenButton.setEnabled(false);
            }

            @Override
            public void keyPressed(KeyEvent e) { }

            @Override
            public void keyReleased(KeyEvent e) { }
        };

        verfallsdatumTextField.addKeyListener(buttonAktivierenListener);
        GrundfuerMahnungTextfield.addKeyListener(buttonAktivierenListener);

        //Mahnung auf Knopfdruck erstellen
        erstellenButton.addActionListener(e -> {
            if(e.getSource() == erstellenButton) {
                try {
                    Vereinssoftware.rollenverwaltung.mahnungErstellen(mitgliedsID, GrundfuerMahnungTextfield.getText(), LocalDateTime.parse(verfallsdatumTextField.getText()));
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });

    }
}
