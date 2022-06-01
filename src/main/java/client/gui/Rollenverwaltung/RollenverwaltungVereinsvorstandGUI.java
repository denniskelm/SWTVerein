package client.gui.Rollenverwaltung;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RollenverwaltungVereinsvorstandGUI extends JFrame {
    private JPanel RollenVerwaltungVereinsVorstand;
    private JButton mitgliedButton;
    private JButton mitarbeiterButton;
    private JButton vereinsvorstaendeButton;
    private JButton gaesteButton;
    private JTable table1;

    public RollenverwaltungVereinsvorstandGUI(String title) {
        super(title);


        creatTable();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(RollenVerwaltungVereinsVorstand);
        this.pack();
        setLocationRelativeTo(null);


        // geht auf Mitglied
        mitgliedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RollenverwaltungMitliedGUI Mitglied = new RollenverwaltungMitliedGUI("Rollen Verwaltung Vereinsvorstaende");
                Mitglied.setVisible(true);
                RollenverwaltungVereinsvorstandGUI.this.setVisible(false);
            }

        });
        // geht auf Mitarbeiter

        mitarbeiterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RollenverwaltungVereinsvorstandGUI Vereinsvorsitz = new RollenverwaltungVereinsvorstandGUI("Rollen Verwaltung Mitglied");
                Vereinsvorsitz.setVisible(true);
                RollenverwaltungVereinsvorstandGUI.this.setVisible(false);
            }
        });


        // geht auf Gaeste
        gaesteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RollenVerwaltungGastGUI Gast = new RollenVerwaltungGastGUI("Rollen Verwaltung Gaeste");
                Gast.setVisible(true);
                RollenverwaltungVereinsvorstandGUI.this.setVisible(false);
            }
        });
    }


    private void creatTable() {
        String[][] data = {
                {"1", "2", "3", "4", "5", "6", "7"},
        };

        table1.setModel(new DefaultTableModel(
                data,
                new String[]{"ID", "Name", "E-Mail", "Wohnadresse", "Mitgliedsnummer", "istSpender", "telefonnummer", "Studenkonto"}

        ));

    }

    public static void main(String[] args) {
        JFrame frame = new RollenverwaltungVereinsvorstandGUI("Mahnung Verwalten");
        frame.setVisible(true);
    }
}
