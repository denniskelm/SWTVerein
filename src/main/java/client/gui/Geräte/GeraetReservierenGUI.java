package client.gui.Geräte;

import client.Vereinssoftware;
import client.gui.DefaultSmallPopup;

import javax.naming.NoPermissionException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeraetReservierenGUI extends JFrame{
    private JPanel GastReservieren;
    private JButton jaButton;
    private JButton neinButton;
    private String geraeteID, ausleiherID;

    public GeraetReservierenGUI(String title, String geraeteID, String ausleiherID)    {
        super(title);
        this.geraeteID = geraeteID;
        this.ausleiherID = ausleiherID;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(GastReservieren);
        this.pack();
        setLocationRelativeTo(null);


        //Exit(ja)
        jaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Vereinssoftware.geraeteverwaltung.geraetReservieren("", ""); // TODO

                    DefaultSmallPopup smallPopup = new DefaultSmallPopup("Hinweis",
                            "Sie haben dieses Gerät erfolgreich reserviert");

                } catch (Exception ex) {
                    //throw new RuntimeException(ex);

                    if (ex instanceof NoPermissionException) {
                        DefaultSmallPopup smallPopup = new DefaultSmallPopup("Hinweis",
                                "Sie sind gesperrt \n");
                    } else if (ex instanceof ArrayIndexOutOfBoundsException) {
                        DefaultSmallPopup smallPopup = new DefaultSmallPopup("Hinweis",
                                "Sie haben bereits die maximale Anzahl an Reservierungen erreicht\n");
                    } else if (ex.getMessage().equals("Mitglied hat das Geraet bereits reserviert.")) {
                        DefaultSmallPopup smallPopup = new DefaultSmallPopup("Hinweis",
                                "Sie haben das Gerät bereits reserviert\n");
                    } else {
                        DefaultSmallPopup smallPopup = new DefaultSmallPopup("Hinweis",
                                "Ein Fehler ist aufgetreten\n");
                    }


                }
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
        JFrame frame = new GeraetReservierenGUI("Geraet Reservieren", "", "");
        frame.setVisible(true);
    }
}
