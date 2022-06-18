package client.gui.Geraete;

import client.ClientDefaults;
import client.Umlaut;
import client.Vereinssoftware;
import client.gui.Profilseite.Profilseite;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class GeraeteDatenbankGUI {
    private JPanel Geraetedatenbank;
    private JTextField sucheTextField;
    private JTable Datenbank;
    private JButton geraetHinzufuegenButton;
    private DefaultTableModel model;

    //TODO Suche
    // in geraeteDB einfuegen

    private static JFrame frame;

    public GeraeteDatenbankGUI() {
        frame = new JFrame("Ger" + Umlaut.ae() + "tedatenbank");
        frame = ClientDefaults.standardizeFrame(frame, Geraetedatenbank);

        geraetHinzufuegenButton.setText("Ger" + Umlaut.ae() + "t hinzuf" + Umlaut.ue() + "gen");

        createTable();

        //TODO Geraete Hinzufuegen
        geraetHinzufuegenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new GeraetHinzufuegenGUI();
            }
        });

        Datenbank.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
    }

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {

        int row = Datenbank.rowAtPoint(evt.getPoint());
        int col = Datenbank.columnAtPoint(evt.getPoint());
        String iD;
        if (row >= 0 && col >= 0) {

            if (col == 4) {
                new Profilseite(model.getValueAt(row, 4).toString());
            } else {
                System.out.println(row + ", " + col);
                iD = model.getValueAt(row, 0).toString(); //GeraeteID

                new GeraeteVerwaltenGUI(iD);
                GeraeteDatenbankGUI.frame.dispose();
            }
        }

    }

    private void createTable() {

        try {
            Object[][] geraete = Vereinssoftware.geraeteverwaltung.omniGeraeteDaten();

            model = new DefaultTableModel() {

                @Override
                public boolean isCellEditable(int row, int column) {
                    //macht Tabelle fuer den Nutzer unbearbeitbar
                    return false;
                }
            };

            String[] columns = {"GeraeteID", "Geraet", "Beschreibung", "Kategorie", "SpenderID", "Leihfrist", "Status", "Abholort"};
            ClientDefaults.createColumnsFromArray(columns, model);

            for (Object[] geraet : geraete) {

                if (geraet[0] == null)
                    break;

                model.addRow(new Object[]{
                        geraet[0],
                        geraet[1],
                        geraet[2],
                        geraet[3],
                        geraet[4],
                        geraet[5],
                        geraet[6],
                        geraet[7],
                });
            }

            Datenbank.setModel(model);

        } catch (RemoteException e) {
            System.out.println("Fehler");
            throw new RuntimeException(e);
        }

        ClientDefaults.addSearchFunctionality(Datenbank, sucheTextField);

    }

}
