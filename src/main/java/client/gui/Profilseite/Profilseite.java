package client.gui.Profilseite;
/*
@author
Dennis Kelm
*/

import client.ClientDefaults;
import client.Einstellungen;
import client.Umlaut;
import client.Vereinssoftware;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

//Zeigt die Profilseite eines Nutzers an (nicht unbedingt die eigene)
//mit zahlreichen Informationen (insbesondere die Gesuche, Angebote und gespendeten Geraete)
public class Profilseite {
    private JPanel namePanel;
    private JLabel nameText;
    private JButton XXButton;
    private JPanel stundenkontoPanel;
    private JLabel stundenkontostandText;
    private JPanel emailPanel;
    private JLabel emailText;
    private JPanel eigeneprofilseitePanel;
    private JPanel mahnungenPanel;
    private JLabel mahnungenText;
    private JTable profilseiteEintraegeTable;
    private JScrollPane profilseiteEintraegeScrollPanel;
    private JLabel spenderText;

    public Profilseite(String personenID) {
        boolean ownProfilseite;
        try {
            ownProfilseite = Objects.equals(personenID, Vereinssoftware.session.getID());
        } catch (NoSuchObjectException e) {
            throw new RuntimeException(e);
        }

        //Spendertext anzeigen
        try {
            spenderText.setVisible(Vereinssoftware.rollenverwaltung.istSpender(personenID));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        if (ownProfilseite) {
            XXButton.setText("Anfrageliste");
            XXButton.addActionListener(e -> {
                new AnfragelisteGUI();
            });
            nameText.setText("Ihnen");
        } else {
            //Namen des Mitglieds der Profilseite anzeigen
            try {
                nameText.setText(Vereinssoftware.rollenverwaltung.getMitgliedsNamen(personenID));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            //Kontaktfunktion -> siehe Annahmen im Pflichtenheft
            XXButton.setText("Kontaktieren");
            XXButton.addActionListener(e -> {
                Desktop desktop;
                if (Desktop.isDesktopSupported()
                        && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
                    try {
                        String link = "mailto:" + Vereinssoftware.rollenverwaltung.getMitgliedsMail(personenID) +
                                "?subject=Kontaktanfrage%20aus%20dem%20Verein&body=Hallo%20MITGLIEDSNAME%2C%0D%0A%0D" +
                                "%0A%0D%0AMit%20freundlichen%20Gr%C3%BC%C3%9Fen%0D%0A";// + Vereinssoftware.rollenverwaltung.getMitgliedsNamen(Vereinssoftware.session.getID()))
                        URI mailto = new URI(link);
                        desktop.mail(mailto);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    throw new RuntimeException("Dieses Geraet hat keine Mail-Funktion!");
                }
            });
        }

        //E-Mail richtig setzen
        try {
            emailText.setText(Vereinssoftware.rollenverwaltung.getMitgliedsMail(personenID));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Stundenzahl richtig setzen
        try {
            stundenkontostandText.setText(Vereinssoftware.rollenverwaltung.getStundenzahl(personenID) + " Stunden");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Mahnungen richtig setzen

        try {
            mahnungenText.setText(Vereinssoftware.rollenverwaltung.anzahlMahnungenVonNutzer(Vereinssoftware.session.getID()) + " Mahnungen");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        JFrame frame = new JFrame("Profilseite");
        try {
            generateTable(personenID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        frame = ClientDefaults.standardizeFrame(frame, eigeneprofilseitePanel);
    }

    private void generateTable(String personenID) throws RuntimeException, RemoteException {
        DefaultTableModel model = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                //macht Tabelle fuer den Nutzer unbearbeitbar
                return false;
            }
        };

        profilseiteEintraegeTable.setModel(model);

        String[] columns = new String[]{
                "Typ",
                "Titel",
                "Beschreibung",
                "Kategorie",
                "Verfuegbar ab",
                "Verfuegbar bis",
                "Leihfrist",
                "Status"
        };
        ClientDefaults.createColumnsFromArray(columns, model);

        int j = 0;

        ///////// ANGEBOTE ///////////////////////////////////////////////////////////////////////////
        Object[][] angebote = Vereinssoftware.dienstleistungsverwaltung.omniAngebotDaten();
        if (angebote.length != 0) {
            Object[][] angeboteOfUser = new Object[angebote.length][angebote[0].length];
            j = 0;
            for (Object[] angebot : angebote) {
                if (angebot[5] == null) {
                    break;
                }

                if (Objects.equals(angebot[5].toString(), personenID)) {
                    angeboteOfUser[j] = angebot;
                    j++;
                }
            }

            for (Object[] angebot : angeboteOfUser) {
                if (angebot[0] == null)
                    break;

                LocalDateTime abTime = ((LocalDateTime) angebot[3]);
                String ab = abTime.format(DateTimeFormatter.ISO_LOCAL_DATE);

                LocalDateTime bisTime = ((LocalDateTime) angebot[4]);
                String bis = bisTime.format(DateTimeFormatter.ISO_LOCAL_DATE);

                model.addRow(new Object[]{
                        "Angebot",
                        angebot[0],
                        angebot[1],
                        angebot[2],
                        ab,
                        bis,
                        "-",
                        "-"
                });
            }
        }


        ///////// GESUCHE ///////////////////////////////////////////////////////////////////////////
        Object[][] gesuche = Vereinssoftware.dienstleistungsverwaltung.omniGesuchDaten();

        if (gesuche.length != 0) {
            Object[][] gesucheOfUser = new Object[gesuche.length][gesuche[0].length];
            j = 0;
            for (Object[] gesuch : gesuche) {
                if (gesuch[4] == null) {
                    break;
                }

                if (Objects.equals(gesuch[4].toString(), personenID)) {
                    for (int i = 0; i < gesuch.length; i++) {
                        gesucheOfUser[j][i] = gesuch[i];
                    }
                    j++;
                }
            }

            for (Object[] gesuch : gesucheOfUser) {
                if (gesuch[0] == null)
                    break;

                model.addRow(new Object[]{
                        "Gesuch",
                        gesuch[0],
                        gesuch[1],
                        gesuch[2],
                        "-",
                        "-",
                        "-",
                        "-"
                });
            }
        }


        ///////// GERAETE ///////////////////////////////////////////////////////////////////////////
        Object[][] geraete = Vereinssoftware.geraeteverwaltung.omniGeraeteDaten();

        if (geraete.length == 0) {
            Object[][] geraeteOfUser = new Object[geraete.length][geraete[0].length];
            j = 0;
            for (Object[] geraet : geraete) {
                if (geraet[4] == null) {
                    break;
                }

                if (Objects.equals(geraet[4].toString(), personenID)) {
                    geraeteOfUser[j] = geraet;
                    j++;
                }
            }

            for (Object[] geraet : geraeteOfUser) {
                if (geraet[0] == null)
                    break;

                model.addRow(new Object[]{
                        "Ger" + Umlaut.ae() + "t",
                        geraet[1],
                        geraet[2],
                        geraet[3],
                        "-",
                        "-",
                        geraet[5],
                        geraet[6],
                });
            }
        }

    }
}
