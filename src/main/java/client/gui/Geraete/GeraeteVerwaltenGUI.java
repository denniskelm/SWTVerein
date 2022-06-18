package client.gui.Geraete;

import client.ClientDefaults;
import client.Umlaut;
import client.Vereinssoftware;
import client.gui.DefaultSmallPopup;
import server.geraetemodul.Geraetedaten;
import shared.communication.IAusleiher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class GeraeteVerwaltenGUI {
    private JPanel GeraetVerwalten;
    private JTextField geraetnameTextField;
    private JTextField spenderTextField;
    private JTextField kategorieTextField;
    private JTextField geraetbeschreibungTextField;
    private JTextField leihfristTextField;
    private JTextField aktuellerEntleiherTextField;
    private JTextField verbliebendeLeihdauerTextField;
    private JTextField ausgabeortTextField;
    private JTextField bildTextField;
    private JLabel kategorieLabel;
    private JLabel spenderLabel;
    private JLabel nameLabel;
    private JLabel beschreibungLabel;
    private JLabel leihfristLabel;
    private JLabel entleiherLabel;
    private JLabel leihdauerLabel;
    private JLabel ausgabeortLabel;
    private JLabel bildLabel;
    private JButton reservierungslisteAnzeigenButton;
    private JButton verleihhistorieButton;
    private JButton speichernButton;
    private JButton geraetAusgebenButton;
    private JButton geraetLoeschenButton;
    private final String geraeteID;
    private Object[] geraeteInfos;

    private static JFrame frame;

    public GeraeteVerwaltenGUI(String geraeteID) {

        frame = new JFrame("Ger" + Umlaut.ae() + "te verwalten");
        frame = ClientDefaults.standardizeFrame(frame, GeraetVerwalten);

        this.geraeteID = geraeteID;

        geraetLoeschenButton.setText("Ger" + Umlaut.ae() + "t l" + Umlaut.oe() + "schen");

        try {
            Object[][] geraeteDaten = Vereinssoftware.geraeteverwaltung.omniGeraeteDaten();

            for (Object[] g : geraeteDaten)
                if (g[0].equals(geraeteID)) {
                    geraeteInfos = g;
                    break;
                }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        String ausleiherName = null, ausleihDauer = null;

        try {
            Object[][] resListe = (Object[][]) geraeteInfos[9];

            if (resListe.length > 0) {
                ausleiherName = Vereinssoftware.rollenverwaltung.getMitgliedsNamen((String) resListe[0][0]);
                LocalDateTime ausgeliehenBis = ((LocalDateTime) resListe[0][2]).plusDays(Long.parseLong(geraeteInfos[5].toString()));
                ausleihDauer = LocalDateTime.now().until(ausgeliehenBis, ChronoUnit.DAYS) + " Tage";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        bildTextField.setText(String.valueOf(geraeteInfos[10]));
        geraetnameTextField.setText(String.valueOf(geraeteInfos[1]));
        spenderTextField.setText(String.valueOf(geraeteInfos[4]));
        kategorieTextField.setText(String.valueOf(geraeteInfos[3]));
        geraetbeschreibungTextField.setText(String.valueOf(geraeteInfos[2]));
        leihfristTextField.setText(geraeteInfos[5].toString());
        ausgabeortTextField.setText(String.valueOf(geraeteInfos[7]));
        aktuellerEntleiherTextField.setText(ausleiherName);
        verbliebendeLeihdauerTextField.setText(ausleihDauer);

        reservierungslisteAnzeigenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new ReservierungslisteGUI(geraeteID);
            }
        });

        speichernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    if (!geraetnameTextField.getText().equals(geraeteInfos[1]))
                        Vereinssoftware.geraeteverwaltung.geraeteDatenVerwalten(geraeteID, Geraetedaten.NAME, geraetnameTextField.getText());
                    if (!spenderTextField.getText().equals(geraeteInfos[4]))
                        Vereinssoftware.geraeteverwaltung.geraeteDatenVerwalten(geraeteID, Geraetedaten.SPENDERNAME, spenderTextField.getText());
                    if (!kategorieTextField.getText().equals(geraeteInfos[3]))
                        Vereinssoftware.geraeteverwaltung.geraeteDatenVerwalten(geraeteID, Geraetedaten.KATEGORIE, kategorieTextField.getText());
                    if (!geraetbeschreibungTextField.getText().equals(geraeteInfos[2]))
                        Vereinssoftware.geraeteverwaltung.geraeteDatenVerwalten(geraeteID, Geraetedaten.BESCHREIBUNG, geraetbeschreibungTextField.getText());
                    if (!leihfristTextField.getText().equals(geraeteInfos[5]))
                        Vereinssoftware.geraeteverwaltung.geraeteDatenVerwalten(geraeteID, Geraetedaten.LEIHFRIST, Integer.parseInt(leihfristTextField.getText()));
                    if (!ausgabeortTextField.getText().equals(geraeteInfos[7]))
                        Vereinssoftware.geraeteverwaltung.geraeteDatenVerwalten(geraeteID, Geraetedaten.ABHOLORT, ausgabeortTextField.getText());
                    if (!bildTextField.getText().equals(geraeteInfos[10]))
                        Vereinssoftware.geraeteverwaltung.geraeteDatenVerwalten(geraeteID, Geraetedaten.BILD, bildTextField.getText());

                    frame.dispose();
                    new DefaultSmallPopup("Daten erfolgreich ge" + Umlaut.ae() + "ndert!",
                            "Ihre Daten des Ger" + Umlaut.ae() + "ts \"" + geraetnameTextField.getText() + "\" wurden erfolgreich ge" + Umlaut.ae() + "ndert!");

                } catch (RemoteException ex) {
                    new DefaultSmallPopup("Fehler",
                            "Ihre Daten des Ger" + Umlaut.ae() + "ts \"" + geraetnameTextField.getText() + "\" wurden nicht ge" + Umlaut.ae() + "ndert, wegen des Fehlers: " + ex);
                    throw new RuntimeException(ex);
                }

            }
        });

        geraetAusgebenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Vereinssoftware.geraeteverwaltung.geraetAusgeben(geraeteID);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        verleihhistorieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new VerleihhistorieGUI(geraeteID);
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        geraetLoeschenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Vereinssoftware.geraeteverwaltung.geraetEntfernen(geraeteID);
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}

