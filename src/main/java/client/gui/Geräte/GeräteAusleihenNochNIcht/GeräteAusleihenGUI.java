package client.gui.Geräte.GeräteAusleihenNochNIcht;

import client.gui.DefaultSmallPopup;
import client.gui.DefaultTextWithButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeräteAusleihenGUI extends JFrame {
    private JButton jetztReservierenButton;
    private JPanel GerätAusleihen;
    private JLabel picture;

    public GeräteAusleihenGUI(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(GerätAusleihen);
        this.pack();
        setLocationRelativeTo(null);
        picture.add(new JLabel(new ImageIcon("Path/To/Your/Image.png")));


        // Ausgeliehen (Mitglied)

        //TODO Funktionalität
        // (bis wann ists ausgliehen)
        // (wie viele ppl in queue)
        jetztReservierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTextWithButton textWithButton = new DefaultTextWithButton("Hinweis",
                        "Dieses Gerät ist schon ausgeliehen bis maximal <Date>. Vor Ihnen sind noch <LaengeVonQueue> Personen.",
                        "Bestaetigen");
            }
        });

        // Erfolgreich Ausgeliehen
        //TODO Leihfrist starten und Gerät ausleihen
        jetztReservierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTextWithButton textWithButton = new DefaultTextWithButton("Hinweis",
                        "Dieses Gerät ist erfolgreich von Ihnen ausgeliehen. Die Leihfrist ist nun gestartet.",
                        "Bestaetigen");

            }
        });

        // Ausgeliehen ( Mitarbeiter)

        //TODO funktionalität
        // (PersonenName)
        // (Mitgl.Nr. anzeigen)
        // (reservieren)
        jetztReservierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AusgeliehenMitarbeiterGUI SchonAusgeliehen = new AusgeliehenMitarbeiterGUI("Hinweis");
            }
        });

        //TODO funktionalität
        // (AnzahlDerReserviertenGeräte von der Person)
        jetztReservierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultSmallPopup smallPopup = new DefaultSmallPopup("Hinweis", "Sie haben bereits <Anzahl> Geräte ausgeliehen bzw. reserviert.");
            }
        });

        //TODO funktionalität
        // (Mit Mahnung verknüpfen)
        jetztReservierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultSmallPopup smallPopup = new DefaultSmallPopup("Hinweis",
                        "Sie sind aufgrund mehrerer Mahnungen für das Reservieren von Geräten gesperrt. " +
                                "Melden Sie sich gegebenfalls bei einem Vereinsmitarbeiter.");
            }
        });

        //TODO mit Registireren verbinden
        jetztReservierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTextWithButton textWithButton = new DefaultTextWithButton("Hinweis",
                        "Sie haben noch keinen Zugriff auf diese Funktion",
                        "Jetzt registrieren");

            }
        });


    }

    public static void main(String[] args) {
        JFrame frame = new GeräteAusleihenGUI("Gerät Ausleihen");
        frame.setVisible(true);
    }
}
