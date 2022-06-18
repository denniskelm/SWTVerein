package client.gui.Geraete.GeraeteAusleihenNochNIcht;

import client.gui.DefaultSmallPopup;
import client.gui.DefaultTextWithButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeraeteAusleihenGUI extends JFrame {
    private JButton jetztReservierenButton;
    private JPanel GeraetAusleihen;
    private JLabel picture;

    public GeraeteAusleihenGUI(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(GeraetAusleihen);
        this.pack();
        setLocationRelativeTo(null);
        picture.add(new JLabel(new ImageIcon("Path/To/Your/Image.png")));


        // Ausgeliehen (Mitglied)

        //TODO Funktionalitaet
        // (bis wann ists ausgliehen)
        // (wie viele ppl in queue)
        jetztReservierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTextWithButton textWithButton = new DefaultTextWithButton("Hinweis",
                        "Dieses Geraet ist schon ausgeliehen bis maximal <Date>. Vor Ihnen sind noch <LaengeVonQueue> Personen.",
                        "Bestaetigen");
            }
        });

        // Erfolgreich Ausgeliehen
        //TODO Leihfrist starten und Geraet ausleihen
        jetztReservierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DefaultTextWithButton("Hinweis",
                        "Dieses Geraet ist erfolgreich von Ihnen ausgeliehen. Die Leihfrist ist nun gestartet.",
                        "Bestaetigen");

            }
        });

        // Ausgeliehen (Mitarbeiter)

        //TODO funktionalitaet
        // (PersonenName)
        // (Mitgl.Nr. anzeigen)
        // (reservieren)
        jetztReservierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AusgeliehenMitarbeiterGUI("Hinweis");
            }
        });

        //TODO funktionalitaet
        // (AnzahlDerReserviertenGeraete von der Person)
        jetztReservierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DefaultSmallPopup("Hinweis", "Sie haben bereits <Anzahl> Geraete ausgeliehen bzw. reserviert.");
            }
        });

        //TODO funktionalitaet
        // (Mit Mahnung verknuepfen)
        jetztReservierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DefaultSmallPopup("Hinweis",
                        "Sie sind aufgrund mehrerer Mahnungen fuer das Reservieren von Geraeten gesperrt. " +
                                "Melden Sie sich gegebenfalls bei einem Vereinsmitarbeiter.");
            }
        });

        //TODO mit Registireren verbinden
        jetztReservierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DefaultTextWithButton("Hinweis", "Sie haben noch keinen Zugriff auf diese Funktion",
                        "Jetzt registrieren");

            }
        });

    }
}
