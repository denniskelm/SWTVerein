package client.gui.Geräte;

import client.gui.Rollenverwaltung.RollenVerwaltungGastGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VerleihhistorieGUI extends JFrame{
    private JPanel Verleihhsitorie;
    private JTextField gerätTextField;
    private JTable table1;

    //TODO
    // Gerätnamen anzeigen
    // Stuff hinzufügen

    public VerleihhistorieGUI(String title) {
        super(title);


        creatTable();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Verleihhsitorie);
        this.pack();
    }


    private void creatTable() {
        String[][] data = {
                {"1", "2", "3", "4"},
        };
        String[] columns = {"Nutzername", "NutzerID", "von", "bis"};

        table1.setModel(new DefaultTableModel(
                data, columns)


        );

        JTable table = new JTable(data,columns);
    }



    public static void main(String[] args) {
        JFrame frame = new VerleihhistorieGUI("Verleihhistorie");
        frame.setVisible(true);

    }
}
