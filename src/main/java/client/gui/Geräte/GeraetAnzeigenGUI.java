package client.gui.Geräte;
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

import client.ClientDefaults;

import javax.swing.*;

//TODO Was macht diese Klasse?
public class GeraetAnzeigenGUI {
    private JPanel geraetAnzeigenGUI;
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


    //Zeigt die Gerätedetailinformationen an
    //TODO Implementation Infos fetchen von IGeraeteverwaltung, vllt. die Infos per Parameter übergeben sondern hier fetchen
    public GeraetAnzeigenGUI(String geraeteID, String pathToImage, String titel, String spender, String raum, int reservierungen, int leihfrist, String beschreibung) {
        JFrame frame = new JFrame("Gerätedetails");
        ClientDefaults.insertImageToPanel(imageLabel, pathToImage); //URL zum Bild, z.B. "https://bilder.gartenpaul.de/item/images/456/full/456-R1-M1.jpg"
        frame = ClientDefaults.standardizeFrame(frame, geraetAnzeigenGUI);

        //Anpassen der Texte
        headlineLabel.setText("TODO TITEL"); //sowas wie Vereinssoftware.dienstleistungsverwaltung.getGeraetInformation(String geraeteID)[0]
        metaInfoText1.setText("Spender: " + spender);
        metaInfoText2.setText("Raum: " + raum);
        metaInfoText3.setText("Aktuell " + reservierungen + " Reservierungen");
        metaInfoText4.setText("Leihfrist:  " + leihfrist + " Tage");

        descriptionText.setText("<html><p style=\"width: 600px;\">" + beschreibung + "</p>");

        jetztReservierenButton.addActionListener(e -> {
            //TODO Implemtierung Reservierung hinzufügen
        });
    }


}
