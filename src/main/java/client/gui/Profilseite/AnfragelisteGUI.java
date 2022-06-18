package client.gui.Profilseite;
/*
@author
Dennis Kelm
*/

import client.ClientDefaults;
import client.Kategorie;
import client.Umlaut;
import client.Vereinssoftware;
import client.gui.DefaultSmallPopup;
import client.gui.dienstleistungen.dienstleistungsangebote.DienstleistungsangebotAnzeigenGUI;
import client.gui.dienstleistungen.dienstleistungsangebote.DienstleistungsangebotErstellenGUI;
import client.gui.dienstleistungen.dienstleistungsgesuche.DienstleistungsgesuchAnzeigenGUI;
import client.gui.dienstleistungen.dienstleistungsgesuche.DienstleistungsgesuchErstellenGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

//Erstellt das GUI fuer die Anfrageliste des gerade angemeldeten Nutzers
public class AnfragelisteGUI {
    private JPanel anfragelistePanel;
    private JScrollPane bigTableScrollPanel;
    private JTable anfragelisteTable;
    private JTextField suchenTextField;
    private JLabel infoText;

    private static JFrame frame;

    private final Map<JTextField, Boolean> onceChanged = new HashMap<JTextField, Boolean>();

    public AnfragelisteGUI() {
        frame = new JFrame("Alle Anfragen");
        try {
            this.generateTable();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        infoText.setText("<html><b>Tipp:</b> Klicken Sie in der Anfragenliste auf die W" + Umlaut.oe() + "rter <b>\"Annehmen\"</b> bzw. <b>\"Ablehnen\"</b>");

        frame = ClientDefaults.standardizeFrame(frame, this.anfragelistePanel);

        ClientDefaults.enhanceTextField(suchenTextField, onceChanged);
    }

    private void generateTable() throws Exception {

        DefaultTableModel model = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                //macht Tabelle fuer den Nutzer unbearbeitbar
                return false;
            }
        };
        anfragelisteTable.setModel(model);

        //set TableCellRenderer into a specified JTable column class
        String[] columns = new String[]{
                "Anfragetyp",
                "Titel",
                "Beschreibung",
                "Kategorie",
                "Stunden",
                "ab",
                "bis",
                "Anfragesteller",
                "",
                ""
        };

        ClientDefaults.createColumnsFromArray(columns, model);

        Object[][] angebots_anfragen = Vereinssoftware.anfragenVerwaltung.omniAngebotsAnfrageDaten(Vereinssoftware.session.getID());
        Object[][] gesuchs_anfragen = Vereinssoftware.anfragenVerwaltung.omniGesuchsAnfrageDaten(Vereinssoftware.session.getID());

        Object[][] anfragen = new Object[angebots_anfragen.length + gesuchs_anfragen.length][];

        System.arraycopy(angebots_anfragen, 0, anfragen, 0, angebots_anfragen.length);
        System.arraycopy(gesuchs_anfragen, 0, anfragen, angebots_anfragen.length, gesuchs_anfragen.length);

        int i = 0;
        for (Object[] anfrage :
                anfragen) {

            System.out.println("i " + i);

            if (anfrage[0] == null) {
                break;
            }

            if (i < angebots_anfragen.length) {

                LocalDateTime abTime = ((LocalDateTime) anfrage[6]);
                String ab = abTime.format(DateTimeFormatter.ISO_LOCAL_DATE);

                LocalDateTime bisTime = ((LocalDateTime) anfrage[7]);
                String bis = bisTime.format(DateTimeFormatter.ISO_LOCAL_DATE);

                model.addRow(new Object[]{
                        "Dienstleistungsangebot",
                        anfrage[3],
                        anfrage[4],
                        anfrage[5],
                        anfrage[2],
                        ab,
                        bis,
                        Vereinssoftware.rollenverwaltung.getMitgliedsNamen(anfrage[0].toString()),
                        "Annehmen",
                        "Ablehnen"
                });
            } else {
                model.addRow(new Object[]{
                        "Dienstleistungsgesuch",
                        anfrage[3],
                        anfrage[4],
                        anfrage[5],
                        anfrage[2],
                        "-",
                        "-",
                        Vereinssoftware.rollenverwaltung.getMitgliedsNamen(anfrage[0].toString()),
                        "Annehmen",
                        "Ablehnen"
                });
            }


            i++;
        }

        anfragelisteTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = anfragelisteTable.rowAtPoint(evt.getPoint());
                int col = anfragelisteTable.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {

                    try {
                        if (row < angebots_anfragen.length) {
                            if (col < 7) {
                                LocalDateTime abTime = ((LocalDateTime) anfragen[row][6]);

                                LocalDateTime bisTime = ((LocalDateTime) anfragen[row][7]);
                                DienstleistungsangebotAnzeigenGUI dienstleistungsangebotAnzeigenGUI =
                                        new DienstleistungsangebotAnzeigenGUI(
                                                anfragen[row][1].toString(),
                                                anfragen[row][3].toString(),
                                                anfragen[row][8].toString(),
                                                anfragen[row][4].toString(),
                                                Kategorie.valueOf(anfragen[row][5].toString()),
                                                abTime,
                                                bisTime,
                                                anfragen[row][0].toString());
                            } else if (col == 7) {
                                //Profilseite
                                new Profilseite(anfragen[row][0].toString());
                            } else if (col == 8) {
                                //Annehmen mit DefaultSmallPopup
                                Vereinssoftware.anfragenVerwaltung.aAnfrageAnnehmen(Vereinssoftware.session.getID(), anfragen[row][9].toString());
                                frame.dispose();
                                new AnfragelisteGUI();
                                new DefaultSmallPopup("Anfrage erfolgreich angenommen!", "Ihre Anfrage wurde nun angenommen!");
                            } else if (col == 9) {
                                //Ablehnen mit DefaultSmallPopup
                                Vereinssoftware.anfragenVerwaltung.removeAAnfrage(Vereinssoftware.session.getID(), anfragen[row][9].toString());
                                frame.dispose();
                                new AnfragelisteGUI();
                                new DefaultSmallPopup("Anfrage erfolgreich abgelehnt!", "Die Anfrage wurde erfolgreich abgelehnt!");
                            }
                        } else {
                            if (col < 7) {
                                new DienstleistungsgesuchAnzeigenGUI(
                                        anfragen[row][1].toString(),
                                        anfragen[row][3].toString(),
                                        anfragen[row][8].toString(),
                                        anfragen[row][4].toString(),
                                        Kategorie.valueOf(anfragen[row][5].toString()),
                                        anfragen[row][0].toString());
                            } else if (col == 7)
                                //Profilseite
                                new Profilseite(anfragen[row][0].toString());
                            else if (col == 8) {
                                //Annehmen mit DefaultSmallPopup
                                Vereinssoftware.anfragenVerwaltung.gAnfrageAnnehmen(Vereinssoftware.session.getID(), anfragen[row][7].toString());
                                frame.dispose();
                                new AnfragelisteGUI();
                                new DefaultSmallPopup("Gesuch erfolgreich angenommen!", "Ihre Gesuch wurde nun angenommen!");
                            } else if (col == 9) {
                                //Ablehnen mit DefaultSmallPopup
                                Vereinssoftware.anfragenVerwaltung.removeGAnfrage(Vereinssoftware.session.getID(), anfragen[row][7].toString());
                                frame.dispose();
                                new AnfragelisteGUI();
                                new DefaultSmallPopup("Gesuch erfolgreich abgelehnt!", "Die Anfrage wurde erfolgreich abgelehnt!");
                            }
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        //////////// SUCHE //////////////////////
        ClientDefaults.addSearchFunctionality(anfragelisteTable, suchenTextField);
    }


}