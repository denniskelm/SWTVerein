package client.gui.dienstleistungen.dienstleistungsangebote;
/*
@author
Dennis Kelm
*/

import client.ClientDefaults;
import client.Kategorie;
import client.Umlaut;
import client.Vereinssoftware;
import client.gui.DefaultSmallPopup;
import client.gui.DefaultTextWithButton;

import javax.swing.*;
import java.rmi.NoSuchObjectException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

//Zeigt die GUI fuer ein Dienstleistungsangebot an mit zahlreichen Detailinformationen (und dem Bild)
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
    private JPanel stundenzahlPanel;
    private JTextField stundenzahlTextField;

    private static JFrame frame;

    private static Map<JTextField, Boolean> onceChanged = new HashMap<JTextField, Boolean>();

    //Zeigt die Details des Dienstleistungsangebots an
    public DienstleistungsangebotAnzeigenGUI(String angebots_ID, String titel, String pathToImage, String beschreibung, Kategorie kategorie, LocalDateTime ab, LocalDateTime bis, String personen_ID) {
        frame = new JFrame("Details des Dienstleistungsangebots");
        ClientDefaults.insertImageToPanel(imageLabel, pathToImage); //URL zum Bild, z.B. "https://bilder.gartenpaul.de/item/images/456/full/456-R1-M1.jpg"
        frame = ClientDefaults.standardizeFrame(frame, dienstleistungsangebotAnzeigenGUI);

        //Anpassen der Texte
        headlineLabel.setText(titel); //sowas wie Vereinssoftware.dienstleistungsverwaltung.getGeraetInformation(String geraeteID)[0]
        metaInfoText1.setText("Kategorie: " + kategorie);
        metaInfoText2.setText("Verfuegbar ab: " + ab.format(DateTimeFormatter.ISO_LOCAL_DATE));
        metaInfoText3.setText("Verfuegbar bis: " + bis.format(DateTimeFormatter.ISO_LOCAL_DATE));
        try {
            metaInfoText4.setText("Angeboten von:  " + Vereinssoftware.rollenverwaltung.getMitgliedsNamen(personen_ID));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            Vereinssoftware.session.getID();
        } catch (NoSuchObjectException e) {
            jetztReservierenButton.setEnabled(false);
        }


        ClientDefaults.enhanceTextField(stundenzahlTextField, onceChanged);

        descriptionText.setText("<html><p style=\"width: 600px;\">" + beschreibung + "</p>");

        jetztReservierenButton.addActionListener(e -> {
            frame.dispose();
            try {
                DefaultTextWithButton defaultTextWithButton = new DefaultTextWithButton("Anfrage senden?",
                        "M" + Umlaut.oe() + "chten Sie die Anfrage wirklich an " +
                                Vereinssoftware.rollenverwaltung.getMitgliedsNamen(personen_ID) + " senden?",
                        "Jetzt Anfrage absenden!");

                defaultTextWithButton.getActionButton().addActionListener(e1 -> {
                    try {
                        Vereinssoftware.dienstleistungsverwaltung.angebotAnnehmen(angebots_ID, personen_ID,
                                Vereinssoftware.session.getID(), Integer.parseInt(stundenzahlTextField.getText()));
                        defaultTextWithButton.closeFrame();
                    } catch (Exception ex) {
                        defaultTextWithButton.closeFrame();
                        DefaultSmallPopup errorPopup = new DefaultSmallPopup("Fehler bei der Anfrage!", "Es ist ein Fehler aufgetreten: " + ex);
                        throw new RuntimeException(ex);
                    }
                });

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }


}
