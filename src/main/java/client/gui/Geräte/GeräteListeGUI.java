package client.gui.Geräte;


import client.ClientDefaults;
import client.Vereinssoftware;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;

//TODO Searchbar
// Kategorie
public class GeräteListeGUI extends JFrame {
    private JPanel GeräteListe;
    private JTable Geraeteliste;

    private JScrollPane scrollPane;
    private JTextField kategorieTextField;
    private JTextField sucheTextField;
    private JButton geraetHinzufügenButton;
    private DefaultTableModel model;

    public GeräteListeGUI(String title) {
        super(title);

        createTable();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(GeräteListe);
        this.pack();

        setLocationRelativeTo(null);

        //TODO Geraet hinzufuegen
        geraetHinzufügenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GerätHinzufügenGUI GeraetHinz = new GerätHinzufügenGUI("Geräte Hinzufügen");
                GeraetHinz.setVisible(true);
                GeräteListeGUI.this.setVisible(false);
            }
        });

        // clickyclick
        Geraeteliste.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
    }

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {
        int row = Geraeteliste.rowAtPoint(evt.getPoint());
        int col = Geraeteliste.columnAtPoint(evt.getPoint());
        String iD = null;
        if (row >= 0 && col >= 0) {
            System.out.println(row + ", " + col);

            if (col != 8)
                return;

            iD = model.getValueAt(row, 0).toString(); //GeraeteID
            System.out.println("id: " + iD);
            String gname = model.getValueAt(row, 1).toString(); //Gerät
            String spender = model.getValueAt(row, 2).toString(); //Spender
            String ort = model.getValueAt(row, 3).toString(); //Ausgabeort
            String beschreibung = model.getValueAt(row, 4).toString(); //Gerätebeschreibung
        }

        try {
            if (iD == null) return; // TODO Exception

            GeraetReservierenGUI reservierenGUI = new GeraetReservierenGUI("Geraet Reservieren", iD, Vereinssoftware.session.getID());
            System.out.println("t");
            reservierenGUI.setVisible(true);
            GeräteListeGUI.this.setVisible(false);
        } catch (NoSuchObjectException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTable() {

        try {
            Object[][] geraete = Vereinssoftware.geraeteverwaltung.omniGeraeteDaten();

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
                        "Reservieren"
                });
            }

            Geraeteliste.setModel(model);

            JTable table = new JTable(model);

        } catch (RemoteException e) {
            System.out.println("Fehler");
            throw new RuntimeException(e);
        }

        ClientDefaults.addSearchFunctionality(Geraeteliste, sucheTextField);

    }

    public static void main(String[] args) {
        JFrame frame = new GeräteListeGUI("Geräteliste");
        frame.setVisible(true);
    }
}

