package client.gui.Rollenverwaltung;

import client.ClientDefaults;
import client.Umlaut;
import client.Vereinssoftware;
import client.gui.DefaultSmallPopup;
import client.gui.Mahnung.MahnungsverwaltungGUI;
import server.users.Rolle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.rmi.NoSuchObjectException;
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

       // System.out.println(data[0][1]);


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

        vereinsvorstaendeButton.requestFocus();
    }

    private void createTable() throws RemoteException {
        Object[][] data = Vereinssoftware.rollenverwaltung.vorsitzDaten();
        String[] columns = {"ID", "Vorname", "Nachname", "E-Mail", "Anschrift", "MitgliedsNr", "TelefonNr", "Spenderstatus", "Stundenkonto", "Gesperrt?", "Mitglied seit", "Rolle"};

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
                    vorsitz[8],
                    (Boolean.getBoolean(vorsitz[9].toString()) ? "Ja" : "Nein"),
                    vorsitz[10],
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

                    //Bestimmen, ob der Nutzer Vorsitz ist
                    boolean userIstVorsitz = false;
                    try {
                        userIstVorsitz = Vereinssoftware.session.getRolle().equals(Rolle.VORSITZ);
                    } catch (NoSuchObjectException e) {
                        userIstVorsitz = false;
                    }

                    //Klick auf die Mahnung-Zelle
                    if(col == 11) {
                        if(userIstVorsitz)
                            new RolleAuswaehlenGUI(nutzerId);
                        else
                            new DefaultSmallPopup("Keine Berechtigung!", "Sie haben keine Berechtigung, die Rolle für diese Person zu " + Umlaut.ae() + "ndern!");
                    }
                }
            }
        });
    }
}
