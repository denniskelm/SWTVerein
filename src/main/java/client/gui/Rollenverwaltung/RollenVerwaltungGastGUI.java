package client.gui.Rollenverwaltung;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RollenVerwaltungGastGUI extends JFrame {
    private JPanel RollenverwaltungGast;
    private JButton mitgliederButton;
    private JTable table1;
    private JButton mitarbeiterButton;
    private JButton vereinsvorständeButton;
    private JButton gästeButton;


    public RollenVerwaltungGastGUI(String title) {
        super(title);


        creatTable();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(RollenverwaltungGast);
        this.pack();
        setLocationRelativeTo(null);


        // geht auf Mitglied
        mitgliederButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RollenverwaltungMitliedGUI Mitglied = new RollenverwaltungMitliedGUI("Rollen Verwaltung Mitglied");
                Mitglied.setVisible(true);
                RollenVerwaltungGastGUI.this.setVisible(false);
            }


        });

        // geht auf Mitarbeiter
        mitarbeiterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RollenVerwaltungMitarbeiterGUI Mitarbeiter = new RollenVerwaltungMitarbeiterGUI("Rollen Verwaltung Mitarbeiter");
                Mitarbeiter.setVisible(true);
                RollenVerwaltungGastGUI.this.setVisible(false);
            }
        });


        // geht auf Vereinsvorstand
        vereinsvorständeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RollenverwaltungVereinsvorstandGUI Vorstand = new RollenverwaltungVereinsvorstandGUI("Rollen Verwaltung Vorstand");
                Vorstand.setVisible(true);
                RollenVerwaltungGastGUI.this.setVisible(false);
            }
        });


    }


    private void creatTable() {
        String[][] data = {
                {"1", "2", "3", "4", "5", "6", "7"},
        };
        String[] columns = {"ID", "Name", "E-Mail", "Wohnadresse", "Mitgliedsnummer", "telefonnummer", "Rolle"};

        table1.setModel(new DefaultTableModel(
                data, columns)


        );

        JTable table = new JTable(data, columns);
    }


    public static void main(String[] args) {
        JFrame frame = new RollenVerwaltungGastGUI("Rollenverwaltung Mitglied");
        frame.setVisible(true);

    }
}
