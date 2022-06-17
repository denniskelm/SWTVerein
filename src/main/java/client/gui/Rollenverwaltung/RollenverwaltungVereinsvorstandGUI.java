package client.gui.Rollenverwaltung;

import client.ClientDefaults;
import client.Vereinssoftware;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

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


    private void createTable() throws RemoteException {
        Object[][] data = Vereinssoftware.rollenverwaltung.vorsitzDaten();


        String[] columns = {"ID", "Vorname", "Nachname", "E-Mail", "Anschrift", "MitgliedsNr", "TelefonNr", "Spenderstatus", "Stundenkonto", "Gesperrt?", "Mitglied seit"};

        table1.setModel(new DefaultTableModel(
                data, columns)


        );

        JTable table = new JTable(data, columns);
    }
}
