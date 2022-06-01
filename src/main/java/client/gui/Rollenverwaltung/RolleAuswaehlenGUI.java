package client.gui.Rollenverwaltung;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RolleAuswaehlenGUI extends JFrame {
    private JPanel RolleAuswaehlen;
    private JTextField IDhiereinfuegen;
    private JButton OKButton;
    private JButton abbrechenButton;
    private JRadioButton MitgliedButton;
    private JRadioButton MitarbeiterButton;
    private JRadioButton VereinsvorstandButton;


    public RolleAuswaehlenGUI(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(RolleAuswaehlen);
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
        JFrame frame = new RolleAuswaehlenGUI("Wert aendern");

        frame.setVisible(true);
    }
}
