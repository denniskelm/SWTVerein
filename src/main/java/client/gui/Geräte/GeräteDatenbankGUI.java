package client.gui.Geräte;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GeräteDatenbankGUI extends JFrame{
    private JPanel Gerätedatenbank;
    private JTextField sucheTextField;
    private JTable table1;
    private JButton gerätHinzufügenButton;

    public GeräteDatenbankGUI(String title) {
        super(title);


        creatTable();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Gerätedatenbank);
        this.pack();
    }


    private void creatTable() {
        String[][] data = {
                {"1", "2", "3", "4","5","6","7","8"},
        };
        String[] columns = {"Gerät", "Beschreibung", "Kategorie", "GeräteID", "Spender", "Leihfrist", "Status", "Verwalten"};

        table1.setModel(new DefaultTableModel(
                data, columns)


        );

        JTable table = new JTable(data,columns);
    }



    public static void main(String[] args) {
        JFrame frame = new GeräteDatenbankGUI("Geräte Datenbank");
        frame.setVisible(true);

    }
}
