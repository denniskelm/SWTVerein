package client.gui.dienstleistungen.dienstleistungsangebote;
/*
@author
Dennis Kelm
*/

import client.ClientDefaults;
import client.Vereinssoftware;
import client.gui.DefaultTextWithButton;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//TODO Was macht diese Klasse?
public class DienstleistungsangebotAnzeigenGUI {
    private JPanel dienstleistungsangebotAnzeigenGUI;
    private JLabel headlineLabel;
    private JPanel metaOuterPanel;
    private JPanel metaLeftPanel;
    private JPanel metaRightPanel;
    private JLabel metaInfoText1;
    private JLabel metaInfoText2;
    private JLabel metaInfoText3;
    private JLabel metaInfoText4;
    private JPanel imageOuterPanel;
    private JPanel imageHeadlinePanel;
    private JPanel displayImagePanel;
    private JLabel imageHeadlineText;
    private JLabel descriptionText;
    private JButton jetztReservierenButton;
    private JLabel imageLabel;


    //Zeigt die Details des Dienstleistungsangebots an
    public DienstleistungsangebotAnzeigenGUI(String angebots_ID, String titel, String pathToImage, String beschreibung, String kategorie, LocalDateTime ab, LocalDateTime bis, String personen_ID) {
        JFrame frame = new JFrame("Details des Dienstleistungsangebots");
        ClientDefaults.insertImageToPanel(imageLabel, pathToImage); //URL zum Bild, z.B. "https://bilder.gartenpaul.de/item/images/456/full/456-R1-M1.jpg"
        frame = ClientDefaults.standardizeFrame(frame, dienstleistungsangebotAnzeigenGUI);
        frame.setLocationRelativeTo(null);

        //Anpassen der Texte
        headlineLabel.setText(titel); //sowas wie Vereinssoftware.dienstleistungsverwaltung.getGeraetInformation(String geraeteID)[0]
        metaInfoText1.setText("Kategorie: " + kategorie);
        metaInfoText2.setText("Verfügbar ab: " + ab.format(DateTimeFormatter.ISO_LOCAL_DATE));
        metaInfoText3.setText("Verfügbar bis: " + bis.format(DateTimeFormatter.ISO_LOCAL_DATE));
        try {
            metaInfoText4.setText("Angeboten von:  " + Vereinssoftware.rollenverwaltung.getMitgliedsNamen(personen_ID));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        descriptionText.setText("<html><p style=\"width: 600px;\">" + beschreibung + "</p>");

        jetztReservierenButton.addActionListener(e -> {
            try {
                Vereinssoftware.dienstleistungsverwaltung.angebotAnnehmen(angebots_ID, personen_ID, "1", 0);
                DefaultTextWithButton defaultTextWithButton = new DefaultTextWithButton("Anfrage senden?",
                        "Möchten Sie die Anfrage wirklich an " + Vereinssoftware.rollenverwaltung.getMitgliedsNamen(personen_ID) + " senden?", "Anfrage absenden");

                defaultTextWithButton.getActionButton().addActionListener(e1 -> {
                    try {
                        Vereinssoftware.dienstleistungsverwaltung.angebotAnnehmen(angebots_ID, personen_ID, Vereinssoftware.session.getID(), 10 /* TODO STUNDEN */);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });

                //frame.dispose();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }


}
