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
import client.gui.dienstleistungen.dienstleistungsgesuche.DienstleistungsgesuchErstellenGUI;
import shared.communication.IDienstleistungsverwaltung;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

//Erstellt das GUI für die Dienstleistungsangebote, wo Nutzer alle Angebote einsehen können (Verwaltung ist extra)
public class DienstleistungsangeboteGUI {
    private JPanel dienstleistungsangebotePanel;
    private JScrollPane bigTableScrollPanel;
    private JTable dienstleistungsangeboteTable;
    private JButton dienstleistungsgesuchErstellenButton;
    private JButton dienstleistungsangebotErstellenButton;
    private JTextField suchenTextField;

    private Map<JTextField, Boolean> onceChanged = new HashMap<JTextField, Boolean>();

    public DienstleistungsangeboteGUI() {
        JFrame frame = new JFrame("Alle Dienstleistungsangebote");
        //try {
        try {
            this.generateTable();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //} catch (RemoteException e) {
        //    throw new RuntimeException(e);
        //}

        frame = ClientDefaults.standardizeFrame(frame, this.dienstleistungsangebotePanel);
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
        dienstleistungsangeboteTable.setModel(model);

        //set TableCellRenderer into a specified JTable column class

        String[] columns = new String[]{
                "Dienstleistung",
                "Beschreibung",
                "Kategorie",
                "Verfügbar ab",
                "Verfügbar bis",
                "Anbieter"
        };
        ClientDefaults.createColumnsFromArray(columns, model);

        Object[][] angebote = Vereinssoftware.dienstleistungsverwaltung.OmniAngebotDaten();


        for (Object[] angebot :
                angebote) {
            if (angebot[0] == null) {
                break;
            }

            System.out.println("Kaka: " + angebot[0] + angebot[1] + angebot[2] + angebot[3] + angebot[4] + angebot[5]);

            LocalDateTime abTime = ((LocalDateTime) angebot[3]);
            String ab = abTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
            ;

            LocalDateTime bisTime = ((LocalDateTime) angebot[4]);
            String bis = bisTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
            ;

            model.addRow(new Object[]{
                    angebot[0],
                    angebot[1],
                    angebot[2],
                    ab,
                    bis,
                    Vereinssoftware.rollenverwaltung.getMitgliedsNamen(angebot[5].toString())
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
                        DienstleistungsangebotAnzeigenGUI dienstleistungsangebotAnzeigenGUI = new DienstleistungsangebotAnzeigenGUI(
                                angebote[row][6].toString(), //ID
                                angebote[row][0].toString(), //Titel
                                angebote[row][7].toString(), //pathToImage
                                angebote[row][1].toString(), //beschreibung
                                angebote[row][2].toString(), //Kategorie
                                (LocalDateTime) angebote[row][3], //ab
                                (LocalDateTime) angebote[row][4], //bis
                                (String) angebote[row][5] //PersonenID

                        );
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        //////////// SUCHE //////////////////////
        ClientDefaults.addSearchFunctionality(dienstleistungsangeboteTable, suchenTextField);
    }


}