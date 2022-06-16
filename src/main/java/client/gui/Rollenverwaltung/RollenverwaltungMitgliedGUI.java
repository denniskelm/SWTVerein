package client.gui.Rollenverwaltung;

import client.ClientDefaults;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI fuer die Rollenverwaltung der Mitglieder
 * <p>
 * Hauptautor
 *
 * @author Gia Huy Hans Tran
 * <p>
 * Kleine Verbesserungen
 * @author Dennis Kelm
 */
public class RollenverwaltungMitgliedGUI {
    private JPanel RollenVerwaltungMitglied;
    private JTable table1;
    private JButton mitgliedButton;
    private JButton mitarbeiterButton;
    private JButton vereinsvorstandButton;
    private JButton gaesteButton;
    private static JFrame frame;

    public RollenverwaltungMitgliedGUI() {

        frame = new JFrame("Rollenverwaltung - Mitglied");
        frame = ClientDefaults.standardizeFrame(frame, RollenVerwaltungMitglied);

        // geht auf Mitglied

        mitarbeiterButton.addActionListener(e -> {
            RollenVerwaltungMitarbeiterGUI Mitarbeiter = new RollenVerwaltungMitarbeiterGUI();
            frame.dispose();
        });

        // geht auf Vereinsvorstand
        vereinsvorstandButton.addActionListener(e -> {
            RollenverwaltungVereinsvorstandGUI Vorstand = new RollenverwaltungVereinsvorstandGUI();
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
                {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
        };
        String[] columns = {"ID", "Name", "E-Mail", "Wohnadresse", "Mitgliedsnummer", "telefonnummer", "istSpender", "StundenKonto", "Rolle", "Mahnung"};

        table1.setModel(new DefaultTableModel(
                data, columns)


        );

        JTable table = new JTable(data, columns);
    }
}





