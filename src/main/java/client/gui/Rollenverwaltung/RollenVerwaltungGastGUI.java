package client.gui.Rollenverwaltung;

import client.ClientDefaults;
import client.Umlaut;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI fuer die Rollenverwaltung der Gaeste
 * <p>
 * Hauptautor
 *
 * @author Gia Huy Hans Tran
 * <p>
 * Kleine Verbesserungen
 * @author Dennis Kelm
 */

public class RollenVerwaltungGastGUI {
    private JPanel RollenverwaltungGast;
    private JButton mitgliederButton;
    private JTable table1;
    private JButton mitarbeiterButton;
    private JButton vereinsvorstaendeButton;
    private JButton gaesteButton;

    public static JFrame frame;

    public RollenVerwaltungGastGUI() {
        frame = new JFrame("Rollenverwaltung - G" + Umlaut.ae() + "ste");
        frame = ClientDefaults.standardizeFrame(frame, RollenverwaltungGast);


        // geht auf Mitglied
        mitgliederButton.addActionListener(e -> {
            RollenverwaltungMitgliedGUI Mitglied = new RollenverwaltungMitgliedGUI();
            frame.dispose();
        });

        // geht auf Mitarbeiter
        mitarbeiterButton.addActionListener(e -> {
            RollenVerwaltungMitarbeiterGUI Mitarbeiter = new RollenVerwaltungMitarbeiterGUI();
            frame.dispose();
        });


        // geht auf Vereinsvorstand
        vereinsvorstaendeButton.addActionListener(e -> {
            RollenverwaltungVereinsvorstandGUI Vorstand = new RollenverwaltungVereinsvorstandGUI();
            frame.dispose();
        });

        createTable();


    }


    private void createTable() {
        String[][] data = {
                {"1", "2", "3", "4", "5", "6", "7"},
        };
        String[] columns = {"ID", "Name", "E-Mail", "Wohnadresse", "Mitgliedsnummer", "telefonnummer", "Rolle"};

        table1.setModel(new DefaultTableModel(
                data, columns)


        );

        JTable table = new JTable(data, columns);
    }
}
