package client.gui.Geräte;



import client.Vereinssoftware;
import client.gui.dienstleistungen.dienstleistungsangebote.DienstleistungsangebotAnzeigenGUI;
import server.geraetemodul.Geraet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

//TODO Searchbar
// Kategorie
public class GeräteListeGUI extends JFrame{
    private JPanel GeräteListe;
    private JTable Geraeteliste;

    private JScrollPane scrollPane;
    private JTextField kategorieTextField;
    private JTextField sucheTextField;
    private JButton geraetHinzufügenButton;
    String[][] data;

    public GeräteListeGUI(String title) {
        super(title);


        try {
            createTable();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(GeräteListe);
        this.pack();


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
    //Maximale Anzahl an Reservierungen erreicht
    private void table1MouseClicked(java.awt.event.MouseEvent evt) {

        int row = Geraeteliste.rowAtPoint(evt.getPoint());
        int col = Geraeteliste.columnAtPoint(evt.getPoint());
        if (row >= 0 && col >= 0) {
            System.out.println(row + ", " + col);
            //TODO Implementierung Klick auf Zelle
            String gname = data[row][0].toString(); //Gerät
            String spender = data[row][1].toString(); //Spender
            String ort = data[row][2].toString(); //Ausgabeort
            String beschreibung = data[row][1].toString(); //Gerätebeschreibung
        }

        GeraetReservierenGUI gast = new GeraetReservierenGUI("Geraet Reservieren", "", "");
        gast.setVisible(true);
        GeräteListeGUI.this.setVisible(false);

    }

    private void createTable() throws RemoteException {
        ArrayList<Geraet> gl;
            gl = (ArrayList<Geraet>) Vereinssoftware.geraeteverwaltung.getGeraete();

        String[] columns = {"Gerät", "Spender", "Ausgabeort", "Gerätebeschreibung", "Reservieren"};

        data = new String[gl.size()][5];

        for (int i = 0; i < Vereinssoftware.geraeteverwaltung.getGeraete().size(); i++) {
            data[i][0] = ((Geraet) (Vereinssoftware.geraeteverwaltung.getGeraete().get(i))).getGeraetName();
            data[i][1] = ((Geraet) (Vereinssoftware.geraeteverwaltung.getGeraete().get(i))).getSpenderName();
            data[i][2] = ((Geraet) (Vereinssoftware.geraeteverwaltung.getGeraete().get(i))).getGeraetAbholort();
            data[i][3] = ((Geraet) (Vereinssoftware.geraeteverwaltung.getGeraete().get(i))).getGeraetBeschreibung();
            data[i][4] = "Jetzt reservieren";
        }

        Geraeteliste.setModel(new DefaultTableModel(
                data, columns));


    }

    public static void main(String[] args) {
        JFrame frame = new GeräteListeGUI("Geräteliste");
        frame.setVisible(true);

    }
}

