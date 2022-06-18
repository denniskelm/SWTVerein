package client.gui.Rollenverwaltung;

import client.ClientDefaults;
import client.Umlaut;
import client.Vereinssoftware;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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

        try {
            createTable();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        frame = new JFrame("Rollenverwaltung - G" + Umlaut.ae() + "ste");
        frame = ClientDefaults.standardizeFrame(frame, RollenverwaltungGast);


        // geht auf Mitglied
        mitgliederButton.addActionListener(e -> {
            new RollenverwaltungMitgliedGUI();
            frame.dispose();
        });

        // geht auf Mitarbeiter
        mitarbeiterButton.addActionListener(e -> {
            new RollenVerwaltungMitarbeiterGUI();
            frame.dispose();
        });

        // geht auf Vereinsvorstand
        vereinsvorstaendeButton.addActionListener(e -> {
            new RollenverwaltungVereinsvorstandGUI();
            frame.dispose();
        });

        gaesteButton.requestFocus();
    }

    private void createTable() throws RemoteException {
        Object[][] data = Vereinssoftware.rollenverwaltung.gaesteDaten();
        String[] columns = {"ID", "Vorname", "Nachname", "E-Mail", "Anschrift", "MitgliedsNr", "TelefonNr", "Spender?", "Rolle"};

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //macht Tabelle fuer den Nutzer unbearbeitbar
                return false;
            }
        };

        ClientDefaults.createColumnsFromArray(columns, model);

        for (Object[] gast : data) {

            model.addRow(new Object[]{
                    gast[0],
                    gast[1],
                    gast[2],
                    gast[3],
                    gast[4],
                    gast[5],
                    gast[6],
                    gast[7],
                    "Rolle " + Umlaut.ae() + "ndern"
            });
        }

        table1.setModel(model);

        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table1.rowAtPoint(evt.getPoint());
                int col = table1.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    String nutzerId = model.getValueAt(row, 0).toString();

                    //Klick auf die Rollenverwaltung-Zelle
                    if (col == 8) {
                        new RolleAuswaehlenGUI(nutzerId);
                        frame.dispose();
                    }

                }
            }

        });
    }
}
