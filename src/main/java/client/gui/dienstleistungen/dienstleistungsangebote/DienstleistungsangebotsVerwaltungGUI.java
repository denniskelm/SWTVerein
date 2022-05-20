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

import client.ClientDefaults;
import client.Vereinssoftware;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//Erstellt das GUI für die Dienstleistungsangebote, wo Nutzer alle Angebote einsehen können (Verwaltung ist extra)
public class DienstleistungsangebotsVerwaltungGUI {
    private JPanel dienstleistungsangebotsverwaltungPanel;
    private JScrollPane bigTableScrollPanel;
    private JTable dienstleistungsangeboteTable;
    private JTextField suchenTextField;

    private Map<JTextField, Boolean> onceChanged = new HashMap<JTextField, Boolean>();

    public DienstleistungsangebotsVerwaltungGUI() {
        JFrame frame = new JFrame("Dienstleistungsangebots-Datenbank");
        try {
            this.generateTable();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //DienstleistungsangeboteGUI thisgui = new DienstleistungsangeboteGUI();
        frame = ClientDefaults.standardizeFrame(frame, this.dienstleistungsangebotsverwaltungPanel);
        frame.setLocationRelativeTo(null);

        ClientDefaults.enhanceTextField(suchenTextField, onceChanged);
    }

    private void generateTable() throws Exception {
        DefaultTableModel model = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                //macht Tabelle für den Nutzer unbearbeitbar
                return false;
            }
        };
        dienstleistungsangeboteTable.setModel(model);

        //set TableCellRenderer into a specified JTable column class

        String[] columns = new String[]{
                "ID",
                "Dienstleistung",
                "Beschreibung",
                "Kategorie",
                "Ab",
                "bis",
                "Anbieter",
                "Bild-URL"
        };
        ClientDefaults.createColumnsFromArray(columns, model);


        Object[][] angebote = (Object[][]) Vereinssoftware.dienstleistungsverwaltung.OmniAngebotDaten();


        for (Object[] angebot :
                angebote) {
            if (angebot[0] == null) {
                break;
            }

            LocalDateTime abTime = ((LocalDateTime) angebot[3]);
            String ab = abTime.format(DateTimeFormatter.ISO_LOCAL_DATE);

            LocalDateTime bisTime = ((LocalDateTime) angebot[4]);
            String bis = bisTime.format(DateTimeFormatter.ISO_LOCAL_DATE);

            model.addRow(new Object[]{
                    angebot[6],
                    angebot[0],
                    angebot[1],
                    angebot[2],
                    ab,
                    bis,
                    Vereinssoftware.rollenverwaltung.getMitgliedsNamen((String) angebot[5]),
                    angebot[7]
            });
        }


        dienstleistungsangeboteTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = dienstleistungsangeboteTable.rowAtPoint(evt.getPoint());
                int col = dienstleistungsangeboteTable.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    System.out.println(row + ", " + col);
                    //TODO Implementierung Klick auf Zelle
                    try {
                        DienstleistungsangebotBearbeitenGUI dienstleistungsangebotBearbeitenGUI = new DienstleistungsangebotBearbeitenGUI(
                                angebote[row][6].toString(), //ID
                                angebote[row][0].toString(), //Titel
                                angebote[row][7].toString(), //pathToImage
                                angebote[row][1].toString(), //beschreibung
                                angebote[row][2].toString(), //Kategorie
                                (LocalDateTime) angebote[row][3], //ab
                                (LocalDateTime) angebote[row][4] //bis
                        );
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }
}