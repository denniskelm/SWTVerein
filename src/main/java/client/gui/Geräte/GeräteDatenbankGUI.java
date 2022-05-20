package client.gui.Geräte;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeräteDatenbankGUI extends JFrame {
    private JPanel Gerätedatenbank;
    private JTextField sucheTextField;
    private JTable Datenbank;
    private JButton geraetHinzufügenButton;

    //TODO Suche
    // in geräteDB einfügen

    public GeräteDatenbankGUI(String title) {
        super(title);


        creatTable();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Gerätedatenbank);
        this.pack();

        //TODO Geräte Hinzufügen
        geraetHinzufügenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });


        Datenbank.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
    }

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {

        GeräteVerwaltenGUI db = new GeräteVerwaltenGUI("Geraete Verwaltung");
        db.setVisible(true);
        GeräteDatenbankGUI.this.setVisible(false);
    }

    private void creatTable() {
        String[][] data = {
                {"1", "2", "3", "4", "5", "6", "7", "8"},
        };
        String[] columns = {"Gerät", "Beschreibung", "Kategorie", "GeräteID", "Spender", "Leihfrist", "Status", "Verwalten"};

        Datenbank.setModel(new DefaultTableModel(
                data, columns)


        );

        JTable table = new JTable(data, columns);


    }

    public static void main(String[] args) {
        JFrame frame = new GeräteDatenbankGUI("Geräte Datenbank");
        frame.setVisible(true);

    }
}
