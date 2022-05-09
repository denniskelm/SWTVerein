package client.gui.Geräte;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GeräteListeGUI extends JFrame{
    private JPanel GeräteListe;
    private JTable table1;
    private JTextField kategorieTextField;
    private JTextField sucheTextField;
    private JButton gerätHinzufügenButton;

    public GeräteListeGUI(String title) {
        super(title);


        creatTable();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(GeräteListe);
        this.pack();
    }


    private void creatTable() {
        String[][] data = {
                {"1", "2", "3", "4","5"},
        };
        String[] columns = {"Gerät", "Spender", "Ausgabeort", "Gerätebeschreibung", "Reservieren"};

        table1.setModel(new DefaultTableModel(
                data, columns)


        );

        JTable table = new JTable(data,columns);
    }



    public static void main(String[] args) {
        JFrame frame = new GeräteListeGUI("Geräteliste");
        frame.setVisible(true);

    }
}
