package client.gui.dienstleistungen.dienstleistungsgesuche;
/*
@author
Dennis Kelm
Bastian Reichert
Ole Adelmann
Gabriel Kleebaum
*/

import client.ClientDefaults;
import client.Vereinssoftware;
import client.gui.DefaultSmallPopup;
import client.gui.DefaultTextWithButton;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

//TODO Was macht diese Klasse?
public class DienstleistungsgesuchAnzeigenGUI {
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
    private JPanel stundenzahlPanel;
    private JTextField stundenzahlTextField;

    private static Map<JTextField, Boolean> onceChanged = new HashMap<JTextField, Boolean>();

    //Zeigt die Details eines Dienstleistungsgesuchs an
    //TODO Implementation Infos fetchen von IGeraeteverwaltung, vllt. die Infos per Parameter uebergeben sondern hier fetchen
    public DienstleistungsgesuchAnzeigenGUI(String gesuch_ID, String pathToImage, String titel, String beschreibung, String kategorie, String suchenderID) {
        JFrame frame = new JFrame("Details des Dienstleistungsgesuchs");
        ClientDefaults.insertImageToPanel(imageLabel, pathToImage); //URL zum Bild, z.B. "https://bilder.gartenpaul.de/item/images/456/full/456-R1-M1.jpg"
        frame = ClientDefaults.standardizeFrame(frame, dienstleistungsgesuchAnzeigenGUI);
        frame.setLocationRelativeTo(null);

        ClientDefaults.enhanceTextField(stundenzahlTextField, onceChanged);


        //Anpassen der Texte
        headlineLabel.setText(titel); //sowas wie Vereinssoftware.dienstleistungsverwaltung.getGeraetInformation(String geraeteID)[0]
        metaInfoText1.setText("Kategorie: " + kategorie);
        metaInfoText2.setVisible(false);
        try {
            metaInfoText3.setText("Wird gesucht von: " + Vereinssoftware.rollenverwaltung.getMitgliedsNamen(suchenderID));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        metaInfoText4.setVisible(false);

        descriptionText.setText("<html><p style=\"width: 600px;\">" + beschreibung + "</p>");

        jetztReservierenButton.addActionListener(e -> {
            DefaultTextWithButton defaultTextWithButton = new DefaultTextWithButton(
                    "Dienstleistungsgesuch annehmen?",
                    "Moechtest du den Gesuch mit dem Titel " + titel + " wirklich annehmen?",
                    "Jetzt annehmen!");
            defaultTextWithButton.getActionButton().addActionListener(e1 -> {
                try {
                    Vereinssoftware.dienstleistungsverwaltung.gesuchAnnehmen(gesuch_ID, suchenderID, Vereinssoftware.session.getID(), Integer.parseInt(stundenzahlTextField.getText()));
                    DefaultSmallPopup defaultSmallPopup = new DefaultSmallPopup("Anfrage gesendet", "Sie haben die Anfrage erfolgreich gesendet!");
                } catch (Exception ex) {
                    DefaultSmallPopup errorPopup = new DefaultSmallPopup("Fehler bei der Anfrage!", "Es ist ein Fehler aufgetreten: " + ex);
                    throw new RuntimeException(ex);
                }
            });

        });
    }


}
