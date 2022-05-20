package client.gui.Geräte;

import client.Vereinssoftware;
import server.geraetemodul.Geraet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class GeräteDatenbankGUI extends JFrame {
    private JPanel Gerätedatenbank;
    private JTextField sucheTextField;
    private JTable Datenbank;
    private JButton geraetHinzufügenButton;
    private String[][] data;

    //TODO Suche
    // in geräteDB einfügen

    public GeräteDatenbankGUI(String title) {
        super(title);


        creatTable();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Gerätedatenbank);
        this.pack();

        //TODO Geräte Hinzufügen
        geraetHinzufügenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
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

            if (row != 8)
                return; // nicht auf Verwalten gedrückt

            System.out.println(row + ", " + col);
            //TODO Implementierung Klick auf Zelle
            iD = data[row][0].toString(); //GeraeteID
            String gname = data[row][1].toString(); //Gerät
            String spender = data[row][2].toString(); //Spender
            String ort = data[row][3].toString(); //Ausgabeort
            String beschreibung = data[row][4].toString(); //Gerätebeschreibung
        }

        GeräteVerwaltenGUI db = new GeräteVerwaltenGUI("Geraete Verwaltung", iD);
        db.setVisible(true);
        GeräteDatenbankGUI.this.setVisible(false);
    }

    private void creatTable() {

        try {
            ArrayList<Geraet> gl = (ArrayList<Geraet>) Vereinssoftware.geraeteverwaltung.getGeraete();

            data = new String[gl.size()][8];
            for (int i = 0; i < gl.size(); i++) {
                data[i][0] = gl.get(i).getGeraeteID();
                data[i][1] = gl.get(i).getGeraetName();
                data[i][2] = gl.get(i).getGeraetBeschreibung();
                data[i][3] = gl.get(i).getKategorie();
                data[i][4] = gl.get(i).getSpenderName();
                data[i][5] = String.valueOf(gl.get(i).getLeihfrist());
                data[i][6] = gl.get(i).getLeihstatus().getName();
                data[i][7] = "Verwalten";
            }

            String[] columns = {"GeräteID", "Gerät", "Beschreibung", "Kategorie", "Spender", "Leihfrist", "Status", "Verwalten"};

            Datenbank.setModel(new DefaultTableModel(
                data, columns)
            );

            JTable table = new JTable(data, columns);

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        JFrame frame = new GeräteDatenbankGUI("Geräte Datenbank");
        frame.setVisible(true);

    }
}
