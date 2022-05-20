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

import client.DefaultsClient;
import client.Vereinssoftware;
import client.gui.DefaultSmallPopup;
import client.gui.dienstleistungen.dienstleistungsgesuche.DienstleistungsgesuchErstellenGUI;
import shared.communication.IDienstleistungsverwaltung;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
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

        frame = DefaultsClient.standardizeFrame(frame, this.dienstleistungsangebotePanel);

        DefaultsClient.enhanceTextField(suchenTextField, onceChanged);

        dienstleistungsangebotErstellenButton.addActionListener(e -> {
            DienstleistungsangebotErstellenGUI dienstleistungsangebotErstellenGUI = new DienstleistungsangebotErstellenGUI();
        });

        dienstleistungsgesuchErstellenButton.addActionListener(e -> {
            DienstleistungsgesuchErstellenGUI dienstleistungsgesuchErstellenGUI = new DienstleistungsgesuchErstellenGUI();
        });
    }

    private void generateTable() throws Exception /*throws RemoteException */ {

        IDienstleistungsverwaltung dienstleistungsverwaltung = null;

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
        DefaultsClient.createColumnsFromArray(columns, model);

        model.addRow(new Object[]{
                "Dienstleistung 1",
                "Beschreibung 1",
                "Kategorie 1",
                "07.05.2022",
                "14.05.2022",
                "Stefan"
        });

        Object[][] angebote = (Object[][]) Vereinssoftware.dienstleistungsverwaltung.OmniAngebotDaten();


        for (Object[] angebot :
                angebote) {
            LocalDateTime abTime = ((LocalDateTime) angebot[3]);
            String ab = abTime.getDayOfMonth() + "." + abTime.getMonthValue() + "." + abTime.getYear();

            LocalDateTime bisTime = ((LocalDateTime) angebot[4]);
            String bis = bisTime.getDayOfMonth() + "." + bisTime.getMonthValue() + "." + bisTime.getYear();

            model.addRow(new Object[]{
                    angebot[0],
                    angebot[1],
                    angebot[2],
                    ab,
                    bis,
                    Vereinssoftware.rollenverwaltung.getMitgliedsNamen((String) angebot[5])
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
                                "kaka", //ID
                                angebote[row][1].toString(), //Titel
                                angebote[row][1].toString(), //pathToImage
                                angebote[row][1].toString(), //beschreibung
                                angebote[row][1].toString(), //Kategorie
                                (LocalDateTime) angebote[row][1], //ab
                                (LocalDateTime) angebote[row][1], //bis
                                Vereinssoftware.rollenverwaltung.getMitgliedsNamen((String) angebote[row][5]) //PersonenID

                        );
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        //////////// SUCHE //////////////////////
        DefaultsClient.addSearchFunctionality(dienstleistungsangeboteTable, suchenTextField);
    }


}