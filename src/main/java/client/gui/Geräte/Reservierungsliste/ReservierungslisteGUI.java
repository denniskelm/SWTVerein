package client.gui.Geräte.Reservierungsliste;

import client.gui.Geräte.GeräteListeGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ReservierungslisteGUI extends JFrame{
    private JPanel Reservierungsliste;
    private JTable table1;

    public ReservierungslisteGUI(String title) {
        super(title);


        creatTable();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Reservierungsliste);
        this.pack();
    }


    private void creatTable() {
        String[][] data = {
                {"1", "2", "3"},
        };
        String[] columns = {"Platz", "ID", "Gerät"};

        table1.setModel(new DefaultTableModel(
                data, columns)


        );

        JTable table = new JTable(data,columns);
    }



    public static void main(String[] args) {
        JFrame frame = new ReservierungslisteGUI("Geräteliste");
        frame.setVisible(true);

    }
}
