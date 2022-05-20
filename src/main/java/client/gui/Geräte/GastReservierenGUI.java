package client.gui.Geräte;

import client.gui.DefaultSmallPopup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GastReservierenGUI extends JFrame{
    private JPanel GastReservieren;
    private JButton jaButton;
    private JButton neinButton;

    public GastReservierenGUI(String title)    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(GastReservieren);
        this.pack();


        //Exit(ja)
        jaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });
        //Exit(nein)
        neinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });
/*
        //max. an Reser. erreicht
        //TODO max. Res. erreicht
        jaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultSmallPopup smallPopup = new DefaultSmallPopup("Hinweis",
                        "Sie haben bereits die maximale Anzahl an Reservierungen erreicht\n");
            }
        });

        //erfolgreich
        jaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultSmallPopup smallPopup = new DefaultSmallPopup("Hinweis",
                        "Sie haben dieses Gerät erfolgreich reserviert");
            }
        });
        //exisitiert net meht
        //TODO Geraet existiert net mehr
        jaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultSmallPopup smallPopup = new DefaultSmallPopup("Hinweis",
                        "Dieses Gerät existiert nicht mehr");
            }
        });
*/

    }

    public static void main(String[] args) {
        JFrame frame = new GastReservierenGUI("Gast Reservieren");
        frame.setVisible(true);
    }
}
