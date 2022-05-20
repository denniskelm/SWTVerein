package client.gui.Rollenverwaltung;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RolleAuswählenGUI extends JFrame {
    private JPanel RolleAuswählen;
    private JTextField IDhiereinfügen;
    private JButton OKButton;
    private JButton abbrechenButton;
    private JRadioButton MitgliedButton;
    private JRadioButton MitarbeiterButton;
    private JRadioButton VereinsvorstandButton;


    public RolleAuswählenGUI(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(RolleAuswählen);
        this.pack();
        setLocationRelativeTo(null);


        // TODO ID anzeigen
        // TODO JRadioButton


        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        abbrechenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


    public static void main(String[] args) {
        JFrame frame = new RolleAuswählenGUI("Wert Ändern");

        frame.setVisible(true);
    }
}
