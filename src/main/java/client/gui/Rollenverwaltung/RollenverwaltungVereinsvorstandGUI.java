package client.gui.Rollenverwaltung;

import client.gui.Mahnung.MahnungsverwaltungGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RollenverwaltungVereinsvorstandGUI extends  JFrame{
    private JPanel RollenVerwaltungVereinsVorstand;
    private JButton mitgliedButton;
    private JButton mitarbeiterButton;
    private JButton vereinsvorständeButton;
    private JButton gästeButton;
    private JTable table1;

    public RollenverwaltungVereinsvorstandGUI(String title)    {
        super(title);


        creatTable();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(RollenVerwaltungVereinsVorstand);
        this.pack();
    }


    private void creatTable()   {
        String[][] data = {
                {"1","2","3","4","5","6","7"},
        };

        table1.setModel(new DefaultTableModel(
                data,
                new String[] {"ID", "Name", "E-Mail", "Wohnadresse", "Mitgliedsnummer","istSpender", "telefonnummer", "Studenkonto"}

        ));

    }

    public static void main(String[] args) {
        JFrame frame = new RollenverwaltungVereinsvorstandGUI("Mahnung Verwalten");
        frame.setVisible(true);
    }
}
