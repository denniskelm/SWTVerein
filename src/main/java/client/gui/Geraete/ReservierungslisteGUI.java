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
import client.Kategorie;
import client.Vereinssoftware;
import client.gui.Profilseite.Profilseite;
import client.gui.dienstleistungen.dienstleistungsangebote.DienstleistungsangebotAnzeigenGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//Erstellt das GUI fuer die Dienstleistungsangebote, wo Nutzer alle Angebote einsehen koennen (Verwaltung ist extra)
public class ReservierungslisteGUI {
    private JPanel dienstleistungsangebotePanel;
    private JScrollPane bigTableScrollPanel;
    private JTable reservierungslisteTable;
    private JTextField suchenTextField;

    private static JFrame frame;

    private final Map<JTextField, Boolean> onceChanged = new HashMap<JTextField, Boolean>();

    public ReservierungslisteGUI(String geraeteID) {
        frame = new JFrame("Alle Dienstleistungsangebote");

        try {
            this.generateTable(geraeteID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        frame = ClientDefaults.standardizeFrame(frame, this.dienstleistungsangebotePanel);
        frame.setLocationRelativeTo(null);

        ClientDefaults.enhanceTextField(suchenTextField, onceChanged);
    }

    private void generateTable(String geraeteID) throws Exception {

        DefaultTableModel model = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                //macht Tabelle fuer den Nutzer unbearbeitbar
                return false;
            }
        };
        reservierungslisteTable.setModel(model);

        String[] columns = new String[]{
                "Ausleiher",
                "Reservierdatum",
                "Fristbeginn",
                "Abgegeben?",
                "Vrsl. Endabgabe"
        };
        ClientDefaults.createColumnsFromArray(columns, model);

        Object[][] reservierungsliste = (Object[][]) Vereinssoftware.geraeteverwaltung.getGeraeteInformationen(geraeteID)[9];


        for (Object[] reservierung : reservierungsliste) {
            if (reservierung[0] == null)
                break;

            LocalDateTime fristbeginn = ((LocalDateTime) reservierung[2]);
            String fristbeginnText = fristbeginn.format(DateTimeFormatter.ISO_LOCAL_DATE);

            String reservierdatumText = fristbeginn.format(DateTimeFormatter.ISO_LOCAL_DATE);

            String abgegeben = "Nein";

            String abgabedatum = fristbeginn.plusDays(Long.parseLong(Vereinssoftware.geraeteverwaltung.getGeraeteInformationen(geraeteID)[5].toString()))
                    .format(DateTimeFormatter.ISO_LOCAL_DATE);

            if ((boolean) reservierung[3]) {
                abgegeben = "Ja";
            }

            model.addRow(new Object[]{
                    Vereinssoftware.rollenverwaltung.getMitgliedsNamen(reservierung[0].toString()),
                    reservierdatumText,
                    fristbeginnText,
                    abgegeben,
                    abgabedatum
            });
        }

        reservierungslisteTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = reservierungslisteTable.rowAtPoint(evt.getPoint());
                int col = reservierungslisteTable.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    if (col == 0) {
                        new Profilseite(reservierungsliste[row][0].toString());
                    }
                }
            }
        });

        //////////// SUCHE //////////////////////
        ClientDefaults.addSearchFunctionality(reservierungslisteTable, suchenTextField);
    }

}