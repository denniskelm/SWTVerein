package client.gui.Rollenverwaltung;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RollenverwaltungMitliedGUI extends JFrame {
    private JPanel RollenVerwaltungMitglied;
    private JTable table1;
    private JButton mitgliedButton;
    private JButton mitarbeiterButton;
    private JButton vereinsvorstandButton;
    private JButton gästeButton;

    public RollenverwaltungMitliedGUI(String title) {
        super(title);


        creatTable();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(RollenVerwaltungMitglied);
        this.pack();
        setLocationRelativeTo(null);

        // geht auf Mitglied

        mitarbeiterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RollenVerwaltungMitarbeiterGUI Mitarbeiter = new RollenVerwaltungMitarbeiterGUI("Rollen Verwaltung Mitarbeiter");
                Mitarbeiter.setVisible(true);
                RollenverwaltungMitliedGUI.this.setVisible(false);
            }


        });

        // geht auf Vereinsvorstand
        vereinsvorstandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RollenverwaltungVereinsvorstandGUI Vorstand = new RollenverwaltungVereinsvorstandGUI("Rollen Verwaltung Vereinsvorstände");
                Vorstand.setVisible(true);
                RollenverwaltungMitliedGUI.this.setVisible(false);
            }


        });
        // geht auf Gäste
        gästeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RollenVerwaltungGastGUI Gast = new RollenVerwaltungGastGUI("Rollen Verwaltung Gäste");
                Gast.setVisible(true);
                RollenverwaltungMitliedGUI.this.setVisible(false);
            }


        });
    }


    private void creatTable() {
        String[][] data = {
                {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
        };
        String[] columns = {"ID", "Name", "E-Mail", "Wohnadresse", "Mitgliedsnummer", "telefonnummer", "istSpender", "StundenKonto", "Rolle", "Mahnung"};

        table1.setModel(new DefaultTableModel(
                data, columns)


        );

        JTable table = new JTable(data, columns);
    }


    public static void main(String[] args) {
        JFrame frame = new RollenverwaltungMitliedGUI("Rollenverwaltung Mitglied");
        frame.setVisible(true);

    }


}





