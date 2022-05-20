package client.gui.Geräte;

import client.ClientDefaults;
import client.Vereinssoftware;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class GeräteDatenbankGUI extends JFrame {
    private JPanel Gerätedatenbank;
    private JTextField sucheTextField;
    private JTable Datenbank;
    private JButton geraetHinzufügenButton;
    private DefaultTableModel model;

    //TODO Suche
    // in geräteDB einfügen

    public GeräteDatenbankGUI(String title) {
        super(title);


        createTable();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Gerätedatenbank);
        this.pack();
        setLocationRelativeTo(null);

        //TODO Geräte Hinzufügen
        geraetHinzufügenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GerätHinzufügenGUI ghg = new GerätHinzufügenGUI("Neues Gerät hinzufügen");
                ghg.setVisible(true);
                dispose();
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
                return; // nicht auf Verwalten gedrückt

            System.out.println(row + ", " + col);
            iD = model.getValueAt(row, 0).toString(); //GeraeteID
            String gname = model.getValueAt(row, 1).toString(); //Gerät
            String spender = model.getValueAt(row, 2).toString(); //Spender
            String ort = model.getValueAt(row, 3).toString(); //Ausgabeort
            String beschreibung = model.getValueAt(row, 4).toString(); //Gerätebeschreibung
        }

        GeräteVerwaltenGUI db = new GeräteVerwaltenGUI("Geraete Verwaltung", iD);
        db.setVisible(true);
        GeräteDatenbankGUI.this.setVisible(false);
    }

    private void createTable() {

        try {
            Object[][] geraete = Vereinssoftware.geraeteverwaltung.OmniGeraeteDaten();

            model = new DefaultTableModel() {

                @Override
                public boolean isCellEditable(int row, int column) {
                    //macht Tabelle für den Nutzer unbearbeitbar
                    return false;
                }
            };

            String[] columns = {"GeräteID", "Gerät", "Beschreibung", "Kategorie", "Spender", "Leihfrist", "Status", "Abholort", "Aktion"};
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

    public static void main(String[] args) {
        JFrame frame = new GeräteDatenbankGUI("Geräte Datenbank");
        frame.setVisible(true);

    }
}
