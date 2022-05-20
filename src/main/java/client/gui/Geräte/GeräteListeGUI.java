package client.gui.Geräte;



import client.Vereinssoftware;
import server.geraetemodul.Geraet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public GeräteListeGUI(String title) {
        super(title);


        creatTable();
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

        GeraetReservierenGUI gast = new GeraetReservierenGUI("Geraet Reservieren", "", "");
        gast.setVisible(true);
        GeräteListeGUI.this.setVisible(false);


    }

    private void creatTable() {
        String[] columns = {"Gerät", "Spender", "Ausgabeort", "Gerätebeschreibung", "Reservieren"};
        String[][] data = new String[Vereinssoftware.geraeteverwaltung.getGeraete().size()][5];

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

