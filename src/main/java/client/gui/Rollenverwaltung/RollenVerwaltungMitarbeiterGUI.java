package client.gui.Rollenverwaltung;

import client.ClientDefaults;
import client.Vereinssoftware;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
            new RollenverwaltungMitgliedGUI();
            frame.dispose();
        });

        // geht auf Vereinsvorstand
        VereinsvorstaendeButton.addActionListener(e -> {
            new RollenverwaltungVereinsvorstandGUI();
            frame.dispose();
        });
        // geht auf Gaeste
        gaesteButton.addActionListener(e -> {
            new RollenVerwaltungGastGUI();
            frame.dispose();
        });

    }

    private void createTable() throws RemoteException {
        Object[][] data = Vereinssoftware.rollenverwaltung.mitarbeiterDaten();
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

        for (Object[] mitarbeiter : data) {

            model.addRow(new Object[]{
                    mitarbeiter[0],
                    mitarbeiter[1],
                    mitarbeiter[2],
                    mitarbeiter[3],
                    mitarbeiter[4],
                    mitarbeiter[5],
                    mitarbeiter[6],
                    mitarbeiter[7],
            });
        }

        table1.setModel(model);
    }
}

