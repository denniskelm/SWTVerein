package client.gui.dienstleistungen.dienstleistungsgesuche;
/*
@author
Dennis Kelm
*/

import client.ClientDefaults;
import client.Vereinssoftware;
import client.gui.DefaultSmallPopup;
import client.gui.dienstleistungen.dienstleistungsangebote.DienstleistungsangebotAnzeigenGUI;
import client.gui.dienstleistungen.dienstleistungsangebote.DienstleistungsangebotErstellenGUI;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

        try {
            this.generateTable();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        frame = ClientDefaults.standardizeFrame(frame, this.dienstleistungsgesuchePanel);
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
        dienstleistungsgesucheTable.setModel(model);

        //set TableCellRenderer into a specified JTable column class

        String[] columns = new String[]{
                "Dienstleistung",
                "Beschreibung",
                "Kategorie",
                "Suchender"
        };
        ClientDefaults.createColumnsFromArray(columns, model);

        Object[][] gesuche = Vereinssoftware.dienstleistungsverwaltung.OmniGesuchDaten();


        for (Object[] gesuch :
                gesuche) {
            if (gesuch[0] == null) {
                break;
            }

            model.addRow(new Object[]{
                    gesuch[0],
                    gesuch[1],
                    gesuch[2],
                    Vereinssoftware.rollenverwaltung.getMitgliedsNamen(gesuch[4].toString())
            });
        }


        dienstleistungsgesucheTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = dienstleistungsgesucheTable.rowAtPoint(evt.getPoint());
                int col = dienstleistungsgesucheTable.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    System.out.println(row + ", " + col);
                    //TODO Implementierung Klick auf Zelle
                    try {
                        DienstleistungsgesuchAnzeigenGUI dienstleistungsgesuchAnzeigenGUI = new DienstleistungsgesuchAnzeigenGUI(
                                gesuche[row][3].toString(), //ID
                                gesuche[row][5].toString(), //pathToImage
                                gesuche[row][0].toString(), //Titel
                                gesuche[row][1].toString(), //beschreibung
                                gesuche[row][2].toString(), //Kategorie
                                (String) gesuche[row][4] //PersonenID

                        );
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        //////////// SUCHE //////////////////////
        ClientDefaults.addSearchFunctionality(dienstleistungsgesucheTable, suchenTextField);
    }
}