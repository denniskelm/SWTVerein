package client.gui.Mahnung;

import client.ClientDefaults;
import client.Vereinssoftware;
import client.gui.DefaultSmallPopup;
import client.gui.Rollenverwaltung.RollenverwaltungMitgliedGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * GUI fuer das Verwalten von Mahnungen
 * <p>
 * Hauptautor
 *
 * @author Gia Huy Hans Tran
 * @author Gabriel Kleebaum
 * @author Ole Adelmann
 * <p>
 * Kleine Verbesserungen
 * @author Dennis Kelm
 */
public class MahnungsverwaltungGUI {
    private JPanel MahnungVerwaltung;

    private JButton erstellenButton;
    private JTextField verfallsdatumTextField;
    private JTextField GrundfuerMahnungTextfield;
    private JLabel mahnungenAmountText;

    private static JFrame frame;


    public MahnungsverwaltungGUI(String mitgliedsID) {
        frame = new JFrame("Mahnung erstellen");
        frame = ClientDefaults.standardizeFrame(frame, MahnungVerwaltung);

        // Anzahl der Mahnungen anzeigen

        try {
            mahnungenAmountText.setText(Vereinssoftware.rollenverwaltung.anzahlMahnungenVonNutzer(mitgliedsID) + " Mahnung/en");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }


        //Button erst aktivieren, wenn Grund und Datum eingegeben
        erstellenButton.setEnabled(false);

        KeyListener buttonAktivierenListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (GrundfuerMahnungTextfield.getText().length() > 0 && verfallsdatumTextField.getText().length() > 0)
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
                    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
                    LocalDateTime verfallsdatum = LocalDate.parse(verfallsdatumTextField.getText(), formatter).atTime(12, 0, 0);
                    Vereinssoftware.rollenverwaltung.mahnungErstellen(mitgliedsID, GrundfuerMahnungTextfield.getText(), verfallsdatum);
                    frame.dispose();
                    new RollenverwaltungMitgliedGUI();
                    new DefaultSmallPopup("Mahnung erstellt", "Mahnung für das Mitglied " + Vereinssoftware.rollenverwaltung.getMitgliedsNamen(mitgliedsID) + " erfolgreich erstellt!");
                } catch (Exception ex) {
                    new DefaultSmallPopup("Mahnung: Fehler", "Mahnung nicht erstellt wegen des Fehlers " + ex.getMessage());
                    throw new RuntimeException(ex);
                }
            }

        });

    }
}
