package client.gui.Geräte;



import client.Vereinssoftware;
import server.geraetemodul.Geraet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.util.ArrayList;

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

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {
        int row = Geraeteliste.rowAtPoint(evt.getPoint());
        int col = Geraeteliste.columnAtPoint(evt.getPoint());
        String iD = null;
        if (row >= 0 && col >= 0) {
            System.out.println(row + ", " + col);
            //TODO Implementierung Klick auf Zelle
            iD = data[row][0].toString(); //GeraeteID
            String gname = data[row][1].toString(); //Gerät
            String spender = data[row][2].toString(); //Spender
            String ort = data[row][3].toString(); //Ausgabeort
            String beschreibung = data[row][4].toString(); //Gerätebeschreibung
        }

        try {
            ArrayList<Geraet> gl = (ArrayList<Geraet>) Vereinssoftware.geraeteverwaltung.getGeraete();
            if (iD != null) return; // TODO Exception

            GeraetReservierenGUI reservierenGUI = new GeraetReservierenGUI("Geraet Reservieren", iD, Vereinssoftware.session.getID());
            reservierenGUI.setVisible(true);
            GeräteListeGUI.this.setVisible(false);
        } catch (NoSuchObjectException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTable() throws RemoteException {
        ArrayList<Geraet> gl = (ArrayList<Geraet>) Vereinssoftware.geraeteverwaltung.getGeraete();

        String[] columns = {"Gerät", "Spender", "Ausgabeort", "Gerätebeschreibung", "Reservieren"};

        data = new String[gl.size()][6];
        for (int i = 0; i < gl.size(); i++) {
            data[i][0] = gl.get(i).getGeraeteID();
            data[i][1] = gl.get(i).getGeraetName();
            data[i][2] = gl.get(i).getSpenderName();
            data[i][3] = gl.get(i).getGeraetAbholort();
            data[i][4] = gl.get(i).getGeraetBeschreibung();
            data[i][5] = "Jetzt reservieren";
        }
        Geraeteliste.setModel(new DefaultTableModel(
                data, columns));
    }

    public static void main(String[] args) {
        JFrame frame = new GeräteListeGUI("Geräteliste");
        frame.setVisible(true);
    }
}

