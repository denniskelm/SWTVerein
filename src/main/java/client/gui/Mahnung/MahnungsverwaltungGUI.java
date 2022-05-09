package client.gui.Mahnung;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MahnungsverwaltungGUI extends JFrame{
    private JPanel MahnungVerwaltung;
    private JTable table1;
    private JButton erstellenButton;
    private JScrollPane Scrollpane;
    private JTextField grundFürMahnungTextField;
    private JTextField verfallsdatumTextField;

    public MahnungsverwaltungGUI(String title)    {
        super(title);


        creatTable();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MahnungVerwaltung);
        this.pack();
    }


    private void creatTable()   {
        String[][] data = {
                {"1","2","3"},
                {"4","5","6"},
                {"7","8","9"}
        };

        table1.setModel(new DefaultTableModel(
                data,
                new String[] {"Mahnungen", "Verfallsdatum", "Grund für Mahnung"}

        ));

    }

    public static void main(String[] args) {
        JFrame frame = new MahnungsverwaltungGUI("Mahnung Verwalten");
        frame.setVisible(true);
    }
}
