package client.gui.Rollenverwaltung;

import client.ClientDefaults;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI fuer die Rollenverwaltung der Vereinsvorstaende
 * <p>
 * Hauptautor
 *
 * @author Gia Huy Hans Tran
 * <p>
 * Kleine Verbesserungen
 * @author Dennis Kelm
 */
public class RollenverwaltungVereinsvorstandGUI {
    private JPanel RollenVerwaltungVereinsVorstand;
    private JButton mitgliedButton;
    private JButton mitarbeiterButton;
    private JButton vereinsvorstaendeButton;
    private JButton gaesteButton;
    private JTable table1;

    private static JFrame frame;

    public RollenverwaltungVereinsvorstandGUI() {

        frame = new JFrame("Rollenverwaltung - Vereinsvorstand");
        frame = ClientDefaults.standardizeFrame(frame, RollenVerwaltungVereinsVorstand);


        // geht auf Mitglied
        mitgliedButton.addActionListener(e -> {
            RollenverwaltungMitgliedGUI Mitglied = new RollenverwaltungMitgliedGUI();
            frame.dispose();
        });
        // geht auf Mitarbeiter

        mitarbeiterButton.addActionListener(e -> {
            RollenVerwaltungMitarbeiterGUI Mitarbeiter = new RollenVerwaltungMitarbeiterGUI();
            frame.dispose();
        });


        // geht auf Gaeste
        gaesteButton.addActionListener(e -> {
            RollenVerwaltungGastGUI Gast = new RollenVerwaltungGastGUI();
            frame.dispose();
        });
    }


    private void creatTable() {
        String[][] data = {
                {"1", "2", "3", "4", "5", "6", "7"},
        };

        table1.setModel(new DefaultTableModel(
                data,
                new String[]{"ID", "Name", "E-Mail", "Wohnadresse", "Mitgliedsnummer", "istSpender", "telefonnummer", "Studenkonto"}

        ));

    }
}
