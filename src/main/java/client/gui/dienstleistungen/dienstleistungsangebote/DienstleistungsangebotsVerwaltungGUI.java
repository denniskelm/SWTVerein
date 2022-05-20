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
import client.gui.DefaultSmallPopup;
import client.gui.dienstleistungen.dienstleistungsgesuche.DienstleistungsgesuchErstellenGUI;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
        this.generateTable();
        //DienstleistungsangeboteGUI thisgui = new DienstleistungsangeboteGUI();
        frame = DefaultsClient.standardizeFrame(frame, this.dienstleistungsangebotsverwaltungPanel);

        DefaultsClient.enhanceTextField(suchenTextField, onceChanged);
    }

    private void generateTable() {
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
                "Datum",
                "Anbieter"
        };
        DefaultsClient.createColumnsFromArray(columns, model);

        model.addRow(new Object[]{
                "D00001",
                "Dienstleistung 1",
                "Beschreibung 1",
                "Kategorie 1",
                "07.05.2022 - 14.05.2022",
                "Stefan"
        });

        dienstleistungsangeboteTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = dienstleistungsangeboteTable.rowAtPoint(evt.getPoint());
                int col = dienstleistungsangeboteTable.columnAtPoint(evt.getPoint());
                //TODO row ist ID in der Tabelle
                if (row >= 0 && col >= 0) {
                    System.out.println(row + ", " + col);
                    //TODO Implementierung Klick auf Zelle
                    DienstleistungsangebotBearbeitenGUI dienstleistungsangebotBearbeitenGUI = new DienstleistungsangebotBearbeitenGUI();
                }
            }
        });
    }


    public static void main(String[] args) {
        //TODO Am Ende entfernen
        FlatLightLaf.setup();

    }
}