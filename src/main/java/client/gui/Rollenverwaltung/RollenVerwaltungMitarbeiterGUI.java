package client.gui.Rollenverwaltung;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RollenVerwaltungMitarbeiterGUI extends JFrame{
    private JPanel RollenVerwaltungMitarbeiter;
    private JTable table1;
    private JButton mitgliederButton;
    private JButton mitarbeiterButton;
    private JButton VereinsvorständeButton;
    private JButton gästeButton;

    public RollenVerwaltungMitarbeiterGUI(String title) {
        super(title);


        creatTable();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(RollenVerwaltungMitarbeiter);
        this.pack();
        setLocationRelativeTo(null);



        // geht auf Mitglied

        mitgliederButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RollenverwaltungMitliedGUI Mitglied = new RollenverwaltungMitliedGUI("Rollen Verwaltung Mitglied");
                Mitglied.setVisible(true);
                RollenVerwaltungMitarbeiterGUI.this.setVisible(false);
            }


        });

        // geht auf Vereinsvorstand
        VereinsvorständeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RollenverwaltungVereinsvorstandGUI Vorstand = new RollenverwaltungVereinsvorstandGUI("Rollen Verwaltung Vereinsvorstände");
                Vorstand.setVisible(true);
                RollenVerwaltungMitarbeiterGUI.this.setVisible(false);
            }


        });
        // geht auf Gäste
        gästeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RollenVerwaltungGastGUI Gast = new RollenVerwaltungGastGUI("Rollen Verwaltung Gäste");
                Gast.setVisible(true);
                RollenVerwaltungMitarbeiterGUI.this.setVisible(false);
            }


        });


    }


    private void creatTable() {
        String[][] data = {
                {"1", "2", "3", "4", "5", "6", "7", "8", "9","10"},
        };
        String[] columns = {"ID", "Name", "E-Mail", "Wohnadresse", "Mitgliedsnummer", "telefonnummer","StundenKonto", "istSpender", "Rolle", "Mahnung"};

        table1.setModel(new DefaultTableModel(
                data, columns)


        );

        JTable table = new JTable(data,columns);
    }



    public static void main(String[] args) {
        JFrame frame = new RollenverwaltungMitliedGUI("Rollenverwaltung Gäste");
        frame.setVisible(true);

    }



}

