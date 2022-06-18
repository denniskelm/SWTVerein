package client.gui.Rollenverwaltung;

import client.ClientDefaults;
import client.Kategorie;
import client.Umlaut;
import client.Vereinssoftware;
import client.gui.DefaultSmallPopup;
import client.gui.Mahnung.MahnungsverwaltungGUI;
import client.gui.Profilseite.AnfragelisteGUI;
import client.gui.Profilseite.Profilseite;
import client.gui.dienstleistungen.dienstleistungsangebote.DienstleistungsangebotAnzeigenGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;

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
        String[] columns = {"ID", "Vorname", "Nachname", "E-Mail", "Anschrift", "MitgliedsNr", "TelefonNr", "Spenderstatus", "Stundenkonto", "Gesperrt?", "Mitglied seit", "Rolle", "Mahnung"};

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
                    mitarbeiter[8],
                    mitarbeiter[9],
                    mitarbeiter[10],
                    "Mahnung erstellen",
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
                    String nutzerId = model.getValueAt(row, col).toString();

                    //Klick auf die Mahnung-Zelle
                    if(col == 11) {
                        new MahnungsverwaltungGUI(nutzerId);
                    }

                    //Klick auf die Rollenzeile
                    else if(col == 12) {
                        //TODO Rollenfenster öffnen
                        new RolleAuswaehlenGUI(nutzerId);
                    }
                }
            }
        });
    }
}

