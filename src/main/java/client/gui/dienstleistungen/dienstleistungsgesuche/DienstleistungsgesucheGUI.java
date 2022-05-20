package client.gui.dienstleistungen.dienstleistungsgesuche;
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
import client.gui.DefaultSmallPopup;
import client.gui.dienstleistungen.dienstleistungsangebote.DienstleistungsangebotErstellenGUI;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Map;

//Erstellt das GUI für die Dienstleistungsgesuche, wo Nutzer alle Gesuche einsehen können (Verwaltung ist extra)
public class DienstleistungsgesucheGUI {
    private JPanel dienstleistungsgesuchePanel;
    private JScrollPane bigTableScrollPanel;
    private JTable dienstleistungsgesucheTable;
    private JButton dienstleistungsgesuchErstellenButton;
    private JButton dienstleistungsangebotErstellenButton;
    private JTextField suchenTextField;

    private Map<JTextField, Boolean> onceChanged = new HashMap<JTextField, Boolean>();

    public DienstleistungsgesucheGUI() {
        JFrame frame = new JFrame("Alle Dienstleistungsgesuche");
        this.generateTable();
        //DienstleistungsangeboteGUI thisgui = new DienstleistungsangeboteGUI();
        frame = ClientDefaults.standardizeFrame(frame, this.dienstleistungsgesuchePanel);

        ClientDefaults.enhanceTextField(suchenTextField, onceChanged);

        dienstleistungsangebotErstellenButton.addActionListener(e -> {
            DienstleistungsangebotErstellenGUI dienstleistungsangebotErstellenGUI = new DienstleistungsangebotErstellenGUI();
        });

        dienstleistungsgesuchErstellenButton.addActionListener(e -> {
            DienstleistungsgesuchErstellenGUI dienstleistungsgesuchErstellenGUI = new DienstleistungsgesuchErstellenGUI();
        });
    }

    private void generateTable() {
        DefaultTableModel model = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                //macht Tabelle für den Nutzer unbearbeitbar
                return false;
            }
        };
        dienstleistungsgesucheTable.setModel(model);

        //set TableCellRenderer into a specified JTable column class

        String[] columns = new String[]{
                "Gesuch",
                "Beschreibung",
                "Kategorie",
                "Anbieter"
        };
        ClientDefaults.createColumnsFromArray(columns, model);

        model.addRow(new Object[]{
                "Gesuch 1",
                "Beschreibung 1",
                "Kategorie 1",
                "Dieter"
        });

        dienstleistungsgesucheTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = dienstleistungsgesucheTable.rowAtPoint(evt.getPoint());
                int col = dienstleistungsgesucheTable.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    System.out.println(row + ", " + col);
                    //TODO Implementierung Klick auf Zelle
                    DefaultSmallPopup smallPopup = new DefaultSmallPopup("Test test", "TODO");
                }
            }
        });
    }


    public static void main(String[] args) {
        //TODO Am Ende entfernen
        FlatLightLaf.setup();

    }
}