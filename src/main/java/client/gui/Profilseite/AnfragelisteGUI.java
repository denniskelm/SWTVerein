package client.gui.Profilseite;
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
import client.Vereinssoftware;
import client.gui.dienstleistungen.dienstleistungsangebote.DienstleistungsangebotAnzeigenGUI;
import client.gui.dienstleistungen.dienstleistungsangebote.DienstleistungsangebotErstellenGUI;
import client.gui.dienstleistungen.dienstleistungsgesuche.DienstleistungsgesuchErstellenGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

//Erstellt das GUI für die Dienstleistungsangebote, wo Nutzer alle Angebote einsehen können (Verwaltung ist extra)
public class AnfragelisteGUI {
    private JPanel anfragelistePanel;
    private JScrollPane bigTableScrollPanel;
    private JTable anfragelisteTable;
    private JButton dienstleistungsgesuchErstellenButton;
    private JButton dienstleistungsangebotErstellenButton;
    private JTextField suchenTextField;

    private final Map<JTextField, Boolean> onceChanged = new HashMap<JTextField, Boolean>();

    //TODO Anpassung an Servercode
    public AnfragelisteGUI() {
        JFrame frame = new JFrame("Alle Dienstleistungsangebote");
        try {
            this.generateTable();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        frame = ClientDefaults.standardizeFrame(frame, this.anfragelistePanel);
        frame.setLocationRelativeTo(null);

        ClientDefaults.enhanceTextField(suchenTextField, onceChanged);

        dienstleistungsangebotErstellenButton.addActionListener(e -> {
            DienstleistungsangebotErstellenGUI dienstleistungsangebotErstellenGUI = new DienstleistungsangebotErstellenGUI();
        });

        dienstleistungsgesuchErstellenButton.addActionListener(e -> {
            DienstleistungsgesuchErstellenGUI dienstleistungsgesuchErstellenGUI = new DienstleistungsgesuchErstellenGUI();
        });
    }

    private void generateTable() throws Exception {

        DefaultTableModel model = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                //macht Tabelle für den Nutzer unbearbeitbar
                return false;
            }
        };
        anfragelisteTable.setModel(model);

        //set TableCellRenderer into a specified JTable column class

        String[] columns = new String[]{
                "Anfragetyp",
                "Beschreibung",
                "Kategorie",
                "Stunden",
                "ab",
                "bis",
                "Anfragesteller"
        };

        ClientDefaults.createColumnsFromArray(columns, model);

        Object[][] angebots_anfragen = Vereinssoftware.anfragenliste.omniAngebotsAnfrageDaten();
        Object[][] gesuchs_anfragen = Vereinssoftware.anfragenliste.omniGesuchsAnfrageDaten();

        Object[][] anfragen = new Object[angebots_anfragen.length + gesuchs_anfragen.length][];

        System.arraycopy(angebots_anfragen, 0, anfragen, 0, angebots_anfragen.length);
        System.arraycopy(gesuchs_anfragen, 0, anfragen, angebots_anfragen.length, gesuchs_anfragen.length);

        for (Object[] anfrage :
                anfragen) {
            if (anfrage[0] == null) {
                break;
            }
            LocalDateTime abTime = ((LocalDateTime) anfrage[3]);
            String ab = abTime.format(DateTimeFormatter.ISO_LOCAL_DATE);

            LocalDateTime bisTime = ((LocalDateTime) anfrage[4]);
            String bis = bisTime.format(DateTimeFormatter.ISO_LOCAL_DATE);

            model.addRow(new Object[]{
                    anfrage[0],
                    anfrage[1],
                    anfrage[2],
                    ab,
                    bis,
                    Vereinssoftware.rollenverwaltung.getMitgliedsNamen(anfrage[5].toString())
            });
        }


        anfragelisteTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = anfragelisteTable.rowAtPoint(evt.getPoint());
                int col = anfragelisteTable.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    System.out.println(row + ", " + col);
                    //TODO Implementierung Klick auf Zelle
                    try {
                        /*DienstleistungsangebotAnzeigenGUI dienstleistungsangebotAnzeigenGUI = new DienstleistungsangebotAnzeigenGUI(
                                //TODO

                        ); */
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