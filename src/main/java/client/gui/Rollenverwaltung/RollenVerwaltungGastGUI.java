package client.gui.Rollenverwaltung;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RollenVerwaltungGastGUI extends JFrame{
    private JPanel RollenverwaltungGast;
    private JButton mitgliederButton;
    private JTable table1;
    private JButton mitarbeiterButton;
    private JButton vereinsvorständeButton;
    private JButton gästeButton;

    public RollenVerwaltungGastGUI(String title) {
        super(title);


        creatTable();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(RollenverwaltungGast);
        this.pack();
    }


    private void creatTable() {
        String[][] data = {
                {"1", "2", "3", "4", "5", "6", "7"},
        };
        String[] columns = {"ID", "Name", "E-Mail", "Wohnadresse", "Mitgliedsnummer", "telefonnummer",  "Rolle"};

        table1.setModel(new DefaultTableModel(
                data, columns)


        );

        JTable table = new JTable(data,columns);
    }



    public static void main(String[] args) {
        JFrame frame = new RollenVerwaltungGastGUI("Rollenverwaltung Mitglied");
        frame.setVisible(true);

    }
}
