package client.gui.Rollenverwaltung;

import client.ClientDefaults;
import client.Vereinssoftware;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

/**
 * GUI fuer die Rollenverwaltung der Mitarbeiter
 * <p>
 * Hauptautor
 *
 * @author Gia Huy Hans Tran
 * <p>
 * Kleine Verbesserungen
 * @author Dennis Kelm
 */
public class RollenVerwaltungMitarbeiterGUI {
    private JPanel RollenVerwaltungMitarbeiter;
    private JTable table1;
    private JButton mitgliederButton;
    private JButton mitarbeiterButton;
    private JButton VereinsvorstaendeButton;
    private JButton gaesteButton;

    public static JFrame frame;

    public RollenVerwaltungMitarbeiterGUI() {

        try {
            createTable();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        frame = new JFrame("Rollenverwaltung - Mitarbeiter");
        frame = ClientDefaults.standardizeFrame(frame, RollenVerwaltungMitarbeiter);


        // geht auf Mitglied

        mitgliederButton.addActionListener(e -> {
            RollenverwaltungMitgliedGUI Mitglied = new RollenverwaltungMitgliedGUI();
            frame.dispose();
        });

        // geht auf Vereinsvorstand
        VereinsvorstaendeButton.addActionListener(e -> {
            RollenverwaltungVereinsvorstandGUI Vorstand = new RollenverwaltungVereinsvorstandGUI();
            frame.dispose();
        });
        // geht auf Gaeste
        gaesteButton.addActionListener(e -> {
            RollenVerwaltungGastGUI Gast = new RollenVerwaltungGastGUI();
            frame.dispose();
        });


    }


    private void createTable() throws RemoteException {
        Object[][] data = Vereinssoftware.rollenverwaltung.mitarbeiterDaten();


        String[] columns = {"ID", "Vorname", "Nachname", "E-Mail", "Anschrift", "MitgliedsNr", "TelefonNr", "Spenderstatus", "Stundenkonto", "Gesperrt?", "Mitglied seit"};

        table1.setModel(new DefaultTableModel(
                data, columns)


        );

        JTable table = new JTable(data, columns);
    }
}

