package client.gui.Geraete;

import client.ClientDefaults;
import client.Umlaut;
import client.Vereinssoftware;
import client.gui.DefaultSmallPopup;

import javax.naming.NoPermissionException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI fuer die Reservierung eines Geraets
 * <p>
 * Hauptautor
 *
 * @author Gia Huy Hans Tran
 * <p>
 * Kleine Verbesserungen
 * @author Dennis Kelm
 */
public class GeraetReservierenGUI {
    private JPanel GeraetReservieren;
    private JButton jaButton;
    private JButton neinButton;
    private final String geraeteID;
    private final String ausleiherID;

    public GeraetReservierenGUI(String geraeteID, String ausleiherID) {
        this.geraeteID = geraeteID;
        this.ausleiherID = ausleiherID;

        JFrame frame = new JFrame("Ger" + Umlaut.ae() + "t reservieren");
        frame = ClientDefaults.standardizeFrame(frame, GeraetReservieren);


        //Exit(ja)
        jaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Vereinssoftware.geraeteverwaltung.geraetReservieren(geraeteID, ausleiherID);

                    DefaultSmallPopup smallPopup = new DefaultSmallPopup("Hinweis",
                            "Sie haben dieses Geraet erfolgreich reserviert");

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
                                "Sie haben das Geraet bereits reserviert\n");
                    } else {
                        DefaultSmallPopup smallPopup = new DefaultSmallPopup("Hinweis",
                                "Ein Fehler ist aufgetreten\n");
                    }


                }
            }
        });
        //Exit(nein)
        neinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
                        "Sie haben dieses Geraet erfolgreich reserviert");
            }
        });
        //exisitiert net meht
        //TODO Geraet existiert net mehr
        jaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultSmallPopup smallPopup = new DefaultSmallPopup("Hinweis",
                        "Dieses Geraet existiert nicht mehr");
            }
        });
*/

    }
}
