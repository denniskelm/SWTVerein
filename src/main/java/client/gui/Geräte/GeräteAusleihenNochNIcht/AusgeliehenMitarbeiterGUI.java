package client.gui.Geräte.GeräteAusleihenNochNIcht;

import client.Vereinssoftware;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AusgeliehenMitarbeiterGUI extends  JFrame{
    private JPanel panel1;
    private JButton jetztReservierenButton;
    private JButton abbrechenButton;

    public AusgeliehenMitarbeiterGUI(String title)    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
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

    public static void main(String[] args) {
        JFrame frame = new AusgeliehenMitarbeiterGUI("Hinweis");
        frame.setVisible(true);
    }
}
