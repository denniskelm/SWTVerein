package client.gui.Rollenverwaltung;

import client.ClientDefaults;
import client.Vereinssoftware;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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

        try {
            createTable();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        frame = new JFrame("Rollenverwaltung - Vereinsvorstand");
        frame = ClientDefaults.standardizeFrame(frame, RollenVerwaltungVereinsVorstand);


        // geht auf Mitglied
        mitgliedButton.addActionListener(e -> {
            new RollenverwaltungMitgliedGUI();
            frame.dispose();
        });

        // geht auf Mitarbeiter
        mitarbeiterButton.addActionListener(e -> {
            new RollenVerwaltungMitarbeiterGUI();
            frame.dispose();
        });

        // geht auf Gaeste
        gaesteButton.addActionListener(e -> {
            new RollenVerwaltungGastGUI();
            frame.dispose();
        });
    }

    private void createTable() throws RemoteException {
        Object[][] data = Vereinssoftware.rollenverwaltung.vorsitzDaten();
        String[] columns = {"ID", "Vorname", "Nachname", "E-Mail", "Anschrift", "MitgliedsNr", "TelefonNr", "Spenderstatus", "Stundenkonto", "Gesperrt?", "Mitglied seit"};

        //macht Tabelle fuer den Nutzer unbearbeitbar
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //macht Tabelle fuer den Nutzer unbearbeitbar
                return false;
            }
        };

        ClientDefaults.createColumnsFromArray(columns, model);

        for (Object[] vorsitz : data) {

            model.addRow(new Object[]{
                    vorsitz[0],
                    vorsitz[1],
                    vorsitz[2],
                    vorsitz[3],
                    vorsitz[4],
                    vorsitz[5],
                    vorsitz[6],
                    vorsitz[7],
            });
        }

        table1.setModel(model);
    }
}
