package client.gui.dienstleistungen.dienstleistungsangebote;
/*
@author
TODO Raphael Kleebaum
TODO Jonny Schlutter
TODO Gabriel Kleebaum
TODO Mhd Esmail Kanaan
TODO Gia Huy Hans Tran
TODO Ole Björn Adelmann
TODO Bastian Reichert
Dennis Kelm
*/

import client.DefaultsClient;
import client.Vereinssoftware;

import javax.swing.*;
import java.rmi.RemoteException;
import java.time.LocalDateTime;

//TODO Was macht diese Klasse?
public class DienstleistungsangebotAnzeigenGUI {
    private JPanel dienstleistungsgesuchAnzeigenGUI;
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
    //TODO Implementation Infos fetchen von IGeraeteverwaltung, vllt. die Infos per Parameter übergeben sondern hier fetchen
    public DienstleistungsangebotAnzeigenGUI(String angebots_ID, String titel, String pathToImage, String beschreibung, String kategorie, LocalDateTime ab, LocalDateTime bis, String personen_ID) {
        JFrame frame = new JFrame("Details des Dienstleistungsangebots");
        DefaultsClient.insertImageToPanel(imageLabel, pathToImage); //URL zum Bild, z.B. "https://bilder.gartenpaul.de/item/images/456/full/456-R1-M1.jpg"
        frame = DefaultsClient.standardizeFrame(frame, dienstleistungsgesuchAnzeigenGUI);

        //Anpassen der Texte
        headlineLabel.setText("TODO TITEL"); //sowas wie Vereinssoftware.dienstleistungsverwaltung.getGeraetInformation(String geraeteID)[0]
        metaInfoText1.setText("Kategorie: " + kategorie);
        metaInfoText2.setText("Verfügbar ab: " + ab.getDayOfMonth() + "." + ab.getMonth() + "." + ab.getYear());
        metaInfoText3.setText("Verfügbar bis: " + bis.getDayOfMonth() + "." + bis.getMonth() + "." + bis.getYear());
        metaInfoText4.setText("Angeboten von:  " + personen_ID); //TODO sowas wie personenID.getName

        descriptionText.setText("<html><p style=\"width: 600px;\">" + beschreibung + "</p>");

        jetztReservierenButton.addActionListener(e -> {
            try {
                Vereinssoftware.dienstleistungsverwaltung.angebotAnnehmen(angebots_ID, personen_ID, "P00002", 0);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }


}
