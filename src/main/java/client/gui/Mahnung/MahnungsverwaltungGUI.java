package client.gui.Mahnung;

import client.ClientDefaults;
import client.Vereinssoftware;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        ActionListener buttonAktivierenListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(GrundfuerMahnungTextfield.getText() != null && verfallsdatumTextField.getText() != null){
                    erstellenButton.setEnabled(true);
                }
            }
        };

        verfallsdatumTextField.addActionListener(buttonAktivierenListener);
        GrundfuerMahnungTextfield.addActionListener(buttonAktivierenListener);

        //Mahnung auf Knopfdruck erstellen
        erstellenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == erstellenButton) {
                    try {
                        Vereinssoftware.rollenverwaltung.mahnungErstellen(mitgliedsID, GrundfuerMahnungTextfield.getText(), LocalDateTime.parse(verfallsdatumTextField.getText()));
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }
                }

            }
        });

    }
}
