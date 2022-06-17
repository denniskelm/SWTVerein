package client.gui.Geraete;
/*
@author
TODO Raphael Kleebaum
TODO Jonny Schlutter
TODO Gabriel Kleebaum
TODO Mhd Esmail Kanaan
TODO Gia Huy Hans Tran
TODO Ole Bjoern Adelmann
TODO Bastian Reichert
Dennis Kelm
*/

import client.ClientDefaults;
import client.Vereinssoftware;

import javax.swing.*;
import java.rmi.NoSuchObjectException;

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


    //Zeigt die Geraetedetailinformationen an - wird noch implementiert
    public GeraetAnzeigenGUI(String geraeteID, String pathToImage, String titel, String spender, String raum, int reservierungen, int leihfrist, String beschreibung, String kategorie) {
        JFrame frame = new JFrame("Geraetedetails");
        ClientDefaults.insertImageToPanel(imageLabel, pathToImage); //URL zum Bild, z.B. "https://bilder.gartenpaul.de/item/images/456/full/456-R1-M1.jpg"
        frame = ClientDefaults.standardizeFrame(frame, geraetAnzeigenGUI);

        //Anpassen der Texte
        headlineLabel.setText(titel);
        try {
            metaInfoText1.setText("Spender: " + Vereinssoftware.rollenverwaltung.getMitgliedsNamen(spender));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        metaInfoText2.setText("Raum: " + raum);
        metaInfoText3.setText("Aktuell " + reservierungen + " Reservierung(en)");
        metaInfoText4.setText("Leihfrist: " + leihfrist + " Tage");

        descriptionText.setText("<html><p style=\"width: 600px;\"> <b>Kategorie: " + kategorie + "</b><br/>" + beschreibung + "</p>");

        try {
            Vereinssoftware.session.getID();
        } catch (NoSuchObjectException e) {
            jetztReservierenButton.setEnabled(false);
        }

        jetztReservierenButton.addActionListener(e -> {
            try {
                GeraetReservierenGUI geraetReservierenGUI = new GeraetReservierenGUI(
                        geraeteID,
                        Vereinssoftware.session.getID());
            } catch (NoSuchObjectException ex) {
                throw new RuntimeException(ex);
            }

        });
    }


}
