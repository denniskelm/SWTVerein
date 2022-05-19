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
public class DienstleistungsgesuchAnzeigenGUI {
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


    //Zeigt die Details eines Dienstleistungsgesuchs an
    //TODO Implementation Infos fetchen von IGeraeteverwaltung, vllt. die Infos per Parameter übergeben sondern hier fetchen
    public DienstleistungsgesuchAnzeigenGUI(String gesuch_ID, String pathToImage, String titel, String beschreibung, String kategorie, String suchenderID) {
        JFrame frame = new JFrame("Details des Dienstleistungsangebots");
        DefaultsClient.insertImageToPanel(imageLabel, pathToImage); //URL zum Bild, z.B. "https://bilder.gartenpaul.de/item/images/456/full/456-R1-M1.jpg"
        frame = DefaultsClient.standardizeFrame(frame, dienstleistungsangebotAnzeigenGUI);

        //Anpassen der Texte
        headlineLabel.setText("TODO TITEL"); //sowas wie Vereinssoftware.dienstleistungsverwaltung.getGeraetInformation(String geraeteID)[0]
        metaInfoText1.setText("Kategorie: " + kategorie);
        frame.remove(metaInfoText2);
        metaInfoText3.setText("Wird gesucht von: " + suchenderID); //TODO so was wie suchenderID.getName
        frame.remove(metaInfoText4);

        descriptionText.setText("<html><p style=\"width: 600px;\">" + beschreibung + "</p>");

        jetztReservierenButton.addActionListener(e -> {
            try { //TODO Ist das Gesuch annehmen?
                Vereinssoftware.dienstleistungsverwaltung.gesuchAnnehmen(gesuch_ID, suchenderID, "P00002" /* TODO SESSION */, 10 /* TODO STUNDEN */);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }


}
