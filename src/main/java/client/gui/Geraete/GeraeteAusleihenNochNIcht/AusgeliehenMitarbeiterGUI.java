package client.gui.Geraete.GeraeteAusleihenNochNIcht;

import client.Vereinssoftware;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AusgeliehenMitarbeiterGUI extends JFrame {
    private JPanel panel1;
    private JButton jetztReservierenButton;
    private JButton abbrechenButton;

    public AusgeliehenMitarbeiterGUI(String title) {
        super(title);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(panel1);
        pack();
        setLocationRelativeTo(null);

        //TODO Reservieren

        jetztReservierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Vereinssoftware.geraeteverwaltung.geraetReservieren("", ""); // TODO
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        abbrechenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

}
