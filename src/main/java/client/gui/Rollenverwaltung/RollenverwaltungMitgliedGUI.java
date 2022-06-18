package client.gui.Rollenverwaltung;

import client.ClientDefaults;
import client.Vereinssoftware;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.rmi.RemoteException;

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

        try {
            createTable();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        frame = new JFrame("Rollenverwaltung - Mitglied");
        frame = ClientDefaults.standardizeFrame(frame, RollenVerwaltungMitglied);

        // geht auf Mitarbeiter
        mitarbeiterButton.addActionListener(e -> {
            new RollenVerwaltungMitarbeiterGUI();
            frame.dispose();
        });

        // geht auf Vereinsvorstand
        vereinsvorstandButton.addActionListener(e -> {
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
       Object[][] data = Vereinssoftware.rollenverwaltung.mitgliederDaten();
       String[] columns = {"ID", "Vorname", "Nachname", "E-Mail", "Anschrift", "MitgliedsNr", "TelefonNr", "Spenderstatus", "Stundenkonto", "Gesperrt?", "Mitglied seit", ""};

       //macht Tabelle fuer den Nutzer unbearbeitbar
       DefaultTableModel model = new DefaultTableModel() {
           @Override
           public boolean isCellEditable(int row, int column) {
               //macht Tabelle fuer den Nutzer unbearbeitbar
               return false;
           }
       };

       ClientDefaults.createColumnsFromArray(columns, model);

       for (Object[] mitglied : data) {

           model.addRow(new Object[]{
                   mitglied[0],
                   mitglied[1],
                   mitglied[2],
                   mitglied[3],
                   mitglied[4],
                   mitglied[5],
                   mitglied[6],
                   mitglied[7],
           });
       }

       table1.setModel(model);
   }
}





