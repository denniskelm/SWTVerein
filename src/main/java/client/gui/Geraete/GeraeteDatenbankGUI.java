package client.gui.Geraete;

import client.ClientDefaults;
import client.Umlaut;
import client.Vereinssoftware;

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

    public static JFrame frame;

    public GeraeteDatenbankGUI(String title) {
        frame = new JFrame("Ger" + Umlaut.ae() + "tedatenbank");
        frame = ClientDefaults.standardizeFrame(frame, Geraetedatenbank);


        createTable();

        //TODO Geraete Hinzufuegen
        geraetHinzufuegenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GeraetHinzufuegenGUI ghg = new GeraetHinzufuegenGUI("Neues Geraet hinzufuegen");
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
        String iD = null;
        if (row >= 0 && col >= 0) {

            if (col != 8)
                return; // nicht auf Verwalten gedrueckt

            System.out.println(row + ", " + col);
            iD = model.getValueAt(row, 0).toString(); //GeraeteID
            String gname = model.getValueAt(row, 1).toString(); //Geraet
            String spender = model.getValueAt(row, 2).toString(); //Spender
            String ort = model.getValueAt(row, 3).toString(); //Ausgabeort
            String beschreibung = model.getValueAt(row, 4).toString(); //Geraetebeschreibung
        }

        GeraeteVerwaltenGUI db = new GeraeteVerwaltenGUI("Geräteverwaltung", iD);
        GeraeteDatenbankGUI.frame.dispose();
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

            String[] columns = {"GeraeteID", "Geraet", "Beschreibung", "Kategorie", "Spender", "Leihfrist", "Status", "Abholort", "Aktion"};
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
                        "Verwalten"
                });
            }

            Datenbank.setModel(model);

            JTable table = new JTable(model);

        } catch (RemoteException e) {
            System.out.println("Fehler");
            throw new RuntimeException(e);
        }

        ClientDefaults.addSearchFunctionality(Datenbank, sucheTextField);

    }

}
