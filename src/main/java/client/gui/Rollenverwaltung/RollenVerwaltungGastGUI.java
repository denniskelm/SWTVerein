package client.gui.Rollenverwaltung;

import client.ClientDefaults;
import client.Umlaut;
import client.Vereinssoftware;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

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

        try {
            createTable();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }


    }


    private void createTable() throws RemoteException {
        Object[][] data = Vereinssoftware.rollenverwaltung.gaesteDaten();


        String[] columns = {"ID", "Vorname", "Nachname", "E-Mail", "Anschrift", "MitgliedsNr", "TelefonNr"};

        table1.setModel(new DefaultTableModel(
                data, columns)


        );

        JTable table = new JTable(data, columns);
    }
}
